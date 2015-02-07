package cz.upce.fei.muller.binaryHeap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class HeapNode extends AbstractStructureElement {

    private Integer value;

    public HeapNode(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(value,((HeapNode)o).value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
