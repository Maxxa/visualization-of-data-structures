package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class RemoveElementEvent extends AbstractEvent{

    public RemoveElementEvent(AbstractStructureElement removedElement) {
        super(removedElement);
    }

    public Coordinate getRemovedElement(){
        return (Coordinate) element;
    }

}
