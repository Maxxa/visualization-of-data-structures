package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class CreateRootEvent extends AbstractEvent {

    public CreateRootEvent(AbstractStructureElement element) {
        super(element);
    }

    public TreapNodeImpl getNode(){
        return (TreapNodeImpl) element;
    }

}
