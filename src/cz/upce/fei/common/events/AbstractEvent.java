package cz.upce.fei.common.events;

import cz.upce.fei.common.core.IStructureElement;

/**
 * @author Vojtěch Muller
 */
public abstract class AbstractEvent {

    protected IStructureElement element;

    public AbstractEvent(IStructureElement element) {
        this.element = element;
    }
}
