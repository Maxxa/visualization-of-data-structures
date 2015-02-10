package cz.upce.fei.muller.binaryHeap.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.ConnectibleElement;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.ElementInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.muller.binaryHeap.animations.builders.SwapInformation;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.event.ActionEvent;

/**
 * @author Vojtěch Müller
 */
public class SwapElementEndEventHandler extends StepEventHandler {

    private final SwapInformation information;
    private final ElementInfo firstParent;

    public SwapElementEndEventHandler(SwapInformation information) {
        this.information = information;
        this.firstParent= information.getFirst().getParent();
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        buildHandle(information.getSecond(),information.getFirst());
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        buildHandle(information.getFirst(),information.getSecond());
    }

    private void buildHandle(WorkBinaryNodeInfo newNodeInfo,WorkBinaryNodeInfo oldNodeInfo){
        BinaryHeapNode newNode = newNodeInfo.get().getElement();
        BinaryHeapNode oldNode = oldNodeInfo.get().getElement();
        if(firstParent!=null){
            LineElement lineFromFirst = ((BinaryHeapNode)firstParent.getElement()).getChildLine(information.getFirstParentPosition());
            lineFromFirst.setEnd(newNode);
        }

        LineElement lineFromNewLeft = newNode.getChildLine(NodePosition.LEFT);
        LineElement lineFromNewRight = newNode.getChildLine(NodePosition.RIGHT);

        LineElement lineFromOldLeft = oldNode.getChildLine(NodePosition.LEFT);
        LineElement lineFromOldRight= oldNode.getChildLine(NodePosition.RIGHT);

        if(information.getPositionFromFirst().equals(NodePosition.LEFT)){
            lineFromNewLeft.setEnd(oldNode);
            lineFromNewRight.setEnd(information.getFirst().hasRight()?(BinaryHeapNode)information.getFirst().getRightChild().getElement():newNode);
        }else{
            lineFromNewLeft.setEnd(information.getFirst().hasLeft()?(BinaryHeapNode)information.getFirst().getLeftChild().getElement():newNode);
            lineFromNewRight.setEnd(oldNode);
        }

        lineFromOldLeft.setEnd(information.getSecond().hasLeft()?(BinaryHeapNode)information.getSecond().getLeftChild().getElement():oldNode.getRightChildConnector());
        lineFromOldRight.setEnd(information.getSecond().hasRight()?(BinaryHeapNode)information.getSecond().getRightChild().getElement():oldNode.getLeftChildConnector());

    }

}
