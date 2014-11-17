package cz.upce.fei.muller.binaryHeap.structure;

import cz.commons.utils.GeneratorElementsNumbers;
import cz.upce.fei.common.core.IStructureElement;

/**
 * @author Vojtěch Müller
 */
public class HeapNode implements IStructureElement {

    private Integer value;

    private final Integer id = GeneratorElementsNumbers.getNextId();

    public HeapNode(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(value,((HeapNode)o).value);
    }
}
