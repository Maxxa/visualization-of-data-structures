package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class CreateRootEvent extends AbstractEvent {

    public CreateRootEvent(AbstractStructureElement element) {
        super(element);
    }

    public SplayNodeImpl getNode(){
        return (SplayNodeImpl) element;
    }

}
