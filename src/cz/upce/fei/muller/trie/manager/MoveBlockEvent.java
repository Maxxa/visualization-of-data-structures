package cz.upce.fei.muller.trie.manager;

import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class MoveBlockEvent {

    private final Integer id;
    private final Point2D newPoint;
    private final Point2D oldPoint;

    public MoveBlockEvent(Integer id, Point2D oldPoint, Point2D newPoint, boolean changeBlock) {
        this.id = id;
        this.newPoint = newPoint;
        this.oldPoint= oldPoint;
    }

    public Integer getId() {
        return id;
    }

    public Point2D getOldPoint() {
        return oldPoint;
    }

    public Point2D getNewPoint() {
        return newPoint;
    }

    @Override
    public String toString() {
        return "MoveBlockEvent{" +
                "id=" + id +
                ", newPoint=" + newPoint +
                '}';
    }
}
