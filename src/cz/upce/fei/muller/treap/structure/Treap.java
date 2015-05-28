package cz.upce.fei.muller.treap.structure;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.treap.events.*;

/**
 * @author Vojtěch Müller
 */
public class Treap<K extends Comparable<K>, T extends AbstractStructureElement & IPriorityKeyContainer<K>> implements ITreap<K, T> {

    private final EventBus eventBus;
    private TreapNode<K, T> root;
    private TreapNode<K, T> actual;
    private Integer count;

    public Treap(EventBus eventBus) {
        this.eventBus = eventBus;
        clear();
    }

    @Override
    public void insert(T inserted) {
        if (isEmpty()) {
            root = buildNewNode(inserted, null);
            generateNewNodeEvent(root, null, false);
            actual = root;
            generateLastEvent();
            return;
        }
        TreapNode<K, T> insertedNode = buildNewNode(inserted, null);
        actual = root;
        boolean isLeft;

        while (true) {
            int compare = insertedNode.compareKey(actual);
            eventBus.post(new MoveToChildEvent(inserted, actual.key));
            if (compare == 0) {
                System.out.println("ELEMENT EXIST NOT INSERTED");
                eventBus.post(new ElementKeyExistEvent(inserted));
                break;
            } else if (compare < 0) {
                isLeft = true;
                if (actual.hasLeft()) {
                    actual = actual.left;
                } else {
                    actual.left = insertedNode;
                    insertedNode.parent = actual;
                    generateNewNodeEvent(insertedNode, actual, isLeft);
                    actual = insertedNode;
                    alignTop();
                    break;
                }
            } else {
                isLeft = false;
                if (actual.hasRight()) {
                    actual = actual.right;
                } else {
                    actual.right = insertedNode;
                    insertedNode.parent = actual;
                    generateNewNodeEvent(insertedNode, actual, isLeft);
                    actual = insertedNode;
                    alignTop();
                    break;
                }
            }
        }
        generateLastEvent();
    }

    private void generateLastEvent() {
        eventBus.post(new LastActionEvent());
    }

    private void generateNewNodeEvent(TreapNode<K, T> newNode, TreapNode<K, T> parent, boolean isLeftChild) {
        eventBus.post(parent == null ? new CreateRootEvent(newNode.key) : new InsertNodeEvent(newNode.key, parent.key, isLeftChild));
    }

    @Override
    public T find(K element) {
        if (isEmpty()) {
            return null;
        }
        actual = root;
        T result = null;
        eventBus.post(new StartFindingEvent(element));
        while (true) {
            eventBus.post(new FindEvent(actual.key));
            int compare = actual.key.getKey().compareTo(element);
            if (compare == 0) {
                result = actual.key;
            } else if (compare > 0) {
                if (actual.hasLeft()) {
                    actual = actual.left;
                    continue;
                }
            } else {
                if (actual.hasRight()) {
                    actual = actual.right;
                    continue;
                }
            }
            break;
        }
        eventBus.post(new ElementFindEndEvent(result));
        generateLastEvent();
        return result;
    }

    @Override
    public T remove(K element) {
        if (isEmpty()) {
            return null;
        }
        T returnValue;
        eventBus.post(new StartRemoving());
        if (count == 1) {
            returnValue = root.key;
            clear();
            eventBus.post(new RemoveElementEvent(returnValue));
            generateLastEvent();
            return returnValue;
        }
        returnValue = find(element);
        if (returnValue == null) {
            return null;
        }

        TreapNode<K, T> toRemove = actual;

        RemoveHelper removeHelper = alignBottom(actual);

        if (RemoveHelper.SWAP_RIGHT_TO_LEAF.equals(removeHelper)) {
            swapToLeaf(toRemove, false);
        } else if (RemoveHelper.SWAP_LEFT_TO_LEAF.equals(removeHelper)) {
            swapToLeaf(toRemove, true);
        }

        removeLeaf(toRemove);
        count--;
        actual = root;

        eventBus.post(new RemoveElementEvent(returnValue));
        generateLastEvent();
        return returnValue;

    }

    private void swapToLeaf(TreapNode<K, T> removed, boolean isLeft) {
        TreapNode<K, T> temp = isLeft ? removed.left : removed.right;
        eventBus.post(new SwapNodeEvent(removed.key, temp.key));
        if (!removed.isRoot()) {
            if (removed.parent.hasRight() && removed.parent.right.equals(removed)) {
                removed.parent.right = temp;
            } else {
                removed.parent.left = temp;
            }
        }
        temp.parent = removed.parent;
        if (isLeft) {
            temp.right = removed.right;
            temp.left = removed;
        } else {
            temp.left = removed.left;
            temp.right = removed;
        }
        removed.parent = temp;
        removed.left = null;
        removed.right = null;
    }

    private void removeLeaf(TreapNode<K, T> tempNode) {
        if (!tempNode.isRoot()) {
            if (tempNode.parent.hasLeft() && tempNode.parent.left.equals(tempNode)) {
                tempNode.parent.left = null;
            } else {
                tempNode.parent.right = null;
            }
        }
        actual = tempNode.parent;
        count--;
    }

    @Override
    public void clear() {
        count = 0;
        actual = null;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private void alignTop() {
        while (true) {
            if (actual.isRoot()) {
                break;
            }
            if (actual.key.getPriority().compareTo(actual.parent.key.getPriority()) > 0) {
                if (actual.parent.hasLeft() && actual.parent.left.equals(actual)) {
                    rotationRight(actual);
                } else {
                    rotationLeft(actual);
                }
                continue;
            }
            break;
        }
    }

    private RemoveHelper alignBottom(TreapNode<K, T> p) {
        while (true) {

            if (p.isLeaf()) {
                return RemoveHelper.REMOVE_LEAF;
            }

            //childs is leaf
            if (p.hasLeft() && p.left.isLeaf() && p.hasRight() && p.right.isLeaf()) {
                // jestli prohodim s vetsi prioritou ...
                if (p.left.comparePriority(p.right) > 0) {
                    return RemoveHelper.SWAP_LEFT_TO_LEAF;
                } else {
                    return RemoveHelper.SWAP_RIGHT_TO_LEAF;
                }
            }

            if (!p.hasLeft() && p.hasRight() && p.right.isLeaf()) { // is only right child and is leaf
                return RemoveHelper.SWAP_RIGHT_TO_LEAF;
            }

            if (!p.hasRight() && p.hasLeft() && p.left.isLeaf()) {// its only left child and is leaf
                return RemoveHelper.SWAP_LEFT_TO_LEAF;
            }

            if (!p.hasLeft()) {
                rotationLeft(p.right);
                continue;
            }

            if (!p.hasRight()) {
                rotationRight(p.left);
                continue;
            }

            if (p.left.comparePriority(p.right) > 0) {
                rotationRight(p.left);
            } else {
                rotationLeft(p.right);
            }
        }
    }

    private void rotationLeft(TreapNode<K, T> rotatedNode) {
        System.out.println("ROTACE LEVA");
        RotationEvent event = new RotationEvent(true);

        TreapNode<K, T> parentRotatedNode = rotatedNode.parent;
        //change parent for rotated element
        rotatedNode.parent = parentRotatedNode.parent;
        boolean isLeft = true; // root is always left, when have parent is controling...
        if (!rotatedNode.isRoot()) {
            ReferenceHelper parentReference = new ReferenceHelper(rotatedNode.parent.key.getId());
            if (rotatedNode.parent.hasLeft() && rotatedNode.parent.left.equals(parentRotatedNode)) {
                parentReference.setOldReference(rotatedNode.parent.left.key.getId());
                rotatedNode.parent.left = rotatedNode;
                parentReference.setLeftNodePosition(true);
            } else {
                isLeft = false;
                parentReference.setOldReference(rotatedNode.parent.right.key.getId());
                rotatedNode.parent.right = rotatedNode;
            }
            parentReference.setNewReference(rotatedNode.key.getId());
            event.addReferenceHelper(parentReference);
        }
        ReferenceHelper left = new ReferenceHelper(rotatedNode.key.getId());
        ReferenceHelper right = new ReferenceHelper(parentRotatedNode.key.getId());

        right.setOldReference(parentRotatedNode.hasRight() ? parentRotatedNode.right.key.getId() : null);
        left.setOldReference(rotatedNode.hasLeft() ? rotatedNode.left.key.getId() : null);
        left.setLeftNodePosition(true);

        //default change
        parentRotatedNode.right = rotatedNode.left;
        rotatedNode.left = parentRotatedNode;
        parentRotatedNode.parent = rotatedNode;

        left.setNewReference(rotatedNode.hasLeft() ? rotatedNode.left.key.getId() : null);
        right.setNewReference(parentRotatedNode.hasRight() ? parentRotatedNode.right.key.getId() : null);

        event.addReferenceHelper(left);
        event.addReferenceHelper(right);
        if (parentRotatedNode.hasRight()) {
            parentRotatedNode.right.parent = parentRotatedNode;
        }
        if (rotatedNode.isRoot()) {
            root = rotatedNode;
        }
        System.out.println("\n\n TEST ");
        for (ITreeStructure structure : new TreeStructureBuilder<>(rotatedNode, isLeft).getRoot()) {
            System.out.println(structure);
        }

        event.setTreeRestructure(new TreeStructureBuilder<>(rotatedNode, isLeft).getRoot());
        eventBus.post(event);
    }

    private void rotationRight(TreapNode<K, T> rotatedNode) {
        System.out.println("ROTACE PRAVA");
        RotationEvent event = new RotationEvent(false);

        TreapNode<K, T> parentRotatedNode = rotatedNode.parent;
        rotatedNode.parent = rotatedNode.parent.parent;
        boolean isLeft = true; // root is always left, when have parent is controling...
        if (!rotatedNode.isRoot()) {
            ReferenceHelper parentReference = new ReferenceHelper(rotatedNode.parent.key.getId());
            if (rotatedNode.parent.hasLeft() && rotatedNode.parent.left.equals(parentRotatedNode)) {
                parentReference.setOldReference(rotatedNode.parent.left.key.getId());
                rotatedNode.parent.left = rotatedNode;
                parentReference.setLeftNodePosition(true);
            } else {
                isLeft = false;
                parentReference.setOldReference(rotatedNode.parent.right.key.getId());
                rotatedNode.parent.right = rotatedNode;
            }
            parentReference.setNewReference(rotatedNode.key.getId());
            event.addReferenceHelper(parentReference);
        }

        ReferenceHelper left = new ReferenceHelper(parentRotatedNode.key.getId());
        ReferenceHelper right = new ReferenceHelper(rotatedNode.key.getId());
        left.setLeftNodePosition(true);

        left.setOldReference(parentRotatedNode.hasLeft() ? parentRotatedNode.left.key.getId() : null);
        right.setOldReference(rotatedNode.hasRight() ? rotatedNode.right.key.getId() : null);

        //default change
        parentRotatedNode.left = rotatedNode.right;
        rotatedNode.right = parentRotatedNode;
        parentRotatedNode.parent = rotatedNode;

        left.setNewReference(parentRotatedNode.hasLeft() ? parentRotatedNode.left.key.getId() : null);
        right.setNewReference(rotatedNode.hasRight() ? rotatedNode.right.key.getId() : null);

        event.addReferenceHelper(left);
        event.addReferenceHelper(right);

        if (parentRotatedNode.hasLeft()) {
            parentRotatedNode.left.parent = parentRotatedNode;
        }
        if (rotatedNode.isRoot()) {
            root = rotatedNode;
        }
        System.out.println("\n\n TEST ");
        for (ITreeStructure structure : new TreeStructureBuilder<>(rotatedNode, isLeft).getRoot()) {
            System.out.println(structure);
        }

        event.setTreeRestructure(new TreeStructureBuilder<>(rotatedNode, isLeft).getRoot());
        eventBus.post(event);
    }

    private TreapNode<K, T> buildNewNode(T insertedValue, TreapNode<K, T> parent) {
        TreapNode<K, T> node = new TreapNode<>(insertedValue, parent);
        count++;
        return node;
    }

    /**
     * DEBUG METHOD
     */
    public void printTree() {
        //Debuging printing
        WidthIterator iterator = new WidthIterator(root, false);
        boolean first = true;
        for (; iterator.hasNext(); ) {
            ExtendData<K, T> data = iterator.next();
            System.out.println(String.format("id: %s , parent: %s , isLeft: %s", data.node.key.getId(), data.node.isRoot() ? null : data.node.parent.key.getId(), data.isLeft));
        }
    }


    public void setAcutalRoot() {
        actual = root;
    }

    /**
     * DEBUG METHOD
     */
    public boolean isLeft() {
        if (actual.isRoot()) return true;
        return actual == actual.left;
    }

    /**
     * DEBUG METHOD
     */
    public void rotateLeft() {
        rotationLeft(actual);
    }

    /**
     * DEBUG METHOD
     */
    public ITreeStructure getFromActual() {
        //build tree structu re...
        System.out.println("Actual node is: " + actual.key.getId());
        return new TreeStructureBuilder<>(actual, isLeft()).getRoot();
    }

    /**
     * DEBUG METHOD
     */
    public void rotateRight() {
        rotationRight(actual);
    }

    private enum RemoveHelper {
        REMOVE_LEAF, SWAP_LEFT_TO_LEAF, SWAP_RIGHT_TO_LEAF
    }
}

