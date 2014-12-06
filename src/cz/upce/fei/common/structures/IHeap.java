package cz.upce.fei.common.structures;

import cz.upce.fei.common.structures.BinaryNode;

import java.util.Comparator;

/**
 * @author Vojtěch Müller
 */
public interface IHeap<T> {

    void clear();

    void insert(T item);

    T removeRoot();

    boolean isHeap(Comparator com, IBinaryTree tree);
}
