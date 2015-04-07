package cz.upce.fei.muller.TwoDTree.animations.builders;

import cz.commons.layoutManager.WorkBinaryNodeInfo;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.geometry.Point2D;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderMoveToChild extends BuilderAddElement {

    private final List<Transition> transitionList;

    public BuilderMoveToChild(Point2D to, Point2D from, WorkBinaryNodeInfo currentInformation, boolean leftChild,List<Transition> transitionList) {
        super(to, from, currentInformation, leftChild);
        this.transitionList = transitionList;
    }

    @Override
    public Transition getAnimation() {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(transitionList);
        st.getChildren().addAll(super.getAnimation());
        return st;
    }

    @Override
    protected Transition getFadeTransition() {
        return new ParallelTransition();
    }
}
