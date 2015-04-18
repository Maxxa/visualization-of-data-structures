package cz.upce.fei.muller.TwoDTree.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.TwoDTree.events.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class TwoDTree<T extends AbstractStructureElement & ICoordinate> implements ITwoDTree<T> {

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

    private void generateLastEvent(){
        eventBus.post(new LastActionEvent());
    }

    @Override
    public void insert(T insertedValue) {
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
        Node newNode= buildNewNode(insertedValue);

        while (true) {
            int value = compareX ? actual.value.getX() : actual.value.getY();
            isLeft = (compareX ? insertedValue.getX() : insertedValue.getY()) < value;
            eventBus.post(new MoveToChildEvent(newNode.value,actual.value,compareX));
            Node node = isLeft ? actual.left : actual.right;
            if (node == null) {
                node = newNode;
                generateNewNodeEvent(node, actual, isLeft);
                if(isLeft){
                    actual.left=node;
                }else{
                    actual.right=node;
                }
                generateLastEvent();
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

    @Override
    public T remove(int x,int y) {
        eventBus.post(new StartRemoving());
        T returnValue = find(x,y);
        if(returnValue==null){
            // removed element not found...
            generateLastEvent();
            return null;
        }

        //have right? find min on right
        //have only left? find max on left
        //is leaf remove...



        return returnValue;
    }

    @Override
    public T find(int x, int y) {
        boolean isCompareX = true;
        actual = root;
        eventBus.post(new StartFindingEvent(x,y));
        boolean right;
        while (true) {
            eventBus.post(new FindEvent(actual.value,isCompareX));
            if (actual.value.getX() == x && actual.value.getY() == y) {
                eventBus.post(new ElementFindEvent(actual.value));
                generateLastEvent();
                return actual.value;
            }
            right = isCompareX ? actual.value.getX() < x : actual.value.getY() < y;
            if (actual.right != null && right == true) {
                actual = actual.right;
                isCompareX = !isCompareX;
                continue;
            }
            if (actual.left != null && right == false) {
                actual = actual.left;
                isCompareX = !isCompareX;
                continue;
            }
            generateLastEvent();
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
