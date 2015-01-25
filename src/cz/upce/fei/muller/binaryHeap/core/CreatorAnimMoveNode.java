package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.graphics.Element;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class CreatorAnimMoveNode implements IAnimationCreator{

    Point2D from;
    Point2D to;
    Element element;

    public CreatorAnimMoveNode(Point2D from, Point2D to, Element element) {
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    public ParallelTransition getAnimation() {
        return new ParallelTransition(getTranslateTranistion());
    }

    public TranslateTransition getTranslateTranistion(){
        TranslateTransition tt=TranslateTransitionBuilder.create().fromX(from.getX()).fromY(from.getY()).toX(to.getX())
                .toY(to.getY()).duration(new Duration(1000)).node(element).build();
        return tt;
    }


}
