package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class SwapNodeEvent extends AbstractEvent {

    protected HeapNode secondNode;

    public SwapNodeEvent(HeapNode firstNode, HeapNode secondNode) {
        super(firstNode);
        this.secondNode =secondNode;
    }

    public HeapNode getSecondNode() {
        return secondNode;
    }
    public HeapNode getFirstNode() {
        return (HeapNode) element;
    }
}
