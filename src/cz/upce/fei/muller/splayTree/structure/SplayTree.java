package cz.upce.fei.muller.splayTree.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
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
            Match<K,T> m = findMatch(content);
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
        return null; //TODO
    }

    @Override
    public T delete(K key) {
        return null; //TODO
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private void splay(SplayNode<K,T> toTop) {
        if (toTop == null) {
            return;
        }
        while (!toTop.isRoot()) {
            SplayNode<K,T> parent = toTop.parent();

            SplayOperationEvent.SplayOperation splayOperation;
            if (parent.isRoot()) {
                if (parent.isLeft(toTop)) {
                    splayOperation= SplayOperationEvent.SplayOperation.ZIG_LEFT;
                    rotationRight(toTop);
                } else {
                    splayOperation= SplayOperationEvent.SplayOperation.ZIG_RIGHT;
                    rotationLeft(toTop);
                }
            } else {
                SplayNode<K,T> grandparent = parent.parent();
                boolean isCikCikLeft = grandparent.isLeft(parent) && parent.isLeft(toTop);
                boolean isCikCikRight = grandparent.isRight(parent) && parent.isRight(toTop);
                if (isCikCikLeft || isCikCikRight) {
                    if (isCikCikLeft) {
                        splayOperation= SplayOperationEvent.SplayOperation.ZIG_ZIG_LEFT;
                        rotationRight(parent);
                        rotationRight(toTop);
                    } else {
                        splayOperation= SplayOperationEvent.SplayOperation.ZIG_ZIG_RIGHT;
                        rotationLeft(parent);
                        rotationLeft(toTop);
                    }
                } else {
                    if (parent.isLeft(toTop)) {
                        splayOperation= SplayOperationEvent.SplayOperation.ZIG_ZAG_LEFT;
                        rotationRight(toTop);
                        rotationLeft(toTop);

                    } else {
                        splayOperation= SplayOperationEvent.SplayOperation.ZIG_ZAG_RIGTH;
                        rotationLeft(toTop);
                        rotationRight(toTop);
                    }
                }
            }
            eventBus.post(new SplayOperationEvent(splayOperation));
        }
    }

    private void rotationLeft(SplayNode toTop) {
        //TODO
    }

    private void rotationRight(SplayNode toTop) {
        //TODO
    }

    private Match<K,T> findMatch(T a) {
        if (isEmpty()) {
            return new Match<>(false, root, true);
        }

        SplayNode<K, T> n = root;
        boolean matchFound = false;
        boolean smallerThanNode = false;
        while (true) {
            eventBus.post(new MoveToChildEvent(a, n.contents));
            int c = a.getKey().compareTo(n.contents.getKey());
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

    void generateLastEvent() {
        eventBus.post(new LastActionEvent());
    }

}
