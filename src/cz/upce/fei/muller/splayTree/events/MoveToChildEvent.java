package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class MoveToChildEvent extends AbstractEvent{

    private final Object findingKey;

    public MoveToChildEvent(Object findingKey, AbstractStructureElement comparingNode) {
        super(comparingNode);
        this.findingKey = findingKey;
    }

    public Object getFindingKey() {
        return findingKey;
    }

    public SplayNodeImpl getComparingNode() {
        return (SplayNodeImpl) element;
    }

}
