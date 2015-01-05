package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class CompareNodeEvent extends AbstractEvent{

    private final HeapNode secondNode;
    private final boolean isTrue;

    public CompareNodeEvent(HeapNode firstNode, HeapNode second, boolean isSwap) {
        super(firstNode);
        this.secondNode = second;
        this.isTrue = isSwap;
    }

    public HeapNode getFirstNode() {
        return (HeapNode) element;
    }
    public HeapNode getSecondNode() {
        return secondNode;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
