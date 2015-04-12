package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class MoveToChildEvent {
    private final Coordinate newNode;
    private final Coordinate comparingNode;
    private final boolean isCompareX;

    public MoveToChildEvent(AbstractStructureElement newNode, AbstractStructureElement comparingNode, boolean isCompareX) {
        this.newNode = (Coordinate) newNode;
        this.comparingNode = (Coordinate) comparingNode;
        this.isCompareX = isCompareX;
    }

    public Coordinate getNewNode() {
        return newNode;
    }

    public Coordinate getComparingNode() {
        return comparingNode;
    }

    public boolean isCompareX() {
        return isCompareX;
    }
}
