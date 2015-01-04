package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class SwapNodeEvent extends AbstractEvent {

    protected HeapNode secondNode;

    public SwapNodeEvent(HeapNode element, HeapNode secondNode) {
        super(element);
        this.secondNode =secondNode;
    }
}
