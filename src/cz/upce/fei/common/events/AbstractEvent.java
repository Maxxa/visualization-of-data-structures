package cz.upce.fei.common.events;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Muller
 */
public abstract class AbstractEvent {

    protected AbstractStructureElement element;

    public AbstractEvent(AbstractStructureElement element) {
        this.element = element;
    }
}
