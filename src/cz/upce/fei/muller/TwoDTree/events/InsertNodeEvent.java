package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class InsertNodeEvent extends AbstractEvent {

    private final boolean isLeftChild;
    private final Coordinate parentNode;

    public InsertNodeEvent(AbstractStructureElement element, AbstractStructureElement parentNode, boolean isLeftChild) {
        super(element);
        this.parentNode = (Coordinate)parentNode;
        this.isLeftChild = isLeftChild;
    }

    public Coordinate getParentNode(){
        return parentNode;
    }

    public Coordinate getNewNode(){
        return (Coordinate) element;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }
}
