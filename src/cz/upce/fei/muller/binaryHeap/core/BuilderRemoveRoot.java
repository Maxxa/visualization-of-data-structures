package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderRemoveRoot implements IAnimationBuilder {

    private final BinaryHeapNode node;

    public BuilderRemoveRoot(BinaryHeapNode node) {
        this.node = node;
    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = new ParallelTransition(
                FadeTransitionBuilder.create()
                    .duration(Duration.seconds(1))
                    .fromValue(1)
                    .toValue(0)
                    .cycleCount(1)
                    .node(node)
                    .build());

        pt.setOnFinished(getFinishedHandler());
        return pt;


    }

    public EventHandler<ActionEvent> getFinishedHandler() {
        return new StepEventHandler() {

            BinaryHeapNode root = node;
            LineElement left = node.getChildLine(NodePosition.LEFT);
            LineElement right = node.getChildLine(NodePosition.RIGHT);

            @Override
            protected void handleForward(ActionEvent actionEvent) {
                //TODO
            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                //TODO
            }
        };
    }
}
