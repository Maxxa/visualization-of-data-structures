package cz.upce.fei.muller.TwoDTree.events;

/**
 * @author Vojtěch Müller
 */
public class FindingMinMaxEvent {
    private final boolean isMin;
    private final boolean isXCoordinate;

    public FindingMinMaxEvent(boolean isMax, boolean isXCoordinate) {
        this.isMin = isMax;
        this.isXCoordinate = isXCoordinate;
    }

    public boolean isMin() {
        return isMin;
    }

    public boolean isXCoordinate() {
        return isXCoordinate;
    }
}
