package cz.upce.fei.muller.TwoDTree.structure;

import java.util.ArrayList;

/**
 * @author Vojtěch Müller
 */
public interface ITwoDTree<T extends ICoordinate> {

    void create(ArrayList<T> nodes);

    ICoordinate getRoot();

    int getCount();

    ICoordinate getRight();

    ICoordinate getLeft();
}
