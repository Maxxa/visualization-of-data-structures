package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderSwapRoot extends BuilderSwapNode {

    private final BinaryHeapNode leafParent;

    private LineElement lineParentOfLeaf;

    public BuilderSwapRoot(BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond, Point2D firstPoint, Point2D secondPoint, BinaryHeapNode leafParent) {
        super(nodeFirst, nodeSecond, firstPoint, secondPoint);
        this.leafParent = leafParent;
    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = getMovingElements();
        SequentialTransition sq = new SequentialTransition(getFades(true),pt,getFades(false));
        pt.setOnFinished(setFinishedHandler());
        return new ParallelTransition(sq);
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwapRootEndEventHandler(lineParentOfLeaf,leafParent,nodeFirst,nodeSecond);
    }

    private FadeTransition getFadeTransition(int from,int to) {
        BinaryHeapNode left= leafParent.getChild(NodePosition.LEFT);
        lineParentOfLeaf = leafParent.getChildLine(left!=null && left.getElementId()==nodeSecond.getElementId()?NodePosition.LEFT:NodePosition.RIGHT);
        return FadesTransitionBuilder.getTransition(lineParentOfLeaf,Duration.millis(1),from,to);
    }
    private FadeTransition getFadeTransitionRoot(NodePosition position,int from,int to) {
        return FadesTransitionBuilder.getTransition(nodeFirst.getChildLine(position),Duration.millis(1),from,to);
    }

    private ParallelTransition getFades(boolean forward){
        int from = forward?1:0;
        int to = forward?0:1;
        FadeTransition ft =getFadeTransition(from,to);
        FadeTransition ft1 = getFadeTransitionRoot(NodePosition.LEFT,from,to);
        FadeTransition ft2 = getFadeTransitionRoot(NodePosition.RIGHT,from,to);
        return new ParallelTransition(ft,ft1,ft2);
    }
}
