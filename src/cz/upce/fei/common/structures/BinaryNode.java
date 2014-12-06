package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
* @author Vojtěch Müller
*/
public class BinaryNode<T extends AbstractStructureElement> {

    T value =null;

    BinaryNode leftChild = null;

    BinaryNode rightChild = null;

    BinaryNode parent = null;

    NodePosition position;

    BinaryNode(T value,NodePosition position) {
        this.value = value;
        this.position = position;
    }

    boolean existLeftChild(){
        return leftChild!=null;
    }

    boolean existRightChild(){
        return rightChild!=null;
    }

    public T getValue() {
        return value;
    }

    public boolean isLeftPosition(){
        return position ==NodePosition.LEFT;
    }

    public boolean isRightPosition(){
        return position ==NodePosition.RIGHT;
    }
    public boolean isRoot(){
        return position ==NodePosition.ROOT;
    }

    enum NodePosition{
        LEFT,RIGHT,ROOT
    }

}
