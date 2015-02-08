package cz.upce.fei.muller.binaryHeap.animationsRename.builders;

import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.utils.FadesTransitionBuilder;
import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.binaryHeap.animationsRename.handlers.SwapElementEndEventHandler;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderSwapNode implements IAnimationBuilder {

    protected final Point2D firstPoint;
    protected final Point2D secondPoint;

    protected final SwapInformation information = new SwapInformation();

//    public BuilderSwapNode(BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond,
//                           Point2D firstPoint, Point2D secondPoint,
//                           BinaryHeapNode firstParent,
//                           NodePosition positionFromFirst, NodePosition firstParentPosition) {
//        information.positionFromFirst= positionFromFirst;
//        information.first =nodeFirst;
//        information.second= nodeSecond;
//        information.firstParent = firstParent;
//        information.firstParentPosition = firstParentPosition;
//        this.firstPoint = firstPoint;
//        this.secondPoint = secondPoint;
//    }

    public BuilderSwapNode(WorkBinaryNodeInfo infoFirst, WorkBinaryNodeInfo infoSecond,
                           Point2D firstPoint, Point2D secondPoint,
                           BinaryHeapNode firstParent, NodePosition positionFromFirst, NodePosition firstParentPosition) {
        information.first=infoFirst;
        information.second=infoSecond;
        this.firstPoint=firstPoint;
        this.secondPoint=secondPoint;
        information.firstParent = firstParent;
        information.positionFromFirst=positionFromFirst ;
        information.firstParentPosition = firstParentPosition;
    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = getMovingElements();
        SequentialTransition sq = new SequentialTransition(getFades(true),pt,getFades(false));
        pt.setOnFinished(setFinishedHandler());
        return new ParallelTransition(sq);
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwapElementEndEventHandler(information);
    }

    private ParallelTransition getFades(boolean visibility) {
        ParallelTransition pt = new ParallelTransition();

        if(information.firstParent!=null){ // first swaping node have parent, get
            pt.getChildren().add(getFadeTransition(information.firstParent,information.firstParentPosition, visibility));
        }
        pt.getChildren().addAll(
                getFadeTransition((BinaryHeapNode) information.second.get().getElement(), NodePosition.LEFT, visibility),
                getFadeTransition((BinaryHeapNode) information.second.get().getElement(), NodePosition.RIGHT, visibility));
//        if(information.first.getElementId()!=information.second.getElementId()){
            pt.getChildren().addAll(getFadeTransition((BinaryHeapNode) information.first.get().getElement(), NodePosition.RIGHT, visibility),
                                    getFadeTransition((BinaryHeapNode) information.first.get().getElement(), NodePosition.LEFT, visibility));
//        }/
        return pt;
    }

    private FadeTransition getFadeTransition(BinaryHeapNode node,NodePosition position,boolean visibility) {
        return FadesTransitionBuilder.getTransition(node.getChildLine(position),Duration.millis(1),visibility?1:0,visibility?0:1);
    }

    protected ParallelTransition getMovingElements(){
        TranslateTransition t1= RelativeTranslateTransition.build(information.second.get().getElement(), secondPoint,firstPoint, new Duration(1000));
        TranslateTransition t2= RelativeTranslateTransition.build(information.first.get().getElement(),firstPoint,secondPoint,new Duration(1000));
        return new ParallelTransition(t1,t2);
    }


}
