package cz.upce.fei.muller.splayTree.core;

import cz.commons.animation.AnimationControl;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.muller.splayTree.graphics.SplayNodeElement;
import cz.upce.fei.muller.splayTree.graphics.IBinaryNodesElements;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class AnimationsEventsHandlersCore {

    private final AnimationControl animationControl;
    private final BinaryTreeLayoutManager manager;
    private IEndAnimation endHandler;

    private Point2D creatingPoint;
    private Integer removeElementId;

    private List<TranslateTransition> moveParentsElements = new ArrayList<>();

    public AnimationsEventsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        initCreatingPoint();
        manager.getDepthManager().addEventConsumer(this);
    }

    private void initCreatingPoint() {
        creatingPoint = new Point2D(
                manager.getCanvas().getWidth()/2- IBinaryNodesElements.WIDTH/2,
                0
        );
    }


    private SplayNodeElement getNode(Integer elementId){
        return (SplayNodeElement) manager.getElementInfo(elementId).getElement();
    }

    private void insertTransition(IAnimationCreator creator){
        animationControl.getTransitions().add(creator.getAnimation());
    }

    public void setEndAnimationHandler(IEndAnimation handler) {
        this.endHandler = handler;
    }
}
