package cz.upce.fei.muller.binaryHeap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class HeapNode<T extends Comparable<T>> extends AbstractStructureElement implements Comparable<HeapNode<T>> {

    private T value;

    public HeapNode(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(HeapNode<T> o) {
        return value.compareTo(o.value);
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
