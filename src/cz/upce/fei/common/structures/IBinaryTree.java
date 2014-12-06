package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public interface IBinaryTree<T,N> {

    void clear();

    boolean isEmpty();

    int countItems();

    void insertRoot(T item);

    void insertLeftChild(N parent, T item);

    void insertRightChild(N parent, T item);

    boolean isLeaf(N item);

    T removeLeftLeaf(N parent);

    T removeRightLeaf(N parent);

    T getRoot();

    T getParent(N son);

    T getLeft(N parent);

    T getRight(N parent);

    void swapNode(N index1, N index2);
}
