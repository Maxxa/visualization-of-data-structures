package cz.upce.fei.muller.splayTree.core;

import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.RotationPreparation;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.common.events.RotationEvent;
import cz.upce.fei.common.gui.FlashMessageViewer;
import cz.upce.fei.muller.splayTree.animations.FindPlacePreparation;
import cz.upce.fei.muller.splayTree.animations.FlashMessageViewerHelper;
import cz.upce.fei.muller.splayTree.events.SplayOperationEvent;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.*;

/**
 * @author Vojtěch Müller
 */
public abstract class AbstractEventPreparation {

    protected final List<FlashMessageViewer> viewers = new LinkedList<>();
    protected Map<Integer, FindPlacePreparation> searching = new HashMap<>();
    protected final Data data;
    protected List<Transition> moveParentsElements = new ArrayList<>();
    protected List<Transition> rotations = new LinkedList<>();

    protected AbstractEventPreparation(Data data) {
        this.data = data;
    }

    public void clearBeforeNewAction() {
        for (Map.Entry<Integer, FindPlacePreparation> entry : searching.entrySet()) {
            if (entry.getValue().getSearchingNode() != null) {
                data.manager.getCanvas().getChildren().remove(entry.getValue().getSearchingNode());
            }
        }
        moveParentsElements.clear();
        rotations.clear();
        data.animationControl.clear();
        data.manager.getCanvas().getChildren().removeAll(viewers);
        viewers.clear();
        searching.clear();
        clear();
    }

    protected abstract void clear();

    public void splayOperation(SplayOperationEvent.SplayOperation type) {
        FlashMessageViewer flashMessageViewer = FlashMessageViewerHelper.buildViewer(type.toString(), data.flashMessagePosition, data.manager.getCanvas());
        SequentialTransition st = new SequentialTransition();
        st.getChildren().add(FlashMessageViewerHelper.showViewer(flashMessageViewer));
        st.getChildren().addAll(rotations);
        rotations.clear();
        data.animationControl.getTransitions().add(st);
        viewers.add(flashMessageViewer);
    }

    public void moveToChild(Object findingKey, SplayNodeImpl comparingNode) {
        Integer key = (Integer) findingKey;
        FindPlacePreparation preparator;
        if (!searching.containsKey(key)) {
            SplayNodeImpl search = new SplayNodeImpl(key);
            SplayGraphicsNodeElement searchNode = new SplayGraphicsNodeElement(search, 0, 0, true);
            searchNode.setOpacity(0);
            searchNode.setVisible(false);
            data.manager.getCanvas().getChildren().addAll(searchNode);
            preparator = new FindPlacePreparation(searchNode, data.creatingPoint);
            searching.put(key, preparator);
        } else {
            preparator = searching.get(key);
        }
        Point2D point = data.manager.getNodePosition(comparingNode.getId());
        preparator.addMove(point);
    }

    public void matchFind(Object key) {
        FindPlacePreparation preparator = searching.get(key);
        if (preparator != null) {
            preparator.addTransition(getFadeTransition(preparator.getSearchingNode()));
            data.animationControl.getTransitions().addAll(preparator.getMovings());
        } else {
            System.err.println("Preparator je null neco je spatne...");
        }
    }

    public FadeTransition getFadeTransition(SplayGraphicsNodeElement node) {
        return FadesTransitionBuilder.getTransition(node, Duration.millis(500), 1, 0);
    }

    public void moveElementAtLayout(Integer elementId, Point2D oldPoint, Point2D newPoint) {
        this.moveParentsElements.add(new BuilderAnimMoveNode(oldPoint, newPoint, data.getNode(elementId)).getTranslateTransition());
    }

    protected ParallelTransition getElementMovings(List<MoveElementEvent> moves) {
        ParallelTransition pt = new ParallelTransition();
        for (MoveElementEvent move : moves) {
            BuilderAnimMoveNode builderAnimMoveNode = new BuilderAnimMoveNode(move.getOldPoint(), move.getNewPoint(), data.manager.getElementInfo(move.getElementId()).getElement());
            pt.getChildren().add(builderAnimMoveNode.getTranslateTransition());
        }
        return pt;
    }

    public void rotation(RotationEvent event) {
        RotationPreparation preparation = new RotationPreparation(event,(BinaryTreeLayoutManager) data.manager, this.moveParentsElements);
        moveParentsElements.clear();
        rotations.add(new ParallelTransition(preparation.getBuilder().getAnimation()));
    }
}