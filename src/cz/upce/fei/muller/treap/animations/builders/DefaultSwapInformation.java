package cz.upce.fei.muller.treap.animations.builders;

import cz.commons.layoutManager.WorkBinaryNodeInfo;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class DefaultSwapInformation {

    public final WorkBinaryNodeInfo infoFirstElement;
    public final WorkBinaryNodeInfo infoSecondElement;
    public final Point2D firstNodePosition;
    public final Point2D secondNodePosition;

    public DefaultSwapInformation(WorkBinaryNodeInfo infoFirstElement, WorkBinaryNodeInfo infoSecondElement, Point2D firstNodePosition, Point2D secondNodePosition) {
        this.infoFirstElement = infoFirstElement;
        this.infoSecondElement = infoSecondElement;
        this.firstNodePosition = firstNodePosition;
        this.secondNodePosition = secondNodePosition;
    }

}
