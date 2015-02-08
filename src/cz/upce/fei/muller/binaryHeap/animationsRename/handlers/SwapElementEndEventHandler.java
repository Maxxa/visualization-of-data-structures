package cz.upce.fei.muller.binaryHeap.animationsRename.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.ConnectibleElement;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.muller.binaryHeap.animationsRename.builders.SwapInformation;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.event.ActionEvent;

/**
 * @author Vojtěch Müller
 */
public class SwapElementEndEventHandler extends StepEventHandler {


    private final SwapInformation information;

    public SwapElementEndEventHandler(SwapInformation information) {
        this.information = information;
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
        BinaryHeapNode newNode = (BinaryHeapNode)newNodeInfo.get().getElement();
        BinaryHeapNode oldNode =(BinaryHeapNode)oldNodeInfo.get().getElement();
        if(information.getFirstParent()!=null){
            information.getFirstParent().getChildLine(information.getFirstParentPosition()).setEnd(newNode);
        }

        LineElement leftOldLine = oldNode.getChildLine(NodePosition.LEFT);
        LineElement rightOldLine = oldNode.getChildLine(NodePosition.RIGHT);

        LineElement leftNewLine = newNode.getChildLine(NodePosition.LEFT);
        LineElement rightNewLine = newNode.getChildLine(NodePosition.RIGHT);

        // prohozeni pripojnych bodu
        ConnectibleElement tmp1 = leftNewLine.getEnd();
        ConnectibleElement tmp2 = rightNewLine.getEnd();

        if(information.getPositionFromFirst().equals(NodePosition.LEFT)){
            leftNewLine.setEnd(oldNode);
            rightNewLine.setEnd(rightOldLine.getEnd());
        }else{
            leftNewLine.setEnd(leftOldLine.getEnd());
            rightNewLine.setEnd(oldNode);
        }
        leftOldLine.setEnd(tmp1);
        rightOldLine.setEnd(tmp2);


        // nastaveni sprave visibility
//        boolean tmpB1 = leftOldLine.isVisible();
//        boolean tmpB2 = rightOldLine.isVisible();
//        leftOldLine.setVisible(leftNewLine.isVisible());
//        rightOldLine.setVisible(rightNewLine.isVisible());
//        leftNewLine.setVisible(tmpB1);
//        rightNewLine.setVisible(tmpB2);

    }
}
