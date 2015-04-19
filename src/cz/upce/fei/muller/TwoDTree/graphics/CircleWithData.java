package cz.upce.fei.muller.TwoDTree.graphics;

import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import javafx.scene.shape.Circle;

/**
 * @author Vojtěch Müller
 */
public class CircleWithData extends Circle {

    private final Coordinate coordinate;

    public CircleWithData(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
