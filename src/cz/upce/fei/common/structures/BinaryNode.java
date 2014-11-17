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

    BinaryNode(T value) {
        this.value = value;
    }

    boolean isLeftChild(){
        return leftChild!=null;
    }
    boolean isRightChild(){
        return rightChild!=null;
    }

    public T getValue() {
        return value;
    }

}
