package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class BinaryTreeDyn<T extends AbstractStructureElement> implements IBinaryTreeDyn<T> {

    private BinaryNode<T> root;

    private int countItem;

    public BinaryTreeDyn() {
        clear();
    }

    @Override
    public void clear() {
        root = null;
        countItem = 0;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public int countItems() {
        return countItem;
    }

    @Override
    public void insertRoot(T value) {
        if(isEmpty()){
            root = new BinaryNode<>(value, BinaryNode.NodePosition.ROOT);
            countItem++;
        }
    }

    @Override
    public void insertLeftChild(BinaryNode<T> parent, T value) {
        if(parent==null) return;
//TODO
//        removeLeftChild(parent); // clear left sub tree

        parent.leftChild = new BinaryNode<>(value,BinaryNode.NodePosition.LEFT);
        parent.leftChild.parent = parent;
    }

    @Override
    public void insertRightChild(BinaryNode<T> parent, T value) {
        if(parent==null) return;
//TODO
//        removeRightChild(parent); // clear right sub tree

        parent.rightChild = new BinaryNode<>(value,BinaryNode.NodePosition.RIGHT);
        parent.rightChild.parent = parent;
    }

    @Override
    public boolean isLeaf(BinaryNode<T> binaryNode) {
        return binaryNode.existLeftChild() && binaryNode.existRightChild();
    }

    @Override
    public T removeLeftLeaf(BinaryNode<T> parent) {
        T result = null;
        if(existElement(parent)) {
            if(parent.existLeftChild()){
                if(isLeaf(parent.leftChild)) {
                    result = parent.value;
                    parent.leftChild.parent = parent.leftChild = null;
                }
            }
        }
        return result;
    }

    @Override
    public T removeRightLeaf(BinaryNode<T> parent) {
        T result = null;
        if(existElement(parent)) {
            if(parent.existRightChild()){
                if(isLeaf(parent.rightChild)) {
                    result = parent.value;
                    parent.rightChild.parent = parent.rightChild = null;
                }
            }
        }
        return result;
    }

    @Override
    public T getRoot() {
        return root.value;
    }

    @Override
    public T getParent(BinaryNode<T> son) {
        return null;
    }

    @Override
    public T getLeft(BinaryNode<T> parent) {
        BinaryNode<T> tmp = getLeftNode(parent);
        return tmp!=null?tmp.value:null;
    }

    @Override
    public T getRight(BinaryNode<T> parent) {
        BinaryNode<T> tmp = getRightNode(parent);
        return tmp!=null?tmp.value:null;
    }

    @Override
    public void swapNode(BinaryNode<T> firstParentNode, BinaryNode<T> secondChildNode) {
        if(existElement(firstParentNode)&&existElement(secondChildNode)){
            T temp = firstParentNode.value;
            firstParentNode.value = secondChildNode.value;
            secondChildNode.value = temp;
        }
    }

    private boolean existElement(BinaryNode<T> element) {
        return element!=null;
    }

    @Override
    public BinaryNode<T> getRootNode() {
        return root;
    }

    @Override
    public BinaryNode<T> getLeftNode(BinaryNode<T> parent) {
        return existElement(parent)?parent.leftChild:null;
    }

    @Override
    public BinaryNode<T> getRightNode(BinaryNode<T> parent) {
        return existElement(parent)?parent.rightChild:null;
    }
}
