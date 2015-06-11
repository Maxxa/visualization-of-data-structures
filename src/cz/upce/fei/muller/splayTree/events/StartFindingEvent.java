package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class StartFindingEvent extends AbstractEvent{
    public StartFindingEvent(AbstractStructureElement content) {
        super(content);
    }

    public SplayNodeImpl getFindingNode(){
        return (SplayNodeImpl) element;
    }
}
