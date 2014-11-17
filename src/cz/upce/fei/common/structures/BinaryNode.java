package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.IStructureElement;

/**
* @author Vojtěch Müller
*/
public class BinaryNode<T extends IStructureElement> {

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

    public boolean isLeftChild(){
        return position ==NodePosition.LEFT;
    }

    public boolean isRightChild(){
        return position ==NodePosition.RIGHT;
    }
    public boolean isRoot(){
        return position ==NodePosition.ROOT;
    }

    enum NodePosition{
        LEFT,RIGHT,ROOT
    }

}
