package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class StartInserting extends AbstractEvent{
    public StartInserting(AbstractStructureElement content) {
        super(content);
    }
}
