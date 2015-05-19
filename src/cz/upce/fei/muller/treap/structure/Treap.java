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

        TreapNode<K, T> naOdebrani = actual;

        TreapNode<K, T> pom = actual;
        if (!pom.hasLeft() && !pom.hasRight()) { //is leaf
            removeLeaf(pom);

            generateLastEvent();
            return pom.key;
        } else {

            alignBottom(pom);
        }

//        if (pom.hasLeft()) {
//            if (!pom.left.hasRight()) {
//                if (!pom.isRoot()) if (pom.parent.left == pom) {
//                    pom.parent.left = pom.left;
//                } else {
//                    pom.parent.right = pom.left;
//                }
//                pom.left.right = pom.right;
//                pom.left.parent = pom.parent;
//                if (pom.left.hasRight()) pom.left.right.parent = pom.left;
//                pom = pom.left;
//            } else {
//                pom = pom.left;
//                while (pom.hasRight()) {
//                    pom = pom.right;
//                }
//                pom.parent.right = pom.left;
//                if (pom.hasLeft()) pom.left.parent = pom.parent;
//                if (!naOdebrani.isRoot()) {
//                    if (naOdebrani.parent.left == naOdebrani) {
//                        naOdebrani.parent.left = pom;
//                    } else {
//                        naOdebrani.parent.right = pom;
//                    }
//                }
//                pom.right = naOdebrani.right;
//                pom.left = naOdebrani.left;
//                pom.parent = naOdebrani.parent;
//                if (pom.hasLeft()) pom.left.parent = pom;
//                if (pom.hasRight()) pom.right.parent = pom;
//            }
//        } else {
//            if (pom.right.hasLeft()) {
//                if (!pom.isRoot()) if (pom.parent.left == pom) {
//                    pom.parent.left = pom.right;
//                } else {
//                    pom.parent.right = pom.right;
//                }
//                pom.right.left = pom.left;
//                pom.right.parent = pom.parent;
//                if (pom.right.hasLeft()) pom.right.left.parent = pom.right;
//                pom = pom.right;
//            } else {
//                pom = pom.right;
//                while (pom.hasLeft()) {
//                    pom = pom.left;
//                }
//                pom.parent.left = pom.right;
//                if (pom.hasRight()) pom.right.parent = pom.parent;
//                if (!naOdebrani.isRoot()) {
//                    if (naOdebrani.parent.left == naOdebrani) {
//                        naOdebrani.parent.left = pom;
//                    } else {
//                        naOdebrani.parent.right = pom;
//                    }
//                }
//                pom.left = naOdebrani.left;
//                pom.right = naOdebrani.right;
//                pom.parent = naOdebrani.parent;
//                if (pom.hasLeft()) pom.left.parent = pom;
//                if (pom.hasRight()) pom.right.parent = pom;
//            }
//        }
        count--;
        actual = root;
        alignBottom(pom);
        eventBus.post(new RemoveElementEvent(returnValue));
        generateLastEvent();
        return returnValue;

    }

    private void removeLeaf(TreapNode<K, T> tempNode) {
        if (!tempNode.isRoot()) {
            if (tempNode.parent.left == tempNode) {
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
        //TODO testing after control is corect structure for rebuild...
//        while (true) {
//            if (actual.isRoot()) {
//                break;
//            }
//            if (actual.key.getPriority().compareTo(actual.parent.key.getPriority()) > 0) {
//                if (actual.parent.hasLeft() && actual.parent.left.equals(actual)) {
//                   rotationRight(actual);
//                } else {
//                    rotationLeft(actual);
//                }
//                continue;
//            }
//            break;
//        }
    }

    private void alignBottom(TreapNode<K, T> p) {
        while (true) {
            if (!p.hasLeft() && !p.hasRight()) {
                return;
            }
            if (!p.hasLeft()) {
//                if (p.priorita.CompareTo(p.right.priorita) < 0) {
//                    rotationLeft(p.right);
//                    continue;
//                } else {
//                    return;
//                }
            }
            if (!p.hasRight()) {
//                if (p.priorita.CompareTo(p.left.priorita) < 0) {
//                    rotationRight(p.left);
//                    continue;
//                } else {
//                    return;
//                }
            }
//            if (p.left.priorita.CompareTo(p.right.priorita) > 0) {
//                if (p.priorita.CompareTo(p.left.priorita) < 0) {
//                    rotationRight(p.left);
//                    continue;
//                }
//            } else {
//                if (p.priorita.CompareTo(p.right.priorita) < 0) {
//                    rotationLeft(p.right);
//                    continue;
//                }
//            }
            return;
        }
    }

    private void rotationLeft(TreapNode<K, T> rotatedNode) {
        System.out.println("ROTACE LEVA");
        RotationEvent event = new RotationEvent(true);

        TreapNode<K, T> parentRotatedNode = rotatedNode.parent;
        //change parent for rotated element
        rotatedNode.parent = parentRotatedNode.parent;
        boolean isLeftChild = true;
        if (!rotatedNode.isRoot()) {
            ReferenceHelper parentReference = new ReferenceHelper(rotatedNode.parent.key.getId());
            if (rotatedNode.parent.hasLeft() && rotatedNode.parent.left.equals(parentRotatedNode)) {
                parentReference.setOldReference(rotatedNode.parent.left.key.getId());
                rotatedNode.parent.left = rotatedNode;
                parentReference.setLeftNodePosition(true);
            } else {
                isLeftChild = false;
                parentReference.setOldReference(rotatedNode.parent.right.key.getId());
                rotatedNode.parent.right = rotatedNode;
            }
            parentReference.setNewReference(rotatedNode.key.getId());
            event.addReferenceHelper(parentReference);
        }
        ReferenceHelper left = new ReferenceHelper(parentRotatedNode.key.getId());
        ReferenceHelper right = new ReferenceHelper(rotatedNode.key.getId());
        right.setOldReference(parentRotatedNode.hasRight() ? parentRotatedNode.right.key.getId() : null);
        left.setOldReference(rotatedNode.hasLeft() ? rotatedNode.left.key.getId() : null);

        //default change
        parentRotatedNode.right = rotatedNode.left;
        rotatedNode.left = parentRotatedNode;
        parentRotatedNode.parent = rotatedNode;

        right.setNewReference(parentRotatedNode.hasRight() ? parentRotatedNode.right.key.getId() : null);
        left.setNewReference(rotatedNode.hasLeft() ? rotatedNode.left.key.getId() : null);

        event.addReferenceHelper(left);
        event.addReferenceHelper(right);
        if (parentRotatedNode.hasRight()) {
            parentRotatedNode.right.parent = parentRotatedNode;
        }
        if (rotatedNode.isRoot()) {
            root = rotatedNode;
        }
        System.out.println("\n\n TEST ");
        for (ITreeStructure structure:new TreeStructureBuilder<>(rotatedNode, isLeftChild).getRoot()){
            System.out.println(structure);
        }

        event.setTreeRestructure(new TreeStructureBuilder<>(rotatedNode, isLeftChild).getRoot());
        eventBus.post(event);
    }

    private void rotationRight(TreapNode<K, T> rotatedNode) {
        System.out.println("ROTACE PRAVA");
        RotationEvent event = new RotationEvent(false);

        TreapNode<K, T> parentRotatedNode = rotatedNode.parent;
        rotatedNode.parent = rotatedNode.parent.parent;
        boolean isLeftChild = true;
        if (!rotatedNode.isRoot()) {
            ReferenceHelper parentReference = new ReferenceHelper(rotatedNode.parent.key.getId());
            if (rotatedNode.parent.hasLeft() && rotatedNode.parent.left.equals(parentRotatedNode)) {
                parentReference.setOldReference(rotatedNode.parent.left.key.getId());
                rotatedNode.parent.left = rotatedNode;
                parentReference.setLeftNodePosition(true);
            } else {
                isLeftChild = false;
                parentReference.setOldReference(rotatedNode.parent.right.key.getId());
                rotatedNode.parent.right = rotatedNode;
            }
            parentReference.setNewReference(rotatedNode.key.getId());
            event.addReferenceHelper(parentReference);
        }

        ReferenceHelper left = new ReferenceHelper(parentRotatedNode.key.getId());
        ReferenceHelper right = new ReferenceHelper(rotatedNode.key.getId());

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
        for (ITreeStructure structure:new TreeStructureBuilder<>(rotatedNode, isLeftChild).getRoot()){
            System.out.println(structure);
        }

        event.setTreeRestructure(new TreeStructureBuilder<>(rotatedNode, isLeftChild).getRoot());
        eventBus.post(event);
    }

    private TreapNode<K, T> buildNewNode(T insertedValue, TreapNode<K, T> parent) {
        TreapNode<K, T> node = new TreapNode<>(insertedValue, parent);
        count++;
        return node;
    }


    /** DEBUG METHOD */
    public void printTree() {
        //Debuging printing
        WidthIterator iterator = new WidthIterator(root, false);
        boolean first = true;
        for (; iterator.hasNext(); ) {
            ExtendData<K, T> data = iterator.next();
            System.out.println(String.format("id: %s , parent: %s , isLeft: %s", data.node.key.getId(), data.node.isRoot() ? null : data.node.parent.key.getId(), data.isLeft));
        }
    }

    /** DEBUG METHOD */
    public boolean isLeft() {
        if (actual.isRoot()) return true;
        return actual == actual.left;
    }
    /** DEBUG METHOD */
    public void rotateLeft(){
        rotationLeft(actual);
    }

    /** DEBUG METHOD */
    public ITreeStructure getFromActual() {
        //build tree structu re...
        System.out.println("Actual node is: " + actual.key.getId());
        return new TreeStructureBuilder<>(actual, isLeft()).getRoot();
    }

    /** DEBUG METHOD */
    public void rotateRight(){
        rotationRight(actual);
    }

}

