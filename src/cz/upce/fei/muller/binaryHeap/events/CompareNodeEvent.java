package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class CompareNodeEvent extends AbstractEvent{

    private final HeapNode second;
    private final boolean isTrue;

    public CompareNodeEvent(HeapNode element, HeapNode second, boolean isSwap) {
        super(element);
        this.second = second;
        this.isTrue = isSwap;
    }
}
