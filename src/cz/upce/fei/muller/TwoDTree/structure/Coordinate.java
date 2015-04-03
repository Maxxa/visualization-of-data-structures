package cz.upce.fei.muller.TwoDTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class Coordinate extends AbstractStructureElement implements ICoordinate {

    private Integer x;
    private Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public void setX(Integer x) {
        this.x = x;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]]",x,y);
    }
}
