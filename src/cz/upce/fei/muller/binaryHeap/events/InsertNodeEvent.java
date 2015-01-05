package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
 * @author Vojtěch Müller
 */
public class InsertNodeEvent extends AbstractEvent {

    private final boolean isLeftChild;
    private final HeapNode parentNode;

    public InsertNodeEvent(HeapNode element, HeapNode parentNode, boolean isLeftChild) {
        super(element);
        this.parentNode = parentNode;
        this.isLeftChild = isLeftChild;
    }

    public HeapNode getParentNode(){
        return parentNode;
    }

    public HeapNode getNewNode(){
        return (HeapNode) element;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }
}
