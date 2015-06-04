package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class MoveToChildEvent extends AbstractEvent{

    private final SplayNodeImpl comparingNode;

    public MoveToChildEvent(AbstractStructureElement newNode, AbstractStructureElement comparingNode) {
        super(newNode);
        this.comparingNode = (SplayNodeImpl) comparingNode;
    }

    public SplayNodeImpl getNewNode() {
        return (SplayNodeImpl) element;
    }

    public SplayNodeImpl getComparingNode() {
        return comparingNode;
    }

}
