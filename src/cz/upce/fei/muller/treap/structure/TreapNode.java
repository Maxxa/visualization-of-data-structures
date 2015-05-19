package cz.upce.fei.muller.treap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class TreapNode<K extends Comparable<K>,T extends AbstractStructureElement & IPriorityKeyContainer<K>> {

    protected T key;

    protected TreapNode<K,T> parent;
    protected TreapNode<K,T> left;
    protected TreapNode<K,T> right;

    public TreapNode(T key,  TreapNode<K,T> parent) {
        this.key = key;
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    protected int compareKey(TreapNode<K,T> node){
        return key.getKey().compareTo(node.key.getKey());
    }

    protected int comparePriority(TreapNode<K,T> node){
        return key.getPriority().compareTo(node.key.getPriority());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(TreapNode.class.isInstance(obj)){
            TreapNode<K,T> tmp = (TreapNode<K, T>) obj;
            return key.getPriority()==tmp.key.getPriority() &&
                   key.getKey().compareTo(tmp.key.getKey())==0;
        }
        return false;
    }
}

