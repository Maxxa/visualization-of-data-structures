package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.IStructureElement;

/**
 * @author Vojtěch Müller
 */
public class BinaryTree<T extends IStructureElement> implements IBinaryTree<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        clear();
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void insertRoot(T value) {
        if(isEmpty()){
            root = new BinaryNode<>(value, BinaryNode.NodePosition.ROOT);
        }
    }

    @Override
    public void insertLeftChild(BinaryNode<T> parent, T value) {
        if(parent==null) return;

        removeLeftChild(parent); // clear left sub tree

        parent.leftChild = new BinaryNode<>(value,BinaryNode.NodePosition.LEFT);
        parent.leftChild.parent = parent;
    }

    @Override
    public void insertRightChild(BinaryNode<T> parent, T value) {
        if(parent==null) return;

        removeRightChild(parent); // clear right sub tree

        parent.rightChild = new BinaryNode<>(value,BinaryNode.NodePosition.RIGHT);
        parent.rightChild.parent = parent;
    }

    @Override
    public boolean isLeaf(BinaryNode<T> binaryNode) {
        return binaryNode.existLeftChild() && binaryNode.existRightChild();
    }

    @Override
    public T removeLeftChild(BinaryNode<T> element) {
        T result = null;
        if(existElement(element)) {
            if(element.existLeftChild()){
                result = element.value;
                element.leftChild.parent = element.leftChild = null;
            }
        }
        return result;
    }

    @Override
    public T removeRightChild(BinaryNode<T> element) {
        if(element==null) return null;
        T result = null;
        if(element.existRightChild()){
            result = element.value;
            element.rightChild.parent = element.rightChild = null;
        }
        return result;
    }

    @Override
    public BinaryNode getRoot() {
        return root;
    }

    @Override
    public BinaryNode getLeftChild(BinaryNode<T> parent) {
        return existElement(parent)?parent.leftChild:null;
    }

    @Override
    public BinaryNode getRightChild(BinaryNode<T> parent) {
        return existElement(parent)?parent.rightChild:null;
    }

    @Override
    public void swapNodes(BinaryNode<T> firstParentNode, BinaryNode<T> secondChildNode) {
        if(existElement(firstParentNode)&&existElement(secondChildNode)){
            T temp = firstParentNode.value;
            firstParentNode.value = secondChildNode.value;
            secondChildNode.value = temp;
        }
    }

    private boolean existElement(BinaryNode<T> element) {
        return element!=null;
    }

}
