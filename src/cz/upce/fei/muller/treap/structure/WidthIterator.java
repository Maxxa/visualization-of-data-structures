package cz.upce.fei.muller.treap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Vojtěch Müller
 */
class WidthIterator<K extends Comparable<K>, T extends AbstractStructureElement & IPriorityKeyContainer<K>> implements Iterator<ExtendData<K,T>> {
    private LinkedList<IteratorHelper> fifo = new LinkedList<>();

    private IteratorHelper temp = null;

    WidthIterator(TreapNode<K,T> root,boolean isLeft){
        if (root==null)return;
        fifo.add(new IteratorHelper(root,isLeft));
    }

    @Override
    public boolean hasNext() {
        return !fifo.isEmpty();
    }

    @Override
    public ExtendData<K,T> next() {
        if (fifo.isEmpty()) {
            return null;
        }
        temp = fifo.removeFirst();
        if (temp.node.hasLeft()) {
            fifo.add(new IteratorHelper(temp.node.left,true));
        }
        if (temp.node.hasRight()) {
            fifo.add(new IteratorHelper(temp.node.right,false));
        }
        return new ExtendData<>(temp.node,temp.isLeft);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    private class IteratorHelper {
        TreapNode<K,T> node;
        boolean isLeft;

        private IteratorHelper(TreapNode<K,T> node, boolean isLeft) {
            this.node = node;
            this.isLeft = isLeft;
        }
    }

}