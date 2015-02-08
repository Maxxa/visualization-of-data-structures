package cz.upce.fei.muller.binaryHeap.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.utils.FadesTransitionBuilder;
import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.animations.handlers.SwapRootEndEventHandler;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderSwapRoot extends BuilderSwapNode {

    private final BinaryHeapNode leafParent;
    private final NodePosition leafParentPosition;

    public BuilderSwapRoot(WorkBinaryNodeInfo infoFirst, WorkBinaryNodeInfo infoSecond,
                           Point2D firstPoint, Point2D secondPoint,
                           BinaryHeapNode leafParent, NodePosition leafParentPosition) {
        super(infoFirst, infoSecond, firstPoint,secondPoint, null,null,null);
        this.leafParent = leafParent;
        this.leafParentPosition =leafParentPosition;

    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = getMovingElements();
        SequentialTransition sq = new SequentialTransition(getFades(true),pt,getFades(false));
        pt.setOnFinished(setFinishedHandler());
        return new ParallelTransition(sq);
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwapRootEndEventHandler(leafParent.getChildLine(leafParentPosition),information.first,information.second);
    }

    private FadeTransition getFadeTransition(int from,int to) {
        LineElement line = leafParent.getChildLine(leafParentPosition);
        return FadesTransitionBuilder.getTransition(line,Duration.millis(1),from,to);
    }
    private FadeTransition getFadeTransitionRoot(BinaryNodeWithLine node, int from, int to, NodePosition position) {
        return FadesTransitionBuilder.getTransition(node.getChildLine(position),Duration.millis(1),from,to);
    }

    private ParallelTransition getFades(boolean visibility){
        int from = visibility?1:0;
        int to = visibility?0:1;
        FadeTransition ft =getFadeTransition(from,to);
        FadeTransition ft1 = getFadeTransitionRoot((BinaryNodeWithLine) information.first.get().getElement(), from, to, NodePosition.LEFT);
        FadeTransition ft2 = getFadeTransitionRoot((BinaryNodeWithLine) information.first.get().getElement(), from, to, NodePosition.RIGHT);
        FadeTransition ft3 = getFadeTransitionRoot((BinaryNodeWithLine) information.second.get().getElement(), from, to, NodePosition.LEFT);
        FadeTransition ft4 = getFadeTransitionRoot((BinaryNodeWithLine) information.second.get().getElement(), from, to, NodePosition.RIGHT);
        return new ParallelTransition(ft,ft1,ft2,ft3,ft4);
    }
}
