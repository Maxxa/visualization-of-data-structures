/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.muller.binaryHeap.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.structures.BinaryTreeToArray;
import cz.upce.fei.common.structures.IBinaryTree;
import cz.upce.fei.common.structures.IBinaryTreeToArray;
import cz.upce.fei.common.structures.IHeap;
import cz.upce.fei.muller.binaryHeap.events.*;

import java.util.Comparator;

/**
 *
 * @author Max-xa
 */
public class BinaryHeap implements IHeap<HeapNode> {

    private final EventBus eventBus;
    private final HeapCompare comp;

    private final IBinaryTreeToArray<HeapNode> binTree;

    public BinaryHeap(EventBus eventBus, HeapType type) {
        this.eventBus = eventBus;
        this.comp = new HeapCompare(type);
        binTree = new BinaryTreeToArray<>();
    }

    @Override
    public void clear() {
        binTree.clear();
    }

    public boolean isEmpty() {
        return binTree.isEmpty();
    }

    @Override
    public void insert(HeapNode insertedValue) {
        if (isEmpty()) {
            binTree.insertRoot(insertedValue);
            eventBus.post(new CreateRoot(insertedValue));
        } else {
            int countItems = binTree.countItems();
            int parentIndex = (countItems - 1) / 2;
            boolean isLeftChild = false;
            if (countItems % 2 == 1) {// left child
                binTree.insertLeftChild(parentIndex, insertedValue);
                isLeftChild = true;
            } else {// right child
                binTree.insertRightChild(parentIndex, insertedValue);
            }

            countItems = binTree.countItems();
            int iChild = countItems - 1; // new child position
            eventBus.post(new InsertNode(insertedValue,binTree.getParent(iChild),isLeftChild));
            while (true) {
                HeapNode parent =binTree.getParent(iChild);
                boolean isSwap = comp.compare(parent,insertedValue) && iChild>0;
                eventBus.post(new CompareNode(parent,insertedValue,isSwap));
                if (isSwap){
                    binTree.swapNode(iChild, (iChild - 1) / 2);
                    iChild = (iChild - 1) / 2;
                    eventBus.post(new SwapNode(parent,insertedValue));
                } else {
                    break;
                }
            }
        }
        eventBus.post(new EndEvent(insertedValue));
    }

    @Override
    public HeapNode removeRoot() {
        eventBus.post(new SwapNode(binTree.getRoot(),binTree.getLast()));
        binTree.swapNode(0, binTree.countItems() - 1);
        HeapNode oldRoot= binTree.removeLast();
        eventBus.post(new RemoveRoot(oldRoot));

        //sprave serazeni 
        int parentIdx = 0;
        int childIdx;
        HeapNode child,currentNode = binTree.getRoot();
        while (true) {
            HeapNode leftChild = binTree.getLeft(parentIdx);
            HeapNode rightChild = binTree.getRight(parentIdx);

            //Porovnani synu, abych zjistil jakym směreme půjdu
            int test = comp.compareTo(leftChild,rightChild);

            if (test == 1) { //left
                childIdx = 2 * parentIdx + 1;
                child = leftChild;
            } else if (test == -1) { //right
                childIdx = 2 * parentIdx + 2;
                child=rightChild;
            } else {//jedno s kterym prvkem otestuji
                childIdx = 2 * parentIdx + 1;
                child = leftChild;
            }


            //porovnani se synem..
            boolean isSwap = comp.compare(currentNode, child);
            eventBus.post(new CompareNode(currentNode,child,isSwap));
            if (isSwap) {
                binTree.swapNode(parentIdx, childIdx);
                eventBus.post(new SwapNode(currentNode,child));
            } else {
                break;
            }
            parentIdx = childIdx;
        }

        eventBus.post(new EndEvent(oldRoot));
        return oldRoot;
    }

    @Override
    public boolean isHeap(Comparator com, IBinaryTree tree) {
        Object pSyn;
        Object lSyn;
        int i, j;
        for (int otec = 0; otec < tree.countItems(); otec++) {
            if (tree.getParent(2 * otec + 1) == null) { //
                return false;
            }

            pSyn = tree.getRight(2 * otec + 2);
            lSyn = tree.getLeft(2 * otec + 1);

           
            i = com.compare(lSyn, tree.getParent(2 * otec + 1));
            j = com.compare(pSyn, tree.getParent(2 * otec + 1));
            if (i == 1 || j == 1) {
                return false;
            }
        }
        return true;
    }

}
