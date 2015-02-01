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
public class SwapRootEndEventHandler extends StepEventHandler {

    final LineElement lineElementToLeaf;

    final BinaryHeapNode oldRoot;
    final BinaryHeapNode newRoot;

    public SwapRootEndEventHandler(LineElement lineElement, BinaryHeapNode first, BinaryHeapNode second) {
        this.lineElementToLeaf = lineElement;
        this.oldRoot = first;
        this.newRoot = second;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        buildHandle(oldRoot,newRoot);
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        buildHandle(newRoot,oldRoot);
    }

    private void buildHandle(BinaryHeapNode oRoot,BinaryHeapNode nRoot){
        //set line from parent leaf
        lineElementToLeaf.setEnd(oRoot);

        //set line from new root
        LineElement leftNew = nRoot.getChildLine(NodePosition.LEFT);
        LineElement rightNew = nRoot.getChildLine(NodePosition.RIGHT);

        LineElement leftOld = oRoot.getChildLine(NodePosition.LEFT);
        LineElement rightOld = oRoot.getChildLine(NodePosition.RIGHT);

        leftNew.setEnd(leftOld.getEnd());
        rightNew.setEnd(rightOld.getEnd());
        leftNew.setVisible(true);
        rightNew.setVisible(true);

        leftOld.setEnd(oRoot);
        rightOld.setEnd(oRoot);
        leftOld.setVisible(false);
        rightOld.setVisible(false);

    }
}
