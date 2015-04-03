package cz.upce.fei.muller.TwoDTree.structure;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public interface ITwoDTree<T extends ICoordinate> {

    void create(List<T> nodes);

    ICoordinate getRoot();

    int getCount();

    ICoordinate getRight();

    ICoordinate getLeft();

    boolean isEmpty();

    void clear();
}
