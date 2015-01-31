package cz.upce.fei.muller.binaryHeap.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.upce.fei.muller.binaryHeap.events.*;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import cz.upce.fei.muller.binaryHeap.graphics.IBinaryNodesElements;
import javafx.animation.ParallelTransition;
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
        creatingPoint = new Point2D(manager.getCanvas().getWidth()/2- IBinaryNodesElements.WIDTH/2,0);
    }

    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent event) {
        manager.addElement(new BinaryHeapNode(event.getHeapNode(), 0, 0), null, false);
        insertTransition(new BuilderAddElement(manager.getNodePosition(event.getHeapNode().getId()),creatingPoint,getNode(event.getHeapNode().getId())));
    }

    @Subscribe
    public void handleCompareNodeEvent(CompareNodeEvent event) {
//        System.out.println("_Handle compare_");
        //TODO @deprecated?
        // insertTransition(new CreatorCompareNode(getNode(event.getFirstNode().getId()), getNode(event.getSecondNode().getId()), event.isTrue()));
    }

    @Subscribe
    public void handleEndEvent(LastEvent event) {
        System.out.println("_Handle LAST EVENT_\n\n");
        endHandler.endAnimation(animationControl.isMarkedAsStepping());
        initMovingTransition();

        if(!animationControl.isMarkedAsStepping()){
            animationControl.playForward();
        }

        /**
         * TODO
         *  MYBE removing? then remove node?
         *
         * */
//        manager.removeElement(event.getRootNode().getId());//TODO MY BE NEED remove element after animation ?
    }

    private void initMovingTransition() {
        if(moveParentsElements.size()>0) {
            ParallelTransition pt = new ParallelTransition();
            pt.getChildren().addAll(moveParentsElements);
            animationControl.getTransitions().add(pt);
        }
        moveParentsElements.clear();
    }

    @Subscribe
    public void handleInsertNodeEvent(InsertNodeEvent event) {
        manager.addElement(new BinaryHeapNode(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY()), event.getParentNode().getId(), event.isLeftChild());
        BinaryHeapNode parent = getNode(event.getParentNode().getId());
        BinaryHeapNode newNode = getNode(event.getNewNode().getId());
        LineElement line = new LineElement(parent.getChildConnector(event.isLeftChild()?0:1),newNode);
        parent.setChildLine(line,event.isLeftChild());
        manager.getCanvas().getChildren().addAll(line);
        insertTransition(new BuilderAddElement(manager.getNodePosition(event.getNewNode().getId()), creatingPoint, getNode(event.getNewNode().getId())));
    }

    @Subscribe
    public void handleRemoveRootEvent(RemoveRootEvent event) {
        System.out.println("Handle remove root");
        manager.removeElement(event.getRootNode().getId());
        insertTransition(new BuilderRemoveRoot(getNode(event.getRootNode().getId())));
        initMovingTransition();
        removeElementId = event.getRootNode().getId();
    }

    @Subscribe
    public void handleSwapNodeEvent(SwapNodeEvent event) {
        System.out.println("Handle swap node");
        initMovingTransition();
        SwapHandler handler = new SwapHandler(manager,getNode(event.getFirstNode().getId()), getNode(event.getSecondNode().getId()),event);
        IAnimationBuilder creator = handler.getCreator();
        manager.swapElement(event.getFirstNode().getId(), event.getSecondNode().getId());
        insertTransition(creator);
    }

    @Subscribe
    public void handleMoveElementNodeEvent(MoveElementEvent event) {
        this.moveParentsElements.add(new BuilderAnimMoveNode(event.getOldPoint(),event.getNewPoint(),getNode(event.getElementId())).getTranslateTransition());
    }

    private BinaryHeapNode getNode(Integer elementId){
        return (BinaryHeapNode) manager.getElementInfo(elementId).getElement();
    }

    private void insertTransition(IAnimationBuilder creator){
        animationControl.getTransitions().add(creator.getAnimation());
    }

    public void setEndAnimationHandler(IEndAnimation handler) {
        this.endHandler = handler;
    }
}
