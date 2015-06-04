package cz.upce.fei.muller.splayTree.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.ReferenceHelper;
import cz.upce.fei.common.events.RotationEvent;
import cz.upce.fei.muller.splayTree.events.*;

/**
 * @author Vojtěch Müller
 */
public class SplayTree<K extends Comparable<K>, T extends AbstractStructureElement & ISplayData<K>> implements ISplayTree<K, T> {

    private final EventBus eventBus;

    private SplayNode<K, T> root;

    public SplayTree(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void insert(T content) {
        if (content == null) return;

        SplayNode<K, T> newNode = new SplayNode<>(content);

        if (isEmpty()) {
            root = newNode;
            eventBus.post(new CreateRootEvent(content));
        } else {
            //finding node
            eventBus.post(new StartInserting(content));
            Match<K, T> m = findMatch(content.getKey());
            splay(m.node);      // splay node to root.
            if (m.matchFound) { // The element already exists in the tree.
                m.node.contents = content;
                eventBus.post(new ElementKeyExistEvent(m.node.contents));
            } else {
                SplayNode parent = m.node;

                if (m.smallerThanNode) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                eventBus.post(new InsertNodeEvent(newNode.contents, parent.contents, m.smallerThanNode));

            }
        }
        generateLastEvent();
    }

    @Override
    public T find(K key) {
        if (key == null) return null;

        eventBus.post(new StartFindEvent(key));
        Match<K, T> m = findMatch(key);

        eventBus.post(new ElementFindEndEvent(m.node.contents, m.matchFound));
        splay(m.node);
        generateLastEvent();
        return m.node.contents;
    }

    @Override
    public T delete(K key) {
        if (key == null) return null;

        T returnValue = null;
        eventBus.post(new StartRemoving());
        find(key); //TODO this is not finaly
        if (root.contents.getKey().compareTo(key) == 0) {
            returnValue=root.contents;
            SplayNode<K,T> left = root.left;
            SplayNode<K,T> right = root.right;
            removeRoot();
            unificationSubTree(left, right);
        }

        generateLastEvent();
        return returnValue;
    }

    // remove links from root
    private void removeRoot() {
        if(root.hasLeft()){
            root.left.parent=null;
        }

        if(root.hasRight()){
            root.right.parent=null;
        }

        root.right=null;
        root.left=null;
    }

    private void unificationSubTree(SplayNode<K, T> left, SplayNode<K, T> right) {
        if(left ==null && right ==null){
            eventBus.post(new RemoveLastElementEvent());
            root=null;
            return;
        }

        if(left==null){
            //root is right root
            root= right;
        }else{
            SplayNode<K,T> newRoot= findMax(left);
            root=newRoot;
            newRoot.right=right;
        }

    }

    private SplayNode<K, T> findMax(SplayNode<K, T> left) {
        SplayNode<K,T> n =left;
        if (n == null) {
            return n;
        }
        while (n.hasRight()) {
            n = n.right();
        }
        splay(n);
        return n;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private void splay(SplayNode<K, T> toTop) {
        if (toTop == null) {
            return;
        }
        while (!toTop.isRoot()) {
            SplayNode<K, T> parent = toTop.parent();

            SplayOperationEvent.SplayOperation splayOperation;
            if (parent.isRoot()) {
                if (parent.isLeft(toTop)) {
                    splayOperation = SplayOperationEvent.SplayOperation.ZIG_LEFT;
                    rotationRight(toTop);
                } else {
                    splayOperation = SplayOperationEvent.SplayOperation.ZIG_RIGHT;
                    rotationLeft(toTop);
                }
            } else {
                SplayNode<K, T> grandparent = parent.parent();
                boolean isCikCikLeft = grandparent.isLeft(parent) && parent.isLeft(toTop);
                boolean isCikCikRight = grandparent.isRight(parent) && parent.isRight(toTop);
                if (isCikCikLeft || isCikCikRight) {
                    if (isCikCikLeft) {
                        splayOperation = SplayOperationEvent.SplayOperation.ZIG_ZIG_LEFT;
                        rotationRight(parent);
                        rotationRight(toTop);
                    } else {
                        splayOperation = SplayOperationEvent.SplayOperation.ZIG_ZIG_RIGHT;
                        rotationLeft(parent);
                        rotationLeft(toTop);
                    }
                } else {
                    if (parent.isLeft(toTop)) {
                        splayOperation = SplayOperationEvent.SplayOperation.ZIG_ZAG_LEFT;
                        rotationRight(toTop);
                        rotationLeft(toTop);

                    } else {
                        splayOperation = SplayOperationEvent.SplayOperation.ZIG_ZAG_RIGTH;
                        rotationLeft(toTop);
                        rotationRight(toTop);
                    }
                }
            }
            root = toTop;
            eventBus.post(new SplayOperationEvent(splayOperation));
        }
    }

    private Match<K, T> findMatch(K a) {
        if (isEmpty()) {
            return new Match<>(false, root, true);
        }

        SplayNode<K, T> n = root;
        boolean matchFound = false;
        boolean smallerThanNode = false;
        while (true) {
            eventBus.post(new MoveToChildEvent(a, n.contents));
            int c = a.compareTo(n.contents.getKey());
            if (c == 0) {
                matchFound = true;
                break;
            } else if (c < 0) {
                if (!n.hasLeft()) {
                    smallerThanNode = true;
                    break;
                } else {
                    n = n.left();
                }
            } else {
                if (!n.hasRight()) {
                    break; //default is false for variable...
                } else {
                    n = n.right();
                }
            }
        }

        eventBus.post(new MatchFindEvent(n.contents));
        return new Match<>(matchFound, n, smallerThanNode);

    }

    private void rotationLeft(SplayNode<K, T> rotatedNode) {
        System.out.println("ROTACE LEVA");
        RotationEvent event = new RotationEvent(true);

        SplayNode<K, T> parentRotatedNode = rotatedNode.parent;
        //change parent for rotated element
        rotatedNode.parent = parentRotatedNode.parent;
        boolean isLeft = true; // root is always left, when have parent is controling...
        if (!rotatedNode.isRoot()) {
            ReferenceHelper parentReference = new ReferenceHelper(rotatedNode.parent.contents.getId());
            if (rotatedNode.parent.hasLeft() && rotatedNode.parent.left.equals(parentRotatedNode)) {
                parentReference.setOldReference(rotatedNode.parent.left.contents.getId());
                rotatedNode.parent.left = rotatedNode;
                parentReference.setLeftNodePosition(true);
            } else {
                isLeft = false;
                parentReference.setOldReference(rotatedNode.parent.right.contents.getId());
                rotatedNode.parent.right = rotatedNode;
            }
            parentReference.setNewReference(rotatedNode.contents.getId());
            event.addReferenceHelper(parentReference);
        }
        ReferenceHelper left = new ReferenceHelper(rotatedNode.contents.getId());
        ReferenceHelper right = new ReferenceHelper(parentRotatedNode.contents.getId());

        right.setOldReference(parentRotatedNode.hasRight() ? parentRotatedNode.right.contents.getId() : null);
        left.setOldReference(rotatedNode.hasLeft() ? rotatedNode.left.contents.getId() : null);
        left.setLeftNodePosition(true);

        //default change
        parentRotatedNode.right = rotatedNode.left;
        rotatedNode.left = parentRotatedNode;
        parentRotatedNode.parent = rotatedNode;

        left.setNewReference(rotatedNode.hasLeft() ? rotatedNode.left.contents.getId() : null);
        right.setNewReference(parentRotatedNode.hasRight() ? parentRotatedNode.right.contents.getId() : null);

        event.addReferenceHelper(left);
        event.addReferenceHelper(right);
        if (parentRotatedNode.hasRight()) {
            parentRotatedNode.right.parent = parentRotatedNode;
        }
        if (rotatedNode.isRoot()) {
            root = rotatedNode;
        }

        event.setTreeRestructure(new TreeStructureBuilder<>(rotatedNode, isLeft).getRoot());
        eventBus.post(event);
    }

    private void rotationRight(SplayNode<K, T> rotatedNode) {
        System.out.println("ROTACE PRAVA");
        RotationEvent event = new RotationEvent(false);

        SplayNode<K, T> parentRotatedNode = rotatedNode.parent;
        rotatedNode.parent = rotatedNode.parent.parent;
        boolean isLeft = true; // root is always left, when have parent is controling...
        if (!rotatedNode.isRoot()) {
            ReferenceHelper parentReference = new ReferenceHelper(rotatedNode.parent.contents.getId());
            if (rotatedNode.parent.hasLeft() && rotatedNode.parent.left.equals(parentRotatedNode)) {
                parentReference.setOldReference(rotatedNode.parent.left.contents.getId());
                rotatedNode.parent.left = rotatedNode;
                parentReference.setLeftNodePosition(true);
            } else {
                isLeft = false;
                parentReference.setOldReference(rotatedNode.parent.right.contents.getId());
                rotatedNode.parent.right = rotatedNode;
            }
            parentReference.setNewReference(rotatedNode.contents.getId());
            event.addReferenceHelper(parentReference);
        }

        ReferenceHelper left = new ReferenceHelper(parentRotatedNode.contents.getId());
        ReferenceHelper right = new ReferenceHelper(rotatedNode.contents.getId());
        left.setLeftNodePosition(true);

        left.setOldReference(parentRotatedNode.hasLeft() ? parentRotatedNode.left.contents.getId() : null);
        right.setOldReference(rotatedNode.hasRight() ? rotatedNode.right.contents.getId() : null);

        //default change
        parentRotatedNode.left = rotatedNode.right;
        rotatedNode.right = parentRotatedNode;
        parentRotatedNode.parent = rotatedNode;

        left.setNewReference(parentRotatedNode.hasLeft() ? parentRotatedNode.left.contents.getId() : null);
        right.setNewReference(rotatedNode.hasRight() ? rotatedNode.right.contents.getId() : null);

        event.addReferenceHelper(left);
        event.addReferenceHelper(right);

        if (parentRotatedNode.hasLeft()) {
            parentRotatedNode.left.parent = parentRotatedNode;
        }
        if (rotatedNode.isRoot()) {
            root = rotatedNode;
        }

        event.setTreeRestructure(new TreeStructureBuilder<>(rotatedNode, isLeft).getRoot());
        eventBus.post(event);
    }

    private void generateLastEvent() {
        eventBus.post(new LastActionEvent());
    }

}
