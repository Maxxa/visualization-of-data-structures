package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderSwapRoot extends BuilderSwapNode {

    private final BinaryHeapNode leafParent;

    private LineElement lineParentOfLeaf;

    public BuilderSwapRoot(BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond, Point2D firstPoint, Point2D secondPoint, BinaryHeapNode leafParent) {
        super(nodeFirst, nodeSecond, firstPoint, secondPoint);
        this.leafParent = leafParent;
    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = super.getAnimation();
        pt.getChildren().add(0, getFadeTransition());
        pt.setOnFinished(setFinishedHandler());
        return new ParallelTransition(pt);
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwapRootEndEventHandler(lineParentOfLeaf,leafParent,nodeFirst,nodeSecond);
    }

    private FadeTransition getFadeTransition() {
        BinaryHeapNode left= leafParent.getChild(NodePosition.LEFT);
        lineParentOfLeaf = leafParent.getChildLine(left!=null && left.getElementId()==nodeSecond.getElementId()?NodePosition.LEFT:NodePosition.RIGHT);
        return FadesTransitionBuilder.getTransition(lineParentOfLeaf,Duration.millis(1),1,0);
    }

}
