package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class InsertNodeEvent extends AbstractEvent {

    private final boolean isLeftChild;
    private final TreapNodeImpl parentNode;

    public InsertNodeEvent(AbstractStructureElement element, AbstractStructureElement parentNode, boolean isLeftChild) {
        super(element);
        this.parentNode = (TreapNodeImpl)parentNode;
        this.isLeftChild = isLeftChild;
    }

    public TreapNodeImpl getParentNode(){
        return parentNode;
    }

    public TreapNodeImpl getNewNode(){
        return (TreapNodeImpl) element;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }
}
