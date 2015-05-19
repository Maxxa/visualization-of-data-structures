package cz.upce.fei.muller.treap.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.RemovePreparation;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.common.animations.builders.BuilderRemoveRoot;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.gui.FlashMessageViewer;
import cz.upce.fei.muller.treap.animations.FindPlacePreparation;
import cz.upce.fei.muller.treap.animations.InsertPreparation;
import cz.upce.fei.muller.treap.animations.builders.BuilderAddElement;
import cz.upce.fei.muller.treap.animations.builders.BuilderShowFindElement;
import cz.upce.fei.muller.treap.events.*;
import cz.upce.fei.muller.treap.graphics.ITreapBinaryNodesElements;
import cz.upce.fei.muller.treap.graphics.TreapGraphicElement;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;
import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class AnimationsEventsHandlersCore {

    private final AnimationControl animationControl;
    private final BinaryTreeLayoutManager manager;
    private IEndInitAnimation endInitAnimation;

    private Point2D creatingPoint;
    private FindPlacePreparation findPlacePreparator;
    private TreapGraphicElement findingNode;
    private final Point2D flashMessagePosition;
    private List<FlashMessageViewer> viewers = new LinkedList<>();
    private boolean isRemovingEnd = false;
    private List<TranslateTransition> moveParentsElements = new ArrayList<>();
    private RemovePreparation removePreparation;
    private List<TreapGraphicElement> elementsNotFound = new ArrayList<>();

    public AnimationsEventsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        initCreatingPoint();
        flashMessagePosition = new Point2D(manager.getCanvas().getWidth() / 2 + 100, 10);
        manager.getDepthManager().addEventConsumer(this);
    }

    @Subscribe
    public void handleEndEvent(LastActionEvent event) {
        initMovingTransition();
        if (findPlacePreparator != null && findingNode != null && !isRemovingEnd) {
            buildFindEnd();
            findPlacePreparator = null;
        }
        endInitAnimation.endAnimation(animationControl.isMarkedAsStepping());
        if (!animationControl.isMarkedAsStepping()) {
            animationControl.playForward();
        }
        System.out.println("%%%%%%0000000_________END PREPARATION EVENT");
    }

    private void buildFindEnd() {
        animationControl.getTransitions().addAll(findPlacePreparator.getMovings());
        animationControl.getTransitions().add(getFadeTransition());
    }

    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent event) {
        System.out.println("___HANDLE CREATE ROOT__");
        TreapGraphicElement newNode = new TreapGraphicElement(event.getNode(), 0, 0);
        newNode.setOpacity(0);
        manager.addElement(newNode, null, false);
        manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT), newNode.getChildLine(NodePosition.RIGHT));
        insertTransition(new BuilderAddElement(manager.getNodePosition(event.getNode().getId()), creatingPoint, getNode(event.getNode().getId())));
    }

    @Subscribe
    public void handleElementKeyNotInserted(ElementKeyExistEvent event) {
        System.out.println("___ HANDLE ELEMENT KEY EXIST___");
        if (findPlacePreparator != null) {
            findPlacePreparator.addTransition(showViewer(buildViewer("Klíč již existuje.")));
            findPlacePreparator.addTransition(getFadeTransition());
            elementsNotFound.add(findPlacePreparator.getInsertedNode());
            editFindingPlacePreparator();
        }
    }

    @Subscribe
    public void handleInsertNodeEvent(InsertNodeEvent event) {
        System.out.println("___HANDLE INSERT ELEMENT__");
        Point2D toPoint = creatingPoint;
        TreapGraphicElement newNode;
        boolean insertToCanvas = true;
        try {
            if (findPlacePreparator == null) {
                newNode = new TreapGraphicElement(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY());
            } else {
                insertToCanvas = false;
                toPoint = findPlacePreparator.getLastPosition();
                newNode = findPlacePreparator.getInsertedNode();
            }

            manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT), newNode.getChildLine(NodePosition.RIGHT));
            manager.addElement(newNode, event.getParentNode().getId(), event.isLeftChild(), insertToCanvas);
            manager.getNodePosition(event.getNewNode().getId());
            InsertPreparation preparation = new InsertPreparation(event, manager, toPoint, findPlacePreparator != null);
            editFindingPlacePreparator();
            insertTransition(preparation.getBuilder());
            initMovingTransition();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            for (int i = 0; i < ex.getStackTrace().length; i++) {
                System.err.println(ex.getStackTrace()[i]);
            }
        }
    }

    @Subscribe
    public void handleMovingChild(MoveToChildEvent event) {
        System.out.println("Move to child..");
        if (this.findPlacePreparator == null) {
            TreapGraphicElement newNode = new TreapGraphicElement(event.getNewNode(), (int) creatingPoint.getX(), (int) creatingPoint.getY());
            newNode.setOpacity(0);
            manager.getCanvas().getChildren().addAll(newNode);
            findPlacePreparator = new FindPlacePreparation(newNode, creatingPoint);
        }
        findPlacePreparator.addMove(manager.getNodePosition(event.getComparingNode().getId()));

    }

    @Subscribe
    public void handleStartFinding(StartFindingEvent event) {
        TreapNodeImpl tmp = new TreapNodeImpl(event.getKey());
        findingNode = new TreapGraphicElement(tmp, (int) creatingPoint.getX(), (int) creatingPoint.getY(), true);
        findingNode.setOpacity(0);
    }

    @Subscribe
    public void handleFindChilds(FindEvent event) {
        if (findPlacePreparator == null) {
            manager.getCanvas().getChildren().addAll(findingNode);
            findPlacePreparator = new FindPlacePreparation(findingNode, creatingPoint);
        }

        findPlacePreparator.addMove(
                manager.getNodePosition(event.getComparedNodeId()));

    }

    @Subscribe
    public void handleEndFind(ElementFindEndEvent event) {
        if (event.isFind()) {
            TreapGraphicElement node = manager.getElementInfo(event.getFindNode().getId()).getElement();
            findPlacePreparator.addTransition(new BuilderShowFindElement(node).getAnimation());
        } else {
            findPlacePreparator.addTransition(showViewer(buildViewer("Bod nebyl nalezen.")));
        }
    }

    private void editFindingPlacePreparator() {
        if (findPlacePreparator != null) {
            animationControl.getTransitions().addAll(findPlacePreparator.getMovings());
            System.out.println(findPlacePreparator.getMovings().size());
            findPlacePreparator = null;
        }
    }

    private void initCreatingPoint() {
        creatingPoint = new Point2D(
                manager.getCanvas().getWidth() / 2 - ITreapBinaryNodesElements.WIDTH / 2,
                0
        );
    }

    @Subscribe
    public void handleRemoveElement(RemoveElementEvent event) {
        isRemovingEnd = true;
        removePreparation = new RemovePreparation(event.getRemoved().getId(), manager);
        manager.removeElement(event.getRemoved().getId(), false);
        insertTransition(new BuilderRemoveRoot(removePreparation));
        initMovingTransition();
    }


    @Subscribe
    public void handleMoveElementNodeEvent(MoveElementEvent event) {
        this.moveParentsElements.add(new BuilderAnimMoveNode(event.getOldPoint(), event.getNewPoint(), getNode(event.getElementId())).getTranslateTransition());
    }

    public void setEndAnimationHandler(IEndInitAnimation endInitAnimation) {
        this.endInitAnimation = endInitAnimation;
    }

    public RemovePreparation getRemovePreparation() {
        return removePreparation;
    }


    private TreapGraphicElement getNode(Integer elementId) {
        return (TreapGraphicElement) manager.getElementInfo(elementId).getElement();
    }

    private void insertTransition(IAnimationBuilder creator) {
        animationControl.getTransitions().add(creator.getAnimation());
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
        if (findingNode != null) {
            manager.getCanvas().getChildren().remove(findingNode);
        }
        findPlacePreparator = null;
        moveParentsElements.clear();
        removePreparation = null;
        findingNode = null;
        isRemovingEnd = false;
        animationControl.clear();
        manager.getCanvas().getChildren().removeAll(viewers);
        manager.getCanvas().getChildren().removeAll(elementsNotFound);
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
