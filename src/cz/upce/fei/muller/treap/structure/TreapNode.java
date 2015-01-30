package cz.upce.fei.muller.treap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class TreapNode extends AbstractStructureElement {

    private Integer value;

    public TreapNode(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(value,((TreapNode)o).value);
    }

    public Integer getValue() {
        return value;
    }
}
