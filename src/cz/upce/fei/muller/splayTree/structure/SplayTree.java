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
            Match m = findMatch(content);
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

    private void splay(SplayNode toTop) {
        if (toTop == null) {
            return;
        }
        while (!toTop.isRoot()) {
            SplayNode parent = toTop.parent();

            if (parent.isRoot()) {
                if (parent.isLeft(toTop)) { //cik
                    rotationRight(toTop);
                } else { //cak
                    rotationLeft(toTop);
                }
            } else {
                SplayNode grandparent = parent.parent();
                boolean isCikCikLeft = grandparent.isLeft(parent) && parent.isLeft(toTop);
                boolean isCikCikRight = grandparent.isRight(parent) && parent.isRight(toTop);
                if (isCikCikRight || isCikCikRight) {
                    if (isCikCikLeft) {
                        rotationRight(parent);
                        rotationRight(toTop);
                    } else {
                        rotationLeft(parent);
                        rotationLeft(toTop);
                    }
                } else {
                    //cik-cak
                    if (parent.isLeft(toTop)) {
                        rotationRight(toTop);
                        rotationLeft(toTop);

                    } else {
                        rotationLeft(toTop);
                        rotationRight(toTop);
                    }
                }
            }
        }
    }

    private void rotationLeft(SplayNode toTop) {
        //TODO
    }

    private void rotationRight(SplayNode toTop) {
        //TODO
    }

    private Match findMatch(T a) {
        if (isEmpty()) {
            return new Match(false, root, true);
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
        return new Match(matchFound, n, smallerThanNode);

    }

    void generateLastEvent() {
        eventBus.post(new LastActionEvent());
    }

}
