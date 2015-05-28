package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class SwapNodeEvent extends AbstractEvent {

    protected TreapNodeImpl secondNode;

    public SwapNodeEvent(AbstractStructureElement firstNode, AbstractStructureElement secondNode) {
        super(firstNode);
        this.secondNode = (TreapNodeImpl) secondNode;
    }

    public TreapNodeImpl getSecondNode() {
        return secondNode;
    }
    public TreapNodeImpl getFirstNode() {
        return (TreapNodeImpl) element;
    }
}
