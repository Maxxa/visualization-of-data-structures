package cz.upce.fei.muller.splayTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public interface ISplayTree<K extends Comparable<K>,T extends AbstractStructureElement & ISplayData<K>> {

    void insert(T node);

    T find(K key);

    T delete(K key);

    void clear();

    boolean isEmpty();
}

