package cz.upce.fei.muller.TwoDTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
class Node<T extends AbstractStructureElement & ICoordinate> {

    public Node left, right;
    T value;

    public Node(Node left, Node right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public boolean hasLeft(){
        return left!=null;
    }

    public boolean hasRight(){
        return right!=null;
    }

    protected void swapValue(Node<T> swapNode){
        T tmp = value;
        value=swapNode.value;
        swapNode.value=tmp;
    }

}
