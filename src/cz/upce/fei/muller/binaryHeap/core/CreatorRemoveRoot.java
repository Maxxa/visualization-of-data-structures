package cz.upce.fei.muller.binaryHeap.core;

import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.ParallelTransition;

/**
 * @author Vojtěch Müller
 */
public class CreatorRemoveRoot implements IAnimationCreator {
    public CreatorRemoveRoot(BinaryHeapNode node) {
    }

    @Override
    public ParallelTransition getAnimation() {
        return null;
    }
}
