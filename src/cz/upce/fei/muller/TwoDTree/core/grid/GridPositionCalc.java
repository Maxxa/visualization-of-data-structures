package cz.upce.fei.muller.TwoDTree.core.grid;

import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class GridPositionCalc {

    private final int x;
    private final int y;
    private final int size;

    private final double rate;

    public GridPositionCalc(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        rate = (double) size / 200;
    }

    public Point2D getPosition(double x, double y) {
        return new Point2D(
                this.x + x * rate,
                this.y + this.size - y * rate);
    }

    public boolean isPointInRect(double x, double y) {
        return
                x >= this.x && x <= (this.x + size) &&
                        y >= this.y && y < (this.y + size)
                ;
    }
}
