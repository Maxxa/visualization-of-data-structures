package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class SwapNodeEvent extends AbstractEvent {

    protected Coordinate secondNode;

    public SwapNodeEvent(AbstractStructureElement firstNode, AbstractStructureElement secondNode) {
        super(firstNode);
        this.secondNode = (Coordinate) secondNode;
    }

    public Coordinate getSecondNode() {
        return secondNode;
    }
    public Coordinate getFirstNode() {
        return (Coordinate) element;
    }
}
