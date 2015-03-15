package cz.upce.fei.muller.trie.manager;

import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public interface IBlocksPositions {

    Point2D getPositionBlock();
    Point2D getPositionBlockKey();

    double getWidthBefore();
    double getWidthAfter();

}
