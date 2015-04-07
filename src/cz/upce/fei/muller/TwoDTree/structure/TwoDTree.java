package cz.upce.fei.muller.TwoDTree.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.TwoDTree.events.CreateRootEvent;
import cz.upce.fei.muller.TwoDTree.events.InsertNodeEvent;
import cz.upce.fei.muller.TwoDTree.events.LastActionEvent;
import cz.upce.fei.muller.TwoDTree.events.MoveToChild;

import java.io.Serializable;
import java.util.*;

/**
 * @author Vojtěch Müller
 */
public class TwoDTree<T extends AbstractStructureElement & ICoordinate> implements Iterable<T>, ITwoDTree<T> {

    private final EventBus eventBus;
    private Node<T> actual;
    private Node<T> root;
    private int count;

    public TwoDTree(EventBus eventBus) {
        this.eventBus = eventBus;
        root = actual = null;
        count = 0;
    }

    @Override
    public void create(List<T> nodes) {
        count = nodes.size();
        if (nodes.size() == 1) {
            root = new Node(nodes.get(0));
            generateNewNodeEvent(root, null, false);
            actual = root;
            eventBus.post(new LastActionEvent());
            return;
        }
        Collections.sort(nodes, new ComparatorX());
        int median = nodes.size() / 2;
        List<T> rightNodes = nodes.subList(median + 1, nodes.size());
        List<T> leftNodes = nodes.subList(0, median);
        root = new Node(nodes.get(median));
        generateNewNodeEvent(root, null, false);
        actual = root;
        if (!leftNodes.isEmpty()) {
            root.left = createRecursive(leftNodes, root, true, false);
        } else {
            root.left = null;
        }
        if (!rightNodes.isEmpty()) {
            root.right = createRecursive(rightNodes, root, false, false);
        } else {
            root.right = null;
        }
        eventBus.post(new LastActionEvent());
    }

    private Node createRecursive(List<T> nodes, Node parent, Boolean leftSubTree, Boolean isSortingX) {
        Node tmp;
        if (nodes.size() == 1) {
            tmp= buildNewNode(nodes.get(0));
            generateNewNodeEvent(tmp, parent, leftSubTree);
            return tmp;
        }
        Collections.sort(nodes, isSortingX ? new ComparatorX() : new ComparatorY());
        int median = nodes.size() / 2;
        List<T> rightNodes = nodes.subList(median + 1, nodes.size());
        List<T> leftNodes = nodes.subList(0, median);
        tmp= buildNewNode(nodes.get(median));
        generateNewNodeEvent(tmp, parent, leftSubTree);
        if (!leftNodes.isEmpty()) {
            tmp.left = createRecursive(leftNodes, tmp, true, !isSortingX);
        } else {
            tmp.left = null;
        }
        if (!rightNodes.isEmpty()) {
            tmp.right = createRecursive(rightNodes, tmp, false, !isSortingX);
        } else {
            tmp.right = null;
        }
        return tmp;
    }

    private void generateNewNodeEvent(Node newNode, Node parent, boolean isLeftChild) {
        eventBus.post(parent == null ? new CreateRootEvent(newNode.value) : new InsertNodeEvent(newNode.value, parent.value, isLeftChild));
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

    @Override
    public void insert(T insertedValue) {
        if (isEmpty()) {
            root = buildNewNode(insertedValue);
            generateNewNodeEvent(root, null, false);
            actual = root;
            eventBus.post(new LastActionEvent());
            return;
        }
        actual = root;
        boolean compareX = true;
        boolean isLeft;
        Node newNode= buildNewNode(insertedValue);

        while (true) {
            int value = compareX ? actual.value.getX() : actual.value.getY();
            isLeft = (compareX ? insertedValue.getX() : insertedValue.getY()) < value;
            System.out.println("MOVE TO CHILD");
            eventBus.post(new MoveToChild(newNode.value,actual.value,compareX));
            Node node = isLeft ? actual.left : actual.right;
            if (node == null) {
                node = newNode;
                generateNewNodeEvent(node, actual, isLeft);
                if(isLeft){
                    actual.left=node;
                }else{
                    actual.right=node;
                }
                eventBus.post(new LastActionEvent());
                break;
            }
            compareX=!compareX;
            actual=node;
        }
    }

    private Node buildNewNode(T insertedValue) {
        Node node = new Node(insertedValue);
        count++;
        return node;
    }

    public T odeber(T co) {
        //TODO
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
        create(prvky);
        return pom;
    }

    public T najdi(int x, int y) {
        //TODO
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

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        actual = null;
        root = null;
        count = 0;
    }

}
