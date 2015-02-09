package cz.upce.fei.muller.binaryHeap.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.Element;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.animations.builders.SwapInformation;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.event.ActionEvent;


/**
 * @author Vojtěch Müller
 */
public class FadesTransitionHandler extends StepEventHandler{
    private final boolean visibility;
    private final SwapInformation information;

    public FadesTransitionHandler(boolean visibility, SwapInformation information) {
        this.visibility = visibility;
        this.information = information;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        buildFadesAfterEnd((BinaryHeapNode)information.getSecond().get().getElement(),(BinaryHeapNode)information.getFirst().get().getElement());
    }



    @Override
    protected void handleBack(ActionEvent actionEvent) {
        buildFadesAfterEnd((BinaryHeapNode)information.getFirst().get().getElement(),(BinaryHeapNode)information.getSecond().get().getElement());

    }

    private void buildFadesAfterEnd(BinaryHeapNode newNode,BinaryHeapNode oldNode){
        if(!information.getFirst().hasLeft()){
            disable(newNode.getChildLine(NodePosition.LEFT));
        }

        if(!information.getFirst().hasRight()){
            disable(newNode.getChildLine(NodePosition.RIGHT));
        }
        if(!information.getSecond().hasLeft()){
            disable(oldNode.getChildLine(NodePosition.LEFT));
        }
        if(!information.getSecond().hasRight()){
            disable(oldNode.getChildLine(NodePosition.RIGHT));
        }
    }

    void disable(LineElement line){
        line.setOpacity(0);
        line.setVisible(false);
    }
}
