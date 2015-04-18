package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class ElementFindEndEvent extends AbstractEvent{

    private final boolean isFind;

    public ElementFindEndEvent(AbstractStructureElement value) {
        super(value);
        isFind=true;
    }
    public ElementFindEndEvent() {
        super(null);
        this.isFind = false;
    }

    public AbstractStructureElement getFindNode(){
        return element;
    }

    public boolean isFind() {
        return isFind;
    }
}
