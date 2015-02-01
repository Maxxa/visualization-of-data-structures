package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.ConnectibleElement;
import cz.commons.graphics.LineElement;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.event.ActionEvent;

/**
 * @author Vojtěch Müller
 */
public class SwapElementEndEventHandler extends StepEventHandler {


    private final BuilderSwapNode.Information information;

    public SwapElementEndEventHandler(BuilderSwapNode.Information information) {
        this.information = information;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        buildHandle(information.second,information.first);
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        buildHandle(information.first,information.second);
    }

    private void buildHandle(BinaryHeapNode newNode,BinaryHeapNode oldNode){
        if(information.firstParent!=null){
            information.firstParent.getChildLine(information.firstParentPosition).setEnd(newNode);
        }

        LineElement leftOldLine = oldNode.getChildLine(NodePosition.LEFT);
        LineElement rightOldLine = oldNode.getChildLine(NodePosition.RIGHT);

        LineElement leftNewLine = newNode.getChildLine(NodePosition.LEFT);
        LineElement rightNewLine = newNode.getChildLine(NodePosition.RIGHT);

        // prohozeni pripojnych bodu
        ConnectibleElement tmp1 = leftNewLine.getEnd();
        ConnectibleElement tmp2 = rightNewLine.getEnd();

        if(information.positionFromFirst.equals(NodePosition.LEFT)){
            leftNewLine.setEnd(oldNode);
            rightNewLine.setEnd(rightOldLine.getEnd());
        }else{
            leftNewLine.setEnd(leftOldLine.getEnd());
            rightNewLine.setEnd(oldNode);
        }
        leftOldLine.setEnd(tmp1);
        rightOldLine.setEnd(tmp2);


        // nastaveni sprave visibility
        boolean tmpB1 = leftOldLine.isVisible();
        boolean tmpB2 = rightOldLine.isVisible();
        leftOldLine.setVisible(leftNewLine.isVisible());
        rightOldLine.setVisible(rightNewLine.isVisible());
        leftNewLine.setVisible(tmpB1);
        rightNewLine.setVisible(tmpB2);

    }
}
