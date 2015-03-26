package cz.upce.fei.muller.TwoDTree.structure;

import java.io.Serializable;
import java.util.*;

/**
 * @author Vojtěch Müller
 */
public class TwoDTree<T extends ICoordinate> implements Iterable<T>, ITwoDTree {

    private Node<T> actual;
    private Node<T> root;
    private int count;

    public TwoDTree() {
        root = actual = null;
        count = 0;
    }

    void obnov() {
        ArrayList<T> prvky = new ArrayList<>();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            prvky.add(it.next());
        }
        vytvor(prvky);
    }

    @Override
    public Iterator<T> iterator() {
        return new MujIterator();
    }

    public List<T> najdi(int x1, int y1, int x2, int y2) {
        if (count == 0) return null;
        List<T> pom = new ArrayList<>();
        pom.addAll(najdiR(x1, y1, x2, y2, root, true));
        if (pom == null) return null;
        return pom;
    }

    private List<T> najdiR(int x1, int y1, int x2, int y2, Node<T> pr, boolean podleX) {
        ArrayList<T> pomm = new ArrayList<>();
        if (x1 <= pr.value.getX() && x2 >= pr.value.getX() && y1 <= pr.value.getY() && y2 >= pr.value.getY()) {

            pomm.add(pr.value);
        }
        boolean doprava, doleva;
        doprava = doleva = false;
        if (podleX) {
            doprava = pr.value.getX() < x2;
            doleva = pr.value.getX() > x1;
        } else {
            doprava = pr.value.getY() < y2;
            doleva = pr.value.getY() > y1;
        }
        if (pr.right != null && doprava) {
            pomm.addAll(najdiR(x1, y1, x2, y2, pr.right, !podleX));
        }
        if (pr.left != null && doleva) {
            pomm.addAll(najdiR(x1, y1, x2, y2, pr.left, !podleX));
        }
        return pomm;
    }

    private class MujIterator implements Iterator<T>, Serializable {

        private LinkedList<Node> fifo = new LinkedList<>();
        private Node<T> pom;

        public MujIterator() {
            pom = null;
            if (root == null) return;
            fifo.add(root);
        }

        @Override
        public boolean hasNext() {
            return !fifo.isEmpty();
        }

        @Override
        public T next() {
            if (fifo.isEmpty()) {
                return null;
            }
            pom = fifo.removeFirst();
            if (pom.right != null) {
                fifo.add(pom.right);
            }
            if (pom.left != null) {
                fifo.add(pom.left);
            }
            return pom.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public void pridej(T co) {
        ArrayList<T> prvky = new ArrayList<>();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            prvky.add(it.next());
        }
        prvky.add(co);
        vytvor(prvky);
    }

    public T odeber(T co) {
        ArrayList<T> prvky = new ArrayList<>();
        Iterator<T> it = iterator();
        T pom = null;
        T zpet = null;
        while (it.hasNext()) {
            pom = it.next();
            if (!pom.equals(co)) {
                prvky.add(pom);
            } else {
                zpet = pom;
            }
        }
        vytvor(prvky);
        return pom;
    }

    public T najdi(int x, int y) {
        boolean rovina = true;
        Node<T> pom = root;
        boolean doprava;
        while (true) {
            if (pom.value.getX() == x && pom.value.getY() == y) {
                return pom.value;
            }
            doprava = rovina ? pom.value.getX() < x : pom.value.getY() < y;
            if (pom.right != null && doprava == true) {
                pom = pom.right;
                rovina = !rovina;
                continue;
            }
            if (pom.left != null && doprava == false) {
                pom = pom.left;
                rovina = !rovina;
                continue;
            }
            return null;
        }
    }

    public void vytvor(ArrayList<T> prvky) {
        count = prvky.size();
        if (prvky.size() == 1) {
            root = new Node(prvky.get(0));
            actual = root;
            return;
        }
        Collections.sort(prvky, new ComparatorX());
        int median = prvky.size() / 2;
        List<T> prave = prvky.subList(median + 1, prvky.size());
        List<T> leve = prvky.subList(0, median);
        root = new Node(prvky.get(median));
        actual = root;
        if (leve.size() != 0) {
            root.left = vytvorRekurzivne(leve, false);
        } else {
            root.left = null;
        }
        if (!prave.isEmpty()) {
            root.right = vytvorRekurzivne(prave, false);
        } else {
            root.right = null;
        }
    }

    private Node vytvorRekurzivne(List<T> prvky, boolean podeleX) {
        if (prvky.size() == 1) return new Node(prvky.get(0));
        Collections.sort(prvky, podeleX ? new ComparatorX() : new ComparatorY());
        int median = prvky.size() / 2;
        List<T> prave = prvky.subList(median + 1, prvky.size());
        List<T> leve = prvky.subList(0, median);
        Node pom = new Node(prvky.get(median));
        if (!leve.isEmpty()) {
            pom.left = vytvorRekurzivne(leve, !podeleX);
        } else {
            pom.left = null;
        }
        if (!prave.isEmpty()) {
            pom.right = vytvorRekurzivne(prave, !podeleX);
        } else {
            pom.right = null;
        }
        return pom;
    }

    @Override
    public T getRoot() {
        actual = root;
        return root.value;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public T getRight() {
        if (actual.right == null) {
            return null;
        }
        actual = actual.right;
        return actual.value;
    }

    @Override
    public T getLeft() {
        if (actual.left == null) {
            return null;
        }
        actual = actual.left;
        return actual.value;
    }

}
