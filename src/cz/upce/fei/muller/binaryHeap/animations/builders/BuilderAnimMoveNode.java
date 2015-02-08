package cz.upce.fei.muller.binaryHeap.animations.builders;

import cz.commons.graphics.Element;
import cz.commons.utils.FadesTransitionBuilder;
import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.upce.fei.common.core.IAnimationBuilder;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        FadeTransition fadeBefore = getFadeBefore();
        FadeTransition fadeAfter = getFadeAfter();
        SequentialTransition st = new SequentialTransition(getFadeTransition(),getTranslateTransition());
        if(fadeBefore!=null){
            st.getChildren().add(0,fadeBefore);
        }

        if(fadeAfter!=null){
            st.getChildren().add(fadeAfter);
        }
        return st;
    }

    public TranslateTransition getTranslateTransition(){
        return RelativeTranslateTransition.build(element,from,to,new Duration(1000));
    }

    protected FadeTransition getFadeBefore(){
        return null;
    }

    protected FadeTransition getFadeAfter(){
        return null;
    }

    protected FadeTransition getFadeTransition() {
        return FadesTransitionBuilder.getTransition(element, Duration.millis(500), 0 , 1);

    }
}
