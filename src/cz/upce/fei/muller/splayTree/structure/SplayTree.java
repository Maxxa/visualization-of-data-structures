package cz.upce.fei.muller.splayTree.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.splayTree.events.CreateRootEvent;
import cz.upce.fei.muller.splayTree.events.ElementKeyExistEvent;
import cz.upce.fei.muller.splayTree.events.InsertNodeEvent;
import cz.upce.fei.muller.splayTree.events.LastActionEvent;

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
            Match m = findMatch(content);

            if (m.matchFound) { // The element already exists in the tree.
                m.node.contents = content;
                eventBus.post(new ElementKeyExistEvent(m.node.contents));
                splay(m.node);
            } else {
                SplayNode parent = m.node;

                if (m.smallerThanNode) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
                eventBus.post(new InsertNodeEvent(newNode.contents, parent.contents, m.smallerThanNode));
                splay(newNode);
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
                // Zig or zag.
//                rotateUp(toTop);
            } else {
                SplayNode grandparent = parent.parent();
                if (grandparent.left() == parent && parent.left() == toTop
                        ||
                    grandparent.right() == parent && parent.right() == toTop) {
                    // Zig-zig or zag-zag.
//                    rotateUp(parent);
//                    rotateUp(toTop);
                } else {
                    // Zig-zag or zag-zig.
//                    rotateUp(toTop);
//                    rotateUp(toTop);
                }
            }
        }
    }

    /**
     * Finds the SplayNode containing the given element, if any. <i>Does
     * not splay the tree.</i>
     */
    private Match findMatch(T a) {
        // The search starts in the root, not the sentinel.
        SplayNode<K, T> n = root;

        if (n == null) {
            return new Match(false, root, true);
        }

        while (true) {
            int c = a.getKey().compareTo(n.contents.getKey());
            if (c == 0) {
                return new Match(true, n, false);
            } else if (c < 0) {
                if (!n.hasLeft()) {
                    return new Match(false, n, true);
                } else {
                    n = n.left();
                }
            } else {
                if (!n.hasRight()) {
                    return new Match(false, n, false);
                } else {
                    n = n.right();
                }
            }
        }
    }

    void generateLastEvent() {
        eventBus.post(new LastActionEvent());
    }

}
