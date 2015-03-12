package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.graphics.Element;
import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.upce.fei.common.core.IAnimationBuilder;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderAnimMoveNode implements IAnimationBuilder {

    Point2D from;
    Point2D to;
    Element element;

    public BuilderAnimMoveNode(Point2D from, Point2D to, Element element) {
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    public Transition getAnimation() {
        return getTranslateTransition();
    }

    public TranslateTransition getTranslateTransition(){
        return RelativeTranslateTransition.build(element,from,to,new Duration(1000));
    }
}
