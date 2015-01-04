package cz.upce.fei.muller.binaryHeap.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class LastEvent extends AbstractEvent{
    public LastEvent(AbstractStructureElement element) {
        super(element);
    }
}
