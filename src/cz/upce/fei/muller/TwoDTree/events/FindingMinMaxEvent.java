package cz.upce.fei.muller.TwoDTree.events;

/**
 * @author Vojtěch Müller
 */
public class FindingMinMaxEvent {
    private final boolean isMax;

    public FindingMinMaxEvent(boolean isMax) {
        this.isMax = isMax;
    }

    public boolean isMax() {
        return isMax;
    }
}
