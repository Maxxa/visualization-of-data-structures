package cz.upce.fei.muller.TwoDTree.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.muller.TwoDTree.animations.FindPlacePreparation;
import cz.upce.fei.muller.TwoDTree.animations.InsertPreparation;
import cz.upce.fei.muller.TwoDTree.animations.RemovePreparation;
import cz.upce.fei.muller.TwoDTree.animations.SwapPreparation;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderAddElement;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderShowFindElement;
import cz.upce.fei.muller.TwoDTree.events.*;
import cz.upce.fei.muller.TwoDTree.graphics.FlashMessageViewer;
import cz.upce.fei.muller.TwoDTree.graphics.ITwoDNodesElements;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private final Point2D flashMessagePosition;
    private List<FlashMessageViewer> viewers = new LinkedList<>();
    private boolean isRemovingEnd = false;

    public AnimationsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        initCreatingPoint();
        flashMessagePosition = new Point2D(manager.getCanvas().getWidth() / 2 + 100, 10);

        manager.getDepthManager().addEventConsumer(this);
    }

    @Subscribe
    public void handleEndEvent(LastActionEvent event) {
        System.out.println("-----------END BUILDING ANIMATION-----------");

        initMovingTransition();
        if (findPlacePreparator != null && findingNode != null && !isRemovingEnd) {
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
        newNode.setLabelBold(true, true);
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
            Integer depth = manager.getElementInfo(event.getParentNode().getId()).getDepth() + 1;
            if (findPlacePreparator == null) {
                newNode = new TwoDGraphicsNode(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY());
                newNode.setLabelBold(depth % 2 <= 0, true);
            } else {
                insertToCanvas = false;
                toPoint = findPlacePreparator.getLastPosition();
                newNode = findPlacePreparator.getInsertedNode();
            }
            manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT), newNode.getChildLine(NodePosition.RIGHT));
            manager.addElement(newNode, event.getParentNode().getId(), event.isLeftChild(), insertToCanvas);

            InsertPreparation preparation = new InsertPreparation(event, manager, toPoint, findPlacePreparator != null);
            editFindingPlacePreparator();
            insertTransition(preparation.getBuilder());
            initAfterInsertPreparation(depth);
            initMovingTransition();
        } catch (Exception ex) {
            System.err.println(ex);
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                System.err.println(ex.getStackTrace()[i]);
            }
        }
    }

    private void initAfterInsertPreparation(final int depth) {
        if (findPlacePreparator != null) {
            ParallelTransition pt = new ParallelTransition();
            pt.setOnFinished(
                    new StepEventHandler() {
                        boolean isX = depth % 2 <= 0;

                        @Override
                        protected void handleForward(ActionEvent actionEvent) {
                            findPlacePreparator.getInsertedNode().setLabelBold(isX, true);
                        }

                        @Override
                        protected void handleBack(ActionEvent actionEvent) {
                            findPlacePreparator.getInsertedNode().setLabelBold(isX, false);
                        }
                    }

            );
            animationControl.getTransitions().add(pt);
        }
    }

    private void editFindingPlacePreparator() {
        if (findPlacePreparator != null) {
            animationControl.getTransitions().addAll(findPlacePreparator.getMovings());
        }
    }

    @Subscribe
    public void handleMovingChilds(MoveToChildEvent event) {
        System.out.println("EVENT iterable....");
        if (this.findPlacePreparator == null) {
            TwoDGraphicsNode newNode = new TwoDGraphicsNode(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY());
            manager.getCanvas().getChildren().addAll(newNode);
            findPlacePreparator = new FindPlacePreparation(newNode, creatingPoint);
        }

        findPlacePreparator.addMove(
                manager.getNodePosition(event.getComparingNode().getId()),
                getNode(event.getComparingNode().getId()), event.isCompareX());

    }

    @Subscribe
    public void handleStartRemoving(StartRemoving event){
        System.out.println("__________START REMOVE__________");
    }

    @Subscribe
    public void handleEndRemoving(RemoveElement event){
        System.out.println("__________END REMOVE__________");
        isRemovingEnd =true;
        //TODO build remove...
    }

    @Subscribe
    public void handleFindMinMax(FindingMinMaxEvent event){
        System.out.println("finding min max: isMin="+event.isMin());
        String message;
        if(event.isMin()){
            message="Hledám minimum v pravém podstromu, dle ";
        }else{
            message="Hledám maximum v levém podstromu, dle ";
        }
        message+= event.isXCoordinate()?"X":"Y";
        animationControl.getTransitions().add(showViewer(buildViewer(message)));
    }

    @Subscribe
      public void handleSwapElement(SwapNodeEvent event){
        System.out.println("swap element "+event.getSecondNode()+" "+event.getFirstNode());
        if(!event.getFirstNode().getId().equals(event.getSecondNode().getId())) {
            SwapPreparation handler = new SwapPreparation(manager,
                    WorkBinaryNodeInfoBuilder.getWorkInfo(event.getFirstNode().getId(), manager),
                    WorkBinaryNodeInfoBuilder.getWorkInfo(event.getSecondNode().getId(),manager));
            IAnimationBuilder creator = handler.getBuilder();
            manager.swapElement(event.getFirstNode().getId(), event.getSecondNode().getId());
            insertTransition(creator);
        }

    }

    @Subscribe
    public void handleStartFinding(StartFindingEvent event) {
        System.out.println("Start FINDING node...");
        findingNode = new TwoDGraphicsNode(event.getCoordinate(), (int) creatingPoint.getX(), (int) creatingPoint.getY(), true);
        findingNode.setOpacity(0);
    }

    @Subscribe
    public void handleFindChilds(FindEvent event) {
        try {
            if (findPlacePreparator == null) {
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

    @Subscribe
    public void handleEndFind(ElementFindEndEvent event) {
        System.out.println("ELEMENT FIND");
        if (event.isFind()) {
            TwoDGraphicsNode node = manager.getElementInfo(event.getFindNode().getId()).getElement();
            findPlacePreparator.addTransition(new BuilderShowFindElement(node).getAnimation());
        } else {
            findPlacePreparator.addTransition(showViewer(buildViewer("Bod nebyl nalezen.")));
        }
    }

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
        if (findingNode != null) {
            manager.getCanvas().getChildren().remove(findingNode);
        }
        findPlacePreparator = null;
        moveParentsElements.clear();
        removePreparation = null;
        findingNode = null;
        isRemovingEnd =false;
        animationControl.clear();
        manager.getCanvas().getChildren().removeAll(viewers);
        viewers.clear();
    }

    public FadeTransition getFadeTransition() {
        return FadesTransitionBuilder.getTransition(findPlacePreparator.getInsertedNode(), Duration.millis(500), 1, 0);
    }

    private FlashMessageViewer buildViewer(String text) {
        FlashMessageViewer viewer = new FlashMessageViewer(text);
        viewer.setTranslateX(flashMessagePosition.getX());
        viewer.setTranslateY(flashMessagePosition.getY());
        manager.getCanvas().getChildren().addAll(viewer);
        viewer.setOpacity(0);
        viewers.add(viewer);
        return viewer;
    }

    private Transition showViewer(Node element) {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(
                FadesTransitionBuilder.getTransition(element, Duration.ONE, 0, 1),
                ParallelTransitionBuilder.create().delay(Duration.seconds(2)).build(),
                FadesTransitionBuilder.getTransition(element, Duration.ONE, 1, 0)
        );
        return st;
    }

}
