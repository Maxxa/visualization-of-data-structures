package cz.upce.fei.muller.TwoDTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class ExtendData<T  extends AbstractStructureElement & ICoordinate> {

    Node<T> node;
    Dimension dimension;
    boolean isLeaf;

    public ExtendData(Node<T> node, Dimension dimension, boolean isLeaf) {
        this.node = node;
        this.dimension = dimension;
        this.isLeaf=isLeaf;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public T getData() {
        return node.value;
    }

    Node<T> getNode() {
        return node;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public enum Dimension{DIMENSION_X,DIMENSION_Y}
}
