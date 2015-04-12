package cz.upce.fei.muller.TwoDTree.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.muller.TwoDTree.animations.FindPlacePreparation;
import cz.upce.fei.muller.TwoDTree.animations.InsertPreparation;
import cz.upce.fei.muller.TwoDTree.animations.RemovePreparation;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderAddElement;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.TwoDTree.events.*;
import cz.upce.fei.muller.TwoDTree.graphics.ITwoDNodesElements;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.util.Duration;

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
    private RemovePreparation removePreparation;
    private FindPlacePreparation findPlacePreparator;
    private List<TranslateTransition> moveParentsElements = new ArrayList<>();
    private TwoDGraphicsNode findingNode;

    public AnimationsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        initCreatingPoint();
        manager.getDepthManager().addEventConsumer(this);
    }

    @Subscribe
    public void handleEndEvent(LastActionEvent event) {
        System.out.println("-----------END BUILDING ANIMATION-----------");
        initMovingTransition();

        if(findPlacePreparator!=null && findingNode!=null){
            buildFindEnd();
        }

        endInitAnimation.endAnimation(animationControl.isMarkedAsStepping());
        if (!animationControl.isMarkedAsStepping()) {
            animationControl.playForward();
        }
    }

    private void buildFindEnd() {
        animationControl.getTransitions().addAll(findPlacePreparator.getMovings());
        animationControl.getTransitions().add(getFadeTransition());
    }

    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent event) {
        System.out.println("------------EVENT CREATE ROOT-------------");
        TwoDGraphicsNode newNode = new TwoDGraphicsNode(event.getNode(), 0, 0);
        newNode.setOpacity(0);
        manager.addElement(newNode, null, false);
        manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT), newNode.getChildLine(NodePosition.RIGHT));
        insertTransition(new BuilderAddElement(manager.getNodePosition(event.getNode().getId()), creatingPoint, getNode(event.getNode().getId())));
    }

    @Subscribe
    public void handleInsertNodeEvent(InsertNodeEvent event) {
        System.out.println("------------EVENT INSERT-------------");
        Point2D toPoint = creatingPoint;
        TwoDGraphicsNode newNode;
        boolean insertToCanvas = true;
        try {
            if (findPlacePreparator == null) {
                newNode = new TwoDGraphicsNode(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY());
            } else {
                insertToCanvas = false;
                toPoint = findPlacePreparator.getLastPosition();
                newNode = findPlacePreparator.getInsertedNode();
            }

            manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT), newNode.getChildLine(NodePosition.RIGHT));
            manager.addElement(newNode, event.getParentNode().getId(), event.isLeftChild(), insertToCanvas);

            InsertPreparation preparation = new InsertPreparation(event, manager, toPoint, findPlacePreparator);
            insertTransition(preparation.getBuilder());
            initMovingTransition();
        } catch (Exception ex) {
            System.err.println(ex);
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                System.err.println(ex.getStackTrace()[i]);
            }
        }
    }

    @Subscribe
    public void handleMovingChilds(MoveToChildEvent event) {
        System.out.println("EVENT iterable....");
        if (this.findPlacePreparator == null) {
            TwoDGraphicsNode newNode = new TwoDGraphicsNode(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY());
            manager.getCanvas().getChildren().addAll(newNode
                    /*,newNode.getChildLine(NodePosition.LEFT),newNode.getChildLine(NodePosition.RIGHT)*/);
            System.out.println("create and insert...");
            findPlacePreparator = new FindPlacePreparation(newNode, creatingPoint);
        }

        findPlacePreparator.addMove(
                manager.getNodePosition(event.getComparingNode().getId()),
                getNode(event.getComparingNode().getId()), event.isCompareX());

    }

    @Subscribe
    public void handleStartFinding(StartFindingEvent event){
        System.out.println("Start FINDING node...");
        findingNode = new TwoDGraphicsNode(event.getCoordinate(),(int)creatingPoint.getX(),(int)creatingPoint.getY(),true);
        findingNode.setOpacity(0);
    }

    @Subscribe
    public void handleFindChilds(FindEvent event) {
        try {
            if(findPlacePreparator==null){
                manager.getCanvas().getChildren().addAll(findingNode);
                findPlacePreparator = new FindPlacePreparation(findingNode, creatingPoint);
            }
        } catch (Exception ex) {
            System.err.println(ex);
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                System.err.println(ex.getStackTrace()[i]);
            }
        }

        findPlacePreparator.addMove(
                manager.getNodePosition(event.getComparedNodeId()),
                getNode(event.getComparedNodeId()), event.isCompareX());

    }


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
        this.moveParentsElements.add(new BuilderAnimMoveNode(event.getOldPoint(), event.getNewPoint(), getNode(event.getElementId())).getTranslateTransition());
    }

    public RemovePreparation getRemovePreparation() {
        return removePreparation;
    }

    public void setEndAnimationHandler(IEndInitAnimation handler) {
        this.endInitAnimation = handler;
    }

    public void setRemovePreparation(RemovePreparation removePreparation) {
        this.removePreparation = removePreparation;
    }

    private TwoDGraphicsNode getNode(Integer elementId) {
        return (TwoDGraphicsNode) manager.getElementInfo(elementId).getElement();
    }

    private void insertTransition(IAnimationBuilder creator) {
        animationControl.getTransitions().add(creator.getAnimation());
    }

    private void initCreatingPoint() {
        creatingPoint = new Point2D(manager.getCanvas().getWidth() / 2 - ITwoDNodesElements.WIDTH / 2, 0);
    }

    private void initMovingTransition() {
        if (moveParentsElements.size() > 0) {
            ParallelTransition pt = new ParallelTransition();
            pt.getChildren().addAll(moveParentsElements);
            animationControl.getTransitions().add(pt);
        }
        moveParentsElements.clear();
    }

    public void clear() {
        System.out.println("_____________________CLEAR______________________\n\n");
        if(findingNode!=null){
            manager.getCanvas().getChildren().remove(findingNode);
        }
        findPlacePreparator = null;
        moveParentsElements.clear();
        removePreparation = null;
        findingNode = null;
        animationControl.clear();
    }

    public FadeTransition getFadeTransition() {
        return FadesTransitionBuilder.getTransition(findPlacePreparator.getInsertedNode(), Duration.millis(500),1,0);
    }
}
