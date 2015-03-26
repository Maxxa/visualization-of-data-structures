package cz.upce.fei.muller.TwoDTree.structure;

/**
 * @author Vojtěch Müller
 */
public interface ITwoDTree {

    ICoordinate getRoot();

    int getCount();

    ICoordinate getRight();

    ICoordinate getLeft();
}
