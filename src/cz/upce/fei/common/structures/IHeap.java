package cz.upce.fei.common.structures;

import cz.upce.fei.common.structures.BinaryNode;

/**
 * @author Vojtěch Müller
 */
public interface IHeap<T> {

    void clear();

    void insert(T item);

    T removeRoot();

}
