package cz.upce.fei.muller.binaryHeap.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.ElementInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.utils.FadesTransitionBuilder;
import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.binaryHeap.animations.handlers.FadesTransitionHandler;
import cz.upce.fei.muller.binaryHeap.animations.handlers.SwapElementEndEventHandler;
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

    public BuilderSwapNode(WorkBinaryNodeInfo infoFirst, WorkBinaryNodeInfo infoSecond,
                           Point2D firstPoint, Point2D secondPoint,
                           NodePosition positionFromFirst, NodePosition firstParentPosition) {
        information.first=infoFirst;
        information.second=infoSecond;
        this.firstPoint=firstPoint;
        this.secondPoint=secondPoint;
        information.positionFromFirst=positionFromFirst ;
        information.firstParentPosition = firstParentPosition;

    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = getMovingElements();
        SequentialTransition sq = new SequentialTransition(getLinesFades(true),pt, getLinesFades(false));
        pt.setOnFinished(setFinishedHandler());
        return new ParallelTransition(sq);
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwapElementEndEventHandler(information);
    }

    private ParallelTransition getLinesFades(boolean visibility) {
        ParallelTransition pt = new ParallelTransition();

        if(information.first.hasParent()){ // first swapping node have parent...
            pt.getChildren().add(getFadeTransition((BinaryHeapNode) information.first.getParent().getElement(), information.firstParentPosition, visibility));
        }

        addFadeToTransition(pt,information.first.get(), NodePosition.LEFT,visibility);
        addFadeToTransition(pt,information.second.get(),NodePosition.LEFT,visibility);
        addFadeToTransition(pt,information.first.get(), NodePosition.RIGHT,visibility);
        addFadeToTransition(pt,information.second.get(),NodePosition.RIGHT,visibility);
//        initLeftLinesFades(visibility,pt);
//        initRightLinesFades(visibility,pt);
        pt.setOnFinished(new FadesTransitionHandler(visibility,information));
        return pt;
    }

    private void initLeftLinesFades(boolean visibility, ParallelTransition pt) {
        if(information.first.hasLeft()){
            if(information.second.hasLeft()){ // node first and second have left child must evry line adding...
                addFadeToTransition(pt,information.first.get(), NodePosition.LEFT,visibility);
                addFadeToTransition(pt,information.second.get(),NodePosition.LEFT,visibility);
            }else{
                if(visibility){
                    addFadeToTransition(pt,information.first.get(),NodePosition.LEFT,visibility);
                }else{
                    addFadeToTransition(pt,information.second.get(),NodePosition.LEFT,visibility);
                }
            }
        }else{
            if(information.second.hasLeft()){
                if(visibility){
                    addFadeToTransition(pt,information.second.get(),NodePosition.RIGHT,visibility);
                }else{
                    addFadeToTransition(pt,information.first.get(),NodePosition.RIGHT,visibility);
                }
            }
        }
    }

    private void initRightLinesFades(boolean visibility, ParallelTransition pt) {
        if(information.first.hasRight()){
            if(information.second.hasRight()){
                addFadeToTransition(pt,information.first.get(), NodePosition.RIGHT,visibility);
                addFadeToTransition(pt,information.second.get(),NodePosition.RIGHT,visibility);
            }else{
                if(visibility){
                    addFadeToTransition(pt,information.first.get(),NodePosition.RIGHT,visibility);
                }else{
                    addFadeToTransition(pt,information.second.get(),NodePosition.RIGHT,visibility);
                }
            }
        }else{
            if(information.second.hasRight()){
                if(visibility){
                    addFadeToTransition(pt,information.second.get(),NodePosition.RIGHT,visibility);
                }else{
                    addFadeToTransition(pt,information.first.get(),NodePosition.RIGHT,visibility);
                }

            }
        }
    }

    private void addFadeToTransition(ParallelTransition pt, ElementInfo elementInfo,NodePosition position,boolean visibility){
        pt.getChildren().add(getFadeTransition((BinaryHeapNode)elementInfo.getElement(),position, visibility));
    }

    private FadeTransition getFadeTransition(BinaryHeapNode node,NodePosition position, boolean visibility) {
        final LineElement childLine = node.getChildLine(position);
        int from = visibility?1:0;
        int to = visibility?0:1;
        FadeTransition ft = FadesTransitionBuilder.getTransition(childLine,Duration.millis(1),from ,to);
        return ft;
    }

    protected ParallelTransition getMovingElements(){
        TranslateTransition t1= RelativeTranslateTransition.build(information.second.get().getElement(), secondPoint,firstPoint, new Duration(1000));
        TranslateTransition t2= RelativeTranslateTransition.build(information.first.get().getElement(),firstPoint,secondPoint,new Duration(1000));
        return new ParallelTransition(t1,t2);
    }


}
