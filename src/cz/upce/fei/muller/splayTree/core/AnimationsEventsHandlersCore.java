package cz.upce.fei.muller.splayTree.core;

import cz.commons.animation.AnimationControl;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.common.animations.RemovePreparation;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.muller.splayTree.graphics.IBinaryNodesElements;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class AnimationsEventsHandlersCore {

    private final AnimationControl animationControl;
    private final BinaryTreeLayoutManager manager;
    private IEndInitAnimation endHandler;

    private Point2D creatingPoint;
    private RemovePreparation removePreparation;

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










    private SplayGraphicsNodeElement getNode(Integer elementId){
        return (SplayGraphicsNodeElement) manager.getElementInfo(elementId).getElement();
    }

    private void insertTransition(IAnimationBuilder creator){
        animationControl.getTransitions().add(creator.getAnimation());
    }

    public void setEndAnimationHandler(IEndInitAnimation endAnimationHandler) {
        this.endHandler =endAnimationHandler;
    }

    public RemovePreparation getRemovePreparation() {
        return removePreparation;
    }

    public void clear() {
        //TODO clearing
    }
}
