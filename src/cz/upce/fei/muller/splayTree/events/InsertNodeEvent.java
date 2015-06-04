package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class InsertNodeEvent extends AbstractEvent {

    private final boolean isLeftChild;
    private final SplayNodeImpl parentNode;

    public InsertNodeEvent(AbstractStructureElement element, AbstractStructureElement parentNode, boolean isLeftChild) {
        super(element);
        this.parentNode = (SplayNodeImpl)parentNode;
        this.isLeftChild = isLeftChild;
    }

    public SplayNodeImpl getParentNode(){
        return parentNode;
    }

    public SplayNodeImpl getNewNode(){
        return (SplayNodeImpl) element;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }
}
