package cz.upce.fei.muller.TwoDTree.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.TwoDTree.events.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class TwoDTree<T extends AbstractStructureElement & ICoordinate> implements Iterable<ExtendData<T>>, ITwoDTree<T> {

    private final EventBus eventBus;
    private Node<T> actual;
    private boolean isXCoordinate = true;
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
            generateLastEvent();
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
        generateLastEvent();
    }

    private Node createRecursive(List<T> nodes, Node parent, Boolean leftSubTree, Boolean isSortingX) {
        Node tmp;
        if (nodes.size() == 1) {
            tmp = buildNewNode(nodes.get(0));
            generateNewNodeEvent(tmp, parent, leftSubTree);
            return tmp;
        }
        Collections.sort(nodes, isSortingX ? new ComparatorX() : new ComparatorY());
        int median = nodes.size() / 2;
        List<T> rightNodes = nodes.subList(median + 1, nodes.size());
        List<T> leftNodes = nodes.subList(0, median);
        tmp = buildNewNode(nodes.get(median));
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

    private void generateLastEvent() {
        eventBus.post(new LastActionEvent());
    }

    @Override    public void insert(T insertedValue) {
        if (isEmpty()) {
            root = buildNewNode(insertedValue);
            generateNewNodeEvent(root, null, false);
            actual = root;
            generateLastEvent();
            return;
        }
        actual = root;
        boolean compareX = true;
        boolean isLeft;
        Node newNode = buildNewNode(insertedValue);

        while (true) {
            int value = compareX ? actual.value.getX() : actual.value.getY();
            isLeft = (compareX ? insertedValue.getX() : insertedValue.getY()) < value;
            eventBus.post(new MoveToChildEvent(newNode.value, actual.value, compareX));
            Node node = isLeft ? actual.left : actual.right;
            if (node == null) {
                node = newNode;
                generateNewNodeEvent(node, actual, isLeft);
                if (isLeft) {
                    actual.left = node;
                } else {
                    actual.right = node;
                }
                generateLastEvent();
                break;
            }
            compareX = !compareX;
            actual = node;
        }
    }

    private Node buildNewNode(T insertedValue) {
        Node node = new Node(insertedValue);
        count++;
        return node;
    }

    @Override
    public T remove(int x, int y) {
        eventBus.post(new StartRemoving());
        System.out.println("Start removing");
        T returnValue = find(x, y); // finding removed element
        if (returnValue == null) {
            // removed element not found...
            generateLastEvent();
            return null;
        }
        removeRecursive();
        eventBus.post(new RemoveElement(returnValue));
        generateLastEvent();
        return returnValue;
    }

    private void removeRecursive() {
        if(!actual.hasLeft() && !actual.hasRight()){ // is leaf
            // i must remove
            System.out.println("LEAF REMOVE "+actual.value);
            clearReference();
            return;
        }
        RemoveHelper helper = find(actual.hasRight());
        eventBus.post(new SwapNodeEvent(actual.value,helper.node.value));
        actual.swapValue(helper.node);
        actual=helper.node;
        isXCoordinate=helper.isX;
        removeRecursive();
    }

    private void clearReference() {
        WidthIterator iterator = new WidthIterator(root,true);
        if(root.value.getId()==actual.value.getId()){
            System.out.println("Mazu root");
            clear();
            return;
        }

        while (iterator.hasNext()){
            Node<T> data =iterator.next().getNode();

            if(data.hasLeft()){
                if(data.left.value.getId()==actual.value.getId()){
                    System.out.println("Mazu leveho syna");
                    data.left=null;
                }
            }
            if(data.hasRight()){
                if(data.right.value.getId()==actual.value.getId()){
                    System.out.println("Mazu praveho syna");
                    data.right=null;
                }
            }
        }
    }

    // method find min or max at subtree
    private RemoveHelper find(boolean isMin){
        eventBus.post(new FindingMinMaxEvent(isMin));
        System.out.println("Finding in subtree... "+(isMin?"RIGHT":"LEFT")+" coord "+(isXCoordinate?"X":"Y"));
        Node<T> temp = isMin?actual.right:actual.left;
        WidthIterator iterator = new WidthIterator(temp,isXCoordinate);
        Boolean isX = isXCoordinate;
        while (iterator.hasNext()) {
            ExtendData<T> data = iterator.next();
            System.out.println(data.getData());
            int current = isXCoordinate ? temp.value.getX() : temp.value.getY();
            int iterateValue = isXCoordinate ? data.getData().getX() : data.getData().getY();
            if (isMin && iterateValue <= current ||
                !isMin && iterateValue >= current) {
                temp=data.getNode();
                isX=data.getDimension().equals(ExtendData.Dimension.DIMENSION_X);
            }
        }

        System.out.println((isMin?"MIN":"MAX")+" "+temp.value);
        return new RemoveHelper(temp,isX);
    }

    @Override
    public T find(int x, int y) {
        isXCoordinate = true;
        actual = root;
        eventBus.post(new StartFindingEvent(x, y));
        boolean right;
        while (true) {
            eventBus.post(new FindEvent(actual.value, isXCoordinate));
            if (actual.value.getX() == x && actual.value.getY() == y) {
                eventBus.post(new ElementFindEndEvent(actual.value));
                generateLastEvent();
                return actual.value;
            }
            right = isXCoordinate ? actual.value.getX() < x : actual.value.getY() < y;
            if (actual.right != null && right == true) {
                actual = actual.right;
                isXCoordinate = !isXCoordinate;
                continue;
            }
            if (actual.left != null && right == false) {
                actual = actual.left;
                isXCoordinate = !isXCoordinate;
                continue;
            }
            eventBus.post(new ElementFindEndEvent());
            generateLastEvent();
            return null;
        }
    }

    @Override
    public T getRoot() {
        actual = root;
        isXCoordinate=true;
        return root.value;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public T getRight() {
        if (!actual.right.hasRight()) {
            return null;
        }
        actual = actual.right;
        isXCoordinate =!isXCoordinate;
        return actual.value;
    }

    @Override
    public T getLeft() {
        if (!actual.left.hasLeft()) {
            return null;
        }
        actual = actual.left;
        isXCoordinate =!isXCoordinate;
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
        isXCoordinate=true;
    }

    @Override
    public Iterator<ExtendData<T>> iterator() {
        return new WidthIterator(root,true);
    }

    class RemoveHelper{

        Node<T> node;
        boolean isX;

        RemoveHelper(Node<T> node, boolean isX) {
            this.node = node;
            this.isX = isX;
        }
    }

}
