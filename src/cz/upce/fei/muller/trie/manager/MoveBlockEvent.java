package cz.upce.fei.muller.trie.manager;

import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class MoveBlockEvent {

    private final Integer id;
    private final Point2D newPoint;

    public MoveBlockEvent(Integer id, Point2D newPoint, Point2D tmp, boolean changeBlock) {
        this.id = id;
        this.newPoint = newPoint;
    }
}
