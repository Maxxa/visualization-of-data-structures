package cz.upce.fei.muller.TwoDTree.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IEndInitAnimation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class AnimationsHandlersCore {

    private final AnimationControl animationControl;
    private final BinaryTreeLayoutManager manager;
    private IEndInitAnimation endInitAnimation;

    private Point2D creatingPoint;
//    private RemovePreparation removePreparation;

    private List<TranslateTransition> moveParentsElements = new ArrayList<>();

    public AnimationsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        initCreatingPoint();
        manager.getDepthManager().addEventConsumer(this);
    }

//    @Subscribe
//    public void handleEndEvent(LastEvent event) {
//        initMovingTransition();
//        endInitAnimation.endAnimation(animationControl.isMarkedAsStepping());
//        if(!animationControl.isMarkedAsStepping()){
//            animationControl.playForward();
//        }
//    }

//    @Subscribe
//    public void handleCreateRootEvent(CreateRootEvent event) {
//        BinaryHeapNode newNode = new BinaryHeapNode(event.getHeapNode(), 0, 0);
//        newNode.setOpacity(0);
//        manager.addElement(newNode, null, false);
//        manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT),newNode.getChildLine(NodePosition.RIGHT));
//        insertTransition(new BuilderAddElement(manager.getNodePosition(event.getHeapNode().getId()), creatingPoint, getNode(event.getHeapNode().getId())));
//    }

//    @Subscribe
//    public void handleInsertNodeEvent(InsertNodeEvent event) {
//        manager.addElement(new BinaryHeapNode(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY()), event.getParentNode().getId(), event.isLeftChild());
//        BinaryHeapNode newNode = getNode(event.getNewNode().getId());
//        manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT),newNode.getChildLine(NodePosition.RIGHT));
//        InsertPreparation preparation = new InsertPreparation(event,manager,creatingPoint);
//        insertTransition(preparation.getBuilder());
//    }

//    @Subscribe
//    public void handleRemoveRootEvent(RemoveRootEvent event) {
//        removePreparation = new RemovePreparation(event.getRootNode().getId(),manager);
//        manager.removeElement(event.getRootNode().getId(),false);
//        insertTransition(new BuilderRemoveRoot(removePreparation));
//        initMovingTransition();
//    }

//    @Subscribe
//    public void handleSwapNodeEvent(SwapNodeEvent event) {
//        initMovingTransition();
//        if(event.getFirstNode().getId()!=event.getSecondNode().getId()) {
//            SwapPreparation handler = new SwapPreparation(manager,WorkBinaryNodeInfoBuilder.getWorkInfo(event.getFirstNode().getId(),manager),
//                                                           WorkBinaryNodeInfoBuilder.getWorkInfo(event.getSecondNode().getId(),manager));
//            IAnimationBuilder creator = handler.getBuilder();
//            manager.swapElement(event.getFirstNode().getId(), event.getSecondNode().getId());
//            insertTransition(creator);
//        }

//    }

    @Subscribe
    public void handleMoveElementNodeEvent(MoveElementEvent event) {
//        this.moveParentsElements.add(new BuilderAnimMoveNode(event.getOldPoint(),event.getNewPoint(),getNode(event.getElementId())).getTranslateTransition());
    }

//    public RemovePreparation getRemovePreparation() {
//        return removePreparation;
//    }
//
//    public void setEndAnimationHandler(IEndInitAnimation handler) {
//        this.endInitAnimation = handler;
//    }
//
//    public void setRemovePreparation(RemovePreparation removePreparation) {
//        this.removePreparation = removePreparation;
//    }

//    private BinaryHeapNode getNode(Integer elementId){
//        return (BinaryHeapNode) manager.getElementInfo(elementId).getElement();
//    }

    private void insertTransition(IAnimationBuilder creator){
        animationControl.getTransitions().add(creator.getAnimation());
    }

    private void initCreatingPoint() {
//        creatingPoint = new Point2D(manager.getCanvas().getWidth()/2- IBinaryNodesElements.WIDTH/2,0);
    }

    private void initMovingTransition() {
        if(moveParentsElements.size()>0) {
            ParallelTransition pt = new ParallelTransition();
            pt.getChildren().addAll(moveParentsElements);
            animationControl.getTransitions().add(pt);
        }
        moveParentsElements.clear();
    }

    public void clear(){
        moveParentsElements.clear();
//        removePreparation = null;
        animationControl.clear();
    }
}
