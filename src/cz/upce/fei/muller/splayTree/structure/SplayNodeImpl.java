package cz.upce.fei.muller.splayTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class SplayNodeImpl extends AbstractStructureElement implements ISplayData<Integer> {

    private Integer key;

    public SplayNodeImpl(Integer key) {
        this.key = key;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
