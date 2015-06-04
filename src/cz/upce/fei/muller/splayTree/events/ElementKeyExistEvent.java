package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class ElementKeyExistEvent extends AbstractEvent{
    public ElementKeyExistEvent(AbstractStructureElement inserted) {
        super(inserted);
    }
}
