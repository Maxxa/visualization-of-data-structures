package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.graphics.Element;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class CreatorAddElement extends CreatorAnimMoveNode{

    public CreatorAddElement(Point2D to,Point2D creatingPoint, Element element) {
        super(creatingPoint, to, element);
    }

}
