package cz.upce.fei.muller.TwoDTree.structure;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public interface ITwoDTree<T extends ICoordinate> {

    void create(List<T> nodes);

    void insert(T insertedValue);

    T remove(int x, int y);

    T find(int x, int y);

    ICoordinate getRoot();

    int getCount();

    ICoordinate getRight();

    ICoordinate getLeft();

    boolean isEmpty();

    void clear();
}
