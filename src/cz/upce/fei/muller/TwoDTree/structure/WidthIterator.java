package cz.upce.fei.muller.TwoDTree.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Vojtěch Müller
 */
class WidthIterator<T extends AbstractStructureElement & ICoordinate> implements Iterator<ExtendData<T>> {
    private LinkedList<IteratorHelper> fifo = new LinkedList<>();

    private IteratorHelper temp = null;

    WidthIterator(Node<T> root,boolean isX){
        temp=null;
        if (root==null)return;
        fifo.add(new IteratorHelper(root,isX?1:2));
    }

    @Override
    public boolean hasNext() {
        return !fifo.isEmpty();
    }

    @Override
    public ExtendData<T> next() {
        if (fifo.isEmpty()) {
            return null;
        }
        temp = fifo.removeFirst();
        if (temp.node.hasLeft()) {
            fifo.add(new IteratorHelper(temp.node.left,temp.depth+1));
        }
        if (temp.node.hasRight()) {
            fifo.add(new IteratorHelper(temp.node.right,temp.depth+1));
        }
        ExtendData.Dimension dimension = temp.depth%2==0? ExtendData.Dimension.DIMENSION_Y: ExtendData.Dimension.DIMENSION_X;
        boolean isLeaf = !temp.node.hasRight() && !temp.node.hasLeft();
        return new ExtendData<>(temp.node,dimension,isLeaf);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class IteratorHelper {
        Node<T> node;
        int depth;

        private IteratorHelper(Node<T> node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

}