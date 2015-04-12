package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class FindEvent extends AbstractEvent{

    private final boolean isCompareX;

    public FindEvent(AbstractStructureElement value, boolean isCompareX) {
        super(value);
        this.isCompareX = isCompareX;
    }

    public boolean isCompareX() {
        return isCompareX;
    }

    public Integer getComparedNodeId(){
        return this.element.getId();
    }
}
