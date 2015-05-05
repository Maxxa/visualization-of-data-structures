package cz.upce.fei.muller.treap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public interface ITreap<K extends Comparable<K>,T extends AbstractStructureElement & IPriorityKeyContainer<K>> {

    public void insert(T inserted);

    public void find(K element);

    public void remove(K element);

    public void clear();
    public boolean isEmpty();


}
