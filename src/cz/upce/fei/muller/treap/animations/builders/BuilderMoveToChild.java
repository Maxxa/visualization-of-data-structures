package cz.upce.fei.muller.treap.animations.builders;

import cz.commons.layoutManager.WorkBinaryNodeInfo;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class BuilderMoveToChild extends BuilderAddElement {

    public BuilderMoveToChild(Point2D to, Point2D from, WorkBinaryNodeInfo currentInformation, boolean leftChild) {
        super(to, from, currentInformation, leftChild);
    }

    @Override
    public Transition getAnimation() {
        return super.getAnimation();
    }

    @Override
    protected Transition getFadeTransition() {
        return new ParallelTransition();
    }
}
