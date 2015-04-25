package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class RemoveElement extends AbstractEvent{

    public RemoveElement(AbstractStructureElement removedElement) {
        super(removedElement);
    }

    public Coordinate getRemovedELement(){
        return (Coordinate) element;
    }

}
