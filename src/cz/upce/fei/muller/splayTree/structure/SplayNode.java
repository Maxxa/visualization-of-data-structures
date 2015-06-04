package cz.upce.fei.muller.splayTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
class SplayNode<K extends Comparable<K>,T extends AbstractStructureElement & ISplayData<K>> {

    protected SplayNode<K,T> parent;

    protected SplayNode<K,T> left;

    protected SplayNode<K,T> right;

    protected T contents;

    protected SplayNode(T contents) {
        this.contents = contents;
    }

    protected void setLeft(SplayNode child) {
        left = child;
        if (child != null) {
            child.parent = this;
        }
    }

    protected void setRight(SplayNode child) {
        right = child;
        if (child != null) {
            child.parent = this;
        }
    }

    protected SplayNode<K,T> parent() {
        return parent;
    }

    protected SplayNode<K,T> left() {
        return left;
    }

    protected SplayNode<K,T> right() {
        return right;
    }

    protected boolean isRoot() {
        return parent == null;
    }

    protected boolean hasLeft() {
        return left != null;
    }

    protected boolean hasRight() {
        return right != null;
    }

    public boolean isLeft(SplayNode<K,T> toTop) {
        if(hasLeft()){
           if(left.contents.getKey().compareTo(toTop.contents.getKey())==0){
                return true;
            }
        }
        return false;
    }

    public boolean isRight(SplayNode<K,T> toTop) {
        if(hasRight()){
            if(right.contents.getKey().compareTo(toTop.contents.getKey())==0){
                return true;
            }
        }
        return false;
    }
}
