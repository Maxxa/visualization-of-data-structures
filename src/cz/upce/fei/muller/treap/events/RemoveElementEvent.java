package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class RemoveElementEvent extends AbstractEvent {
    public RemoveElementEvent(AbstractStructureElement returnValue) {
        super(returnValue);
    }

    public TreapNodeImpl getRemoved() {
        return (TreapNodeImpl) element;
    }
}
