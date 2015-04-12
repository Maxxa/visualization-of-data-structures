package cz.upce.fei.muller.TwoDTree.events;

import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
 * @author Vojtěch Müller
 */
public class StartFindingEvent {
    private final Coordinate coordinate;

    public StartFindingEvent(int x, int y) {
        this.coordinate = new Coordinate(x,y);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
