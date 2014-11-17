package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.IStructureElement;

/**
 *
 * @author Vojtěch Müller
 */
public class BinaryTreeToArray<T extends IStructureElement> implements IBinaryTreeToArray<T> {

    T[] tree;
    private int count;

    public BinaryTreeToArray() {
       clear();
    }

    @Override
    public void clear() {
        tree = (T[]) new Object[3]; // one level at the beginning
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int countItems() {
        return count;
    }

    @Override
    public void insertRoot(T item) {
        if (isEmpty()) {
            insert(0, item);
        }
    }

    @Override
    public void insertLeftChild(Integer parent, T item) {
        if (!isEmpty()) {
            int index = 2 * parent + 1;
            if (existData(parent)) {
                insert(index, item);
            }
        }
    }

    @Override
    public void insertRightChild(Integer parent, T item) {
        if (!isEmpty()) {
            int index = 2 * parent + 2;
            if (existData(parent)) {
                insert(index, item);
            }
        }
    }

    @Override
    public boolean isLeaf(Integer item) {
        if (existData(item)) {
            if (!existData(2 * item + 1)) {
                if (!existData(2 * item + 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T removeLeftLeaf(Integer parent) {
        int index = 2 * parent + 1;
        return remove(index);
    }

    @Override
    public T removeRightLeaf(Integer parent) {
        int index = 2 * parent + 2;
        return remove(index);
    }

    @Override
    public T getRoot() {
        return get(0);
    }

    @Override
    public T getParent(Integer son) {
        int index = (son - 1) / 2;
        return get(index);
    }

    @Override
    public T getLeft(Integer parent) {
        int index = 2 * parent + 1;
        return get(index);
    }

    @Override
    public T getRight(Integer parent) {
        int index = 2 * parent + 2;
        return get(index);
    }

    @Override
    public T getBrother(Integer item) {
        if (item % 2 == 1) {
            int index = item + 1;
            return get(index);
        } else {
            int index = item - 1;
            return get(index);
        }
    }

    @Override
    public void swapNode(Integer index1, Integer index2) {
        if (existData(index1) && existData(index2)) {
            T p = tree[index1];
            tree[index1] = tree[index2];
            tree[index2] = p;
        }
    }

    @Override
    public T removeLast() {
        T d = tree[count - 1];
        tree[count - 1] = null;
        count--;
        return d;
    }

    private T get(Integer index) {
        if (existData(index)) {
            return tree[index];
        } else {
            return null;
        }
    }

    private T remove(Integer index) {
        if (isLeaf(index)) {
            T o = tree[index];
            tree[index] = null;
            count--;
            controlAndReductionArray();
            return o;
        } else {
            return null;
        }
    }

    private void insert(Integer index, T prvek) {
        try {
            if (!existData(index)) {
                tree[index] = prvek;
                count++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            extendArray();
            if (!existData(index)) {
                tree[index] = prvek;
                count++;
            }
        }
    }

    private boolean existData(Integer i) {
        try {
            return tree[i] != null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private void extendArray() {
        T[] newArray = (T[]) new Object[2 * tree.length + 1];
        System.arraycopy(tree, 0, newArray, 0, tree.length);
        tree = newArray;
    }

    private void controlAndReductionArray() {
        if (count < tree.length / 2) {
            T[] newArray = (T[]) new Object[(tree.length / 2) - 1];
            System.arraycopy(tree, 0, newArray, 0, tree.length);
            tree = newArray;
        }
    }
}
