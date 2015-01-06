package cz.upce.fei.muller.binaryHeap.core;

import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.ParallelTransition;

/**
 * @author Vojtěch Müller
 */
public class CreatorCompareNode implements IAnimationCreator {
    public CreatorCompareNode(BinaryHeapNode node, BinaryHeapNode node1, boolean aTrue) {
    }

    @Override
    public ParallelTransition getAnimation() {
        return null;
    }
}
