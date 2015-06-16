package cz.upce.fei.muller.splayTree.animations.builders;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.Parent;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderRemoveRoot implements IAnimationBuilder {


    private final WorkBinaryNodeInfo rootInfo;

    public BuilderRemoveRoot(WorkBinaryNodeInfo rootInfo) {
        this.rootInfo = rootInfo;
    }

    @Override
    public Transition getAnimation() {
        ParallelTransition parallelTransition = new ParallelTransition();
        BinaryNodeWithLine node = rootInfo.get().getElement();
        if(rootInfo.hasLeft()){
            addFadeToTransition(parallelTransition, node.getChildLine(NodePosition.LEFT));
        }

        if(rootInfo.hasRight()){
            addFadeToTransition(parallelTransition,node.getChildLine(NodePosition.RIGHT));
        }

        parallelTransition.getChildren().add(getFadeTransition(node,Duration.seconds(1)));

        return parallelTransition;
    }

    private void addFadeToTransition(ParallelTransition pt, LineElement element) {
        pt.getChildren().add(getFadeTransition(element,Duration.ONE));
    }

    private FadeTransition getFadeTransition(Parent element,Duration duration) {
        return FadesTransitionBuilder.getTransition(element, duration, 1, 0);
    }
}
