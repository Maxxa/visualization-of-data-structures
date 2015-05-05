package cz.upce.fei.muller.treap.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public class Treap<K extends Comparable<K>,T extends AbstractStructureElement & IPriorityKeyContainer<K>> implements ITreap<K,T> {

    private final EventBus eventBus;
    private TreapNode<K,T> root;
    private TreapNode<K,T> actual;
    private Integer count;

    public Treap(EventBus eventBus) {
        this.eventBus = eventBus;
        clear();
    }

    @Override
    public void insert(T inserted) {
        if (isEmpty()) {
            root = new TreapNode<>(inserted, null);
            //TODO create root... event
            return;
        }
//        TreapNode<T> p = root;
//        TreapNode<T> insertedNode = new TreapNode<>(inserted, null);
//        count++;
        do {
//            //TODO prochazeni podstromu a hledani mista...
//            if (p.key.getComparator().compare(p.key, inserted) > 0) {
//                if (p.hasLeft()) {
//                    p = p.left;
//                } else {
//                    p.left = insertedNode;
//                    insertedNode.parent = p;
//                    actual = insertedNode;
//                    zarovnatNahoru();
//                    break;
//                }
//            } else {
//                if (p.hasRight()) {
//                    p = p.right;
//                } else {
//                    p.right = insertedNode;
//                    insertedNode.parent = p;
//                    actual = insertedNode;
//                    zarovnatNahoru();
//                    break;
//                }
//            }
        } while (true);

        //TODO generate last event...
    }

    @Override
    public void find(K element) {

    }

    @Override
    public void remove(K element) {

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

    private void zarovnatNahoru() {
        TreapNode<K,T> p = actual;
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

    private void zarovnatDolu(TreapNode<K,T> p) {
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

    private void rotaceL(TreapNode<K,T> p) {
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

    private void rotaceP(TreapNode<K,T> p) {
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

}

