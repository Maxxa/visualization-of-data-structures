package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class InsertNode extends AbstractEvent {

    private final boolean isLeftChild;
    private final HeapNode parentNode;

    public InsertNode(HeapNode element,HeapNode parentNode,boolean isLeftChild) {
        super(element);
        this.parentNode = parentNode;
        this.isLeftChild = isLeftChild;
    }
}
