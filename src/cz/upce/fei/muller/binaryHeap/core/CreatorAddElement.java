package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.graphics.Element;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class CreatorAddElement extends CreatorAnimMoveNode{

    public CreatorAddElement(Point2D to, Element element) {
        super(new Point2D(0,0), to, element);
    }

}
