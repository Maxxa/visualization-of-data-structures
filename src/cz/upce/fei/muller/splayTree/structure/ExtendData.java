package cz.upce.fei.muller.splayTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
class ExtendData<K extends Comparable<K>, T extends AbstractStructureElement & ISplayData<K>> {

    boolean isLeft = true;

    SplayNode<K,T> node;

    ExtendData(SplayNode<K, T> node, boolean isLeft) {
        this.isLeft = isLeft;
        this.node = node;
    }
}
