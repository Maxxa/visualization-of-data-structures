package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class CreateRootEvent extends AbstractEvent {

    public CreateRootEvent(HeapNode element) {
        super(element);
    }

    HeapNode getHeapNode(){
        return (HeapNode) element;
    }

}
