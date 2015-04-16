package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class ElementFindEvent extends AbstractEvent{
    public ElementFindEvent(AbstractStructureElement value) {
        super(value);
    }

    public AbstractStructureElement getFindNode(){
        return element;
    }
}
