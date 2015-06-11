package cz.upce.fei.muller.splayTree.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.FadesTransitionHandler;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import cz.upce.fei.common.animations.SwitchElementEndEventHandler;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderAddElement extends BuilderAnimMoveNode {

    private final WorkBinaryNodeInfo currentInformation;
    private final List<SwitchConnectorHelper> helpers;
    private final List<Transition> movesTransition;
    private final ParallelTransition elementMovings;

    public BuilderAddElement(Point2D to, Point2D creatingPoint,
                             SplayGraphicsNodeElement element, WorkBinaryNodeInfo info,
                             ParallelTransition elementMovings, List<SwitchConnectorHelper> helpers, List<Transition> movesTransition) {
        super(creatingPoint, to, element);
        this.elementMovings = elementMovings;
        currentInformation = info;
        this.helpers = helpers;
        this.movesTransition = movesTransition;
    }


    @Override
    public Transition getAnimation() {
        ParallelTransition movingsTransitions = new ParallelTransition();
        movingsTransitions.getChildren().addAll(
                elementMovings,
                super.getAnimation()

        );
        movingsTransitions.getChildren().addAll(this.movesTransition);
        movingsTransitions.setOnFinished(new SwitchElementEndEventHandler(helpers));
        return new SequentialTransition(
                lineFades(true),
                movingsTransitions,
                lineFades(false)
        );
    }

    private Transition lineFades(boolean visibility) {
        ParallelTransition pt = new ParallelTransition();
        for (SwitchConnectorHelper element : helpers) {
            addFadeToTransition(pt, element.getLine(), visibility);
        }
        pt.setOnFinished(new FadesTransitionHandler(helpers, visibility));
        return pt;
    }

    private void addFadeToTransition(ParallelTransition pt, LineElement element, boolean visibility) {
        pt.getChildren().add(getFadeTransition(element, visibility));
    }

    private FadeTransition getFadeTransition(LineElement element, boolean visibility) {
        int from = visibility ? 1 : 0;
        int to = visibility ? 0 : 1;
        return FadesTransitionBuilder.getTransition(element, Duration.ONE, from, to);
    }

//    private Transition initMovings(List<>) {
//        ParallelTransition pt = new ParallelTransition();
//        if(moveParentsElements.size()>0){
//            pt.getChildren().addAll(moveParentsElements);
//        }
//        moveParentsElements.clear();
//        return pt;
//    }
}
