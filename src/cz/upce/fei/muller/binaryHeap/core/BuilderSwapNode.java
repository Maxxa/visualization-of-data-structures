package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderSwapNode implements IAnimationBuilder {

    protected final Point2D firstPoint;
    protected final Point2D secondPoint;
    protected BinaryHeapNode nodeFirst;
    protected BinaryHeapNode nodeSecond;

    public BuilderSwapNode(BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond, Point2D firstPoint, Point2D secondPoint) {
        this.nodeFirst = nodeFirst;
        this.nodeSecond= nodeSecond;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    @Override
    public ParallelTransition getAnimation() {
        TranslateTransition t1= RelativeTranslateTransition.build(nodeSecond, secondPoint,firstPoint, new Duration(1000));
        TranslateTransition t2= RelativeTranslateTransition.build(nodeFirst,firstPoint,secondPoint,new Duration(1000));
        ParallelTransition pt = new ParallelTransition(t1,t2);
        return pt;
    }

}
