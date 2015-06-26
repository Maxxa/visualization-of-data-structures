package cz.upce.fei.common.animations.builders;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.RotationDirectionElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.FadesTransitionHandler;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import cz.upce.fei.common.animations.SwitchElementEndEventHandler;
import cz.upce.fei.common.core.IAnimationBuilder;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderRotationElement implements IAnimationBuilder {

    private final ParallelTransition reconstructionMoves;
    private final List<SwitchConnectorHelper> helpers;
    private final List<Transition> moveParentsElements;
    private final RotationDirectionElement rotationDirectionElement;

    public BuilderRotationElement(ParallelTransition reconstructionMoves, List<SwitchConnectorHelper> helpers, List<Transition> moveParentsElements, RotationDirectionElement rotationDirectionElement) {
        this.reconstructionMoves = reconstructionMoves;
        this.helpers = helpers;
        this.moveParentsElements = moveParentsElements;
        this.rotationDirectionElement = rotationDirectionElement;
    }

    @Override
    public Transition getAnimation() {
        ParallelTransition pt = new ParallelTransition(
                getFadeTransition(rotationDirectionElement,false,Duration.seconds(2)),
                getFadeTransition(rotationDirectionElement,true,Duration.seconds(2))
        );
        pt.setOnFinished(getDirectionEndHandler());
        SequentialTransition sq = new SequentialTransition(
                pt,
                getLinesFades(true), reconstructionMoves, getLinesFades(false),initMoves());
        reconstructionMoves.setOnFinished(setFinishedHandler());
        return sq;
    }

    private Transition initMoves() {
        ParallelTransition pt = new ParallelTransition();
        if(moveParentsElements.size()>0){
            pt.getChildren().addAll(moveParentsElements);
        }
        moveParentsElements.clear();
        return pt;
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwitchElementEndEventHandler(helpers);
    }

    private ParallelTransition getLinesFades(boolean visibility) {
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

    private FadeTransition getFadeTransition(Parent element, boolean visibility) {
        return getFadeTransition(element,visibility,Duration.ONE);
    }

    private FadeTransition getFadeTransition(Parent element, boolean visibility,Duration duration) {
        int from = visibility ? 1 : 0;
        int to = visibility ? 0 : 1;
        return FadesTransitionBuilder.getTransition(element, duration, from, to);
    }

    public EventHandler<ActionEvent> getDirectionEndHandler() {
        return new StepEventHandler() {
            @Override
            protected void handleForward(ActionEvent actionEvent) {
                init();
            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                init();
            }

            private void init() {
                rotationDirectionElement.setVisible(false);
                rotationDirectionElement.setOpacity(0);
            }
        };
    }
}
