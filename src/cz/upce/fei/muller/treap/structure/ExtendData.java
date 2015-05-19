package cz.upce.fei.muller.treap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
class ExtendData<K extends Comparable<K>, T extends AbstractStructureElement & IPriorityKeyContainer<K>> {

    boolean isLeft = true;

    TreapNode<K,T> node;

    ExtendData(TreapNode<K, T> node, boolean isLeft) {
        this.isLeft = isLeft;
        this.node = node;
    }
}
