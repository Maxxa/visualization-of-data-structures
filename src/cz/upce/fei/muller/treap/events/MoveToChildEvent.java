package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class MoveToChildEvent extends AbstractEvent{

    private final TreapNodeImpl comparingNode;

    public MoveToChildEvent(AbstractStructureElement newNode, AbstractStructureElement comparingNode) {
        super(newNode);
        this.comparingNode = (TreapNodeImpl) comparingNode;
    }

    public TreapNodeImpl getNewNode() {
        return (TreapNodeImpl) element;
    }

    public TreapNodeImpl getComparingNode() {
        return comparingNode;
    }

}
