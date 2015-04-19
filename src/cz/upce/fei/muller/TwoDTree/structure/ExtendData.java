package cz.upce.fei.muller.TwoDTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class ExtendData<T  extends AbstractStructureElement & ICoordinate> {

    T data;
    Dimension dimension;
    boolean isLeaf;

    public ExtendData(T data, Dimension dimension, boolean isLeaf) {
        this.data = data;
        this.dimension = dimension;
        this.isLeaf=isLeaf;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public T getData() {
        return data;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public enum Dimension{DIMENSION_X,DIMENSION_Y}
}
