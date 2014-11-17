package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.IStructureElement;

/**
 * @author Vojtěch Müller
 */
public interface IBinaryTree<T extends IStructureElement> {

    void clear();

    boolean isEmpty();

    void insertRoot(T value);

    void insertLeftChild(BinaryNode<T> parent,T value);
    void insertRightChild(BinaryNode<T> parent,T value);

    boolean isLeaf(BinaryNode<T> binaryNode);

    T removeLeftChild(BinaryNode<T> element);
    T removeRightChild(BinaryNode<T> element);

    BinaryNode<T> getRoot();

    BinaryNode<T> getLeftChild(BinaryNode<T> parent);
    BinaryNode<T> getRightChild(BinaryNode<T> parent);

    void swapNodes(BinaryNode<T> firstNode,BinaryNode<T> secondNode);

}
