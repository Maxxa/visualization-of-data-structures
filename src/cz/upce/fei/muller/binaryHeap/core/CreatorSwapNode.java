package cz.upce.fei.muller.binaryHeap.core;

import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class CreatorSwapNode implements IAnimationCreator {

    private final Point2D firstPoint;
    private final Point2D secondPoint;
    private BinaryHeapNode nodeFirst;
    private BinaryHeapNode nodeSecond;

    public CreatorSwapNode(BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond, Point2D firstPoint, Point2D secondPoint) {
        this.nodeFirst = nodeFirst;
        this.nodeSecond= nodeSecond;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public ParallelTransition getAnimation() {
        TranslateTransition t1= TranslateTransitionBuilder.create()
                .fromX(firstPoint.getX()).fromY(firstPoint.getY())
                .toX(secondPoint.getX()).toY(secondPoint.getY())
                .duration(new Duration(1000)).node(nodeSecond).build();

        TranslateTransition t2=TranslateTransitionBuilder.create()
                .fromX(secondPoint.getX()).fromY(secondPoint.getY())
                .toX(firstPoint.getX()).toY(firstPoint.getY())
                .duration(new Duration(1000)).node(nodeFirst).build();

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(t1,t2);
        return pt;
    }
}
