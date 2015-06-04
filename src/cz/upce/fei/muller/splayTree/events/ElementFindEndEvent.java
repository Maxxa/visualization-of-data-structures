package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class ElementFindEndEvent extends AbstractEvent{

    private final boolean isFind;

    public ElementFindEndEvent(AbstractStructureElement value,boolean isFind) {
        super(value);
        this.isFind=isFind;
    }

    public AbstractStructureElement getFindNode(){
        return element;
    }

    public boolean isFind() {
        return isFind;
    }
}
