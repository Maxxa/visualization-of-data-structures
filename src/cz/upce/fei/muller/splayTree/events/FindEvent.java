package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class FindEvent extends AbstractEvent{

    public FindEvent(AbstractStructureElement value) {
        super(value);
    }

    public Integer getComparedNodeId(){
        return this.element.getId();
    }
}
