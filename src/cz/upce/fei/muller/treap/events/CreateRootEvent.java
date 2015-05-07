package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class CreateRootEvent extends AbstractEvent {

    public CreateRootEvent(AbstractStructureElement element) {
        super(element);
    }

    public Coordinate getNode(){
        return (Coordinate) element;
    }

}
