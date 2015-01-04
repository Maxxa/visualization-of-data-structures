package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class RemoveRootEvent extends AbstractEvent {
    public RemoveRootEvent(HeapNode element) {
        super(element);
    }
}
