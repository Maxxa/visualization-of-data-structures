package cz.upce.fei.muller.splayTree.core;

import cz.commons.animation.AnimationControl;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
class Data {

    final AnimationControl animationControl;
    final ITreeLayoutManager manager;

    final Point2D creatingPoint;
    final Point2D flashMessagePosition;

    public Data(AnimationControl animationControl, ITreeLayoutManager manager, Point2D creatingPoint, Point2D flashMessagePosition) {
        this.animationControl = animationControl;
        this.manager = manager;
        this.creatingPoint = creatingPoint;
        this.flashMessagePosition = flashMessagePosition;
    }

    void insertTransition(IAnimationBuilder creator) {
        animationControl.getTransitions().add(creator.getAnimation());
    }

    SplayGraphicsNodeElement getNode(Integer elementId) {
        return (SplayGraphicsNodeElement) manager.getElementInfo(elementId).getElement();
    }
}
