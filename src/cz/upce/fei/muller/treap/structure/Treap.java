package cz.upce.fei.muller.treap.structure;

import com.google.common.eventbus.EventBus;
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
            System.out.println("CREATE ROOT");
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
                System.out.println("LEFT");
                isLeft=true;
                if (actual.hasLeft()) {
                    actual = actual.left;
                } else {
                    actual.left = insertedNode;
                    insertedNode.parent = actual;
                    actual = insertedNode;
                    generateNewNodeEvent(insertedNode, actual, isLeft);
                    alignTop();
                }
                break;
            } else {
                System.out.println("RIGHT");
                isLeft=false;
                if (actual.hasRight()) {
                    actual = actual.right;
                } else {
                    actual.right = insertedNode;
                    insertedNode.parent = actual;
                    actual = insertedNode;
                    generateNewNodeEvent(insertedNode, actual, isLeft);
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

    private void generateNewNodeEvent(TreapNode<K,T> newNode, TreapNode<K,T> parent, boolean isLeftChild) {
        eventBus.post(parent == null ? new CreateRootEvent(newNode.key) : new InsertNodeEvent(newNode.key, parent.key, isLeftChild));
    }

    @Override
    public void find(K element) {
        //TODO
    }

    @Override
    public void remove(K element) {
        //TODO
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
        TreapNode<K, T> p = actual;
        while (true) {
            if (p.isRoot()) {
                return;
            }
//            if (p.key.getPriority().compareTo(p.parent.key.getPriority()) > 0) {
//                if (p.parent.hasLeft()) {
////                    if (p.parent.left.key.CompareTo(p.klic) == 0) {
////                        rotaceP(p);
////                    } else {
////                        rotaceL(p);
////                    }
//                } else {
//                    rotaceL(p);
//                }
//                continue;
//            }
            return;
        }
    }

    private void zarovnatDolu(TreapNode<K, T> p) {
        while (true) {
//            if (p.levy == null && p.pravy == null) {
//                return;
//            }
//            if (p.levy == null) {
//                if (p.priorita.CompareTo(p.pravy.priorita) < 0) {
//                    rotaceL(p.pravy);
//                    continue;
//                } else {
//                    return;
//                }
//            }
//            if (p.pravy == null) {
//                if (p.priorita.CompareTo(p.levy.priorita) < 0) {
//                    rotaceP(p.levy);
//                    continue;
//                } else {
//                    return;
//                }
//            }
//            if (p.levy.priorita.CompareTo(p.pravy.priorita) > 0) {
//                if (p.priorita.CompareTo(p.levy.priorita) < 0) {
//                    rotaceP(p.levy);
//                    continue;
//                }
//            } else {
//                if (p.priorita.CompareTo(p.pravy.priorita) < 0) {
//                    rotaceL(p.pravy);
//                    continue;
//                }
//            }
            return;
        }
    }

    private void rotaceL(TreapNode<K, T> p) {
//        PrvekStromu rodic = p.parent;
//        p.rodic = p.rodic.rodic;
//        if (p.rodic != null) {
//            if (p.rodic.levy != null) {
//                if (p.rodic.levy.klic.CompareTo(rodic.klic) == 0) {
//                    p.rodic.levy = p;
//                } else {
//                    p.rodic.pravy = p;
//                }
//            } else {
//                p.rodic.pravy = p;
//            }
//        }
//        rodic.pravy = p.levy;
//        p.levy = rodic;
//        rodic.rodic = p;
//        if (rodic.pravy != null) {
//            rodic.pravy.rodic = rodic;
//        }
//        if (p.rodic == null) {
//            koren = p;
//        }
    }

    private void rotaceP(TreapNode<K, T> p) {
//        PrvekStromu rodic = p.rodic;
//        p.rodic = p.rodic.rodic;
//        if (p.rodic != null) {
//            if (p.rodic.pravy != null) {
//                if (p.rodic.pravy.klic.CompareTo(rodic.klic) == 0) {
//                    p.rodic.pravy = p;
//                } else {
//                    p.rodic.levy = p;
//                }
//            } else {
//                p.rodic.levy = p;
//            }
//        }
//        rodic.levy = p.pravy;
//        p.pravy = rodic;
//        rodic.rodic = p;
//        if (rodic.levy != null) {
//            rodic.levy.rodic = rodic;
//        }
//        if (p.rodic == null) {
//            koren = p;
//        }
    }

    private TreapNode<K, T> buildNewNode(T insertedValue, TreapNode<K, T> parent) {
        TreapNode<K, T> node = new TreapNode<>(insertedValue, parent);
        count++;
        return node;
    }

}

