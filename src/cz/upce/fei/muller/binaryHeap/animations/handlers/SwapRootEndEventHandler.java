package cz.upce.fei.muller.binaryHeap.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.event.ActionEvent;

/**
 * @author Vojtěch Müller
 */
public class SwapRootEndEventHandler extends StepEventHandler {

    final LineElement lineElementToLeaf;

    final WorkBinaryNodeInfo oldRoot;
    final WorkBinaryNodeInfo newRoot;

    public SwapRootEndEventHandler(LineElement lineElement, WorkBinaryNodeInfo first, WorkBinaryNodeInfo second) {
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

    private void buildHandle(WorkBinaryNodeInfo oRoot,WorkBinaryNodeInfo nRoot){
        BinaryHeapNode oldR = oRoot.get().getElement();
        BinaryHeapNode newR = nRoot.get().getElement();

        //set line from parent leaf
        lineElementToLeaf.setEnd(oldR);

        //set line from new root
        LineElement leftNew = newR.getChildLine(NodePosition.LEFT);
        LineElement rightNew = newR.getChildLine(NodePosition.RIGHT);

        LineElement leftOld = oldR.getChildLine(NodePosition.LEFT);
        LineElement rightOld = oldR.getChildLine(NodePosition.RIGHT);

        leftNew.setEnd(leftOld.getEnd());
        rightNew.setEnd(rightOld.getEnd());
        leftOld.setEnd(oldR);
        rightOld.setEnd(oldR);

        leftOld.setOpacity(0);
        rightNew.setOpacity(0);
        leftOld.setVisible(false);
        rightOld.setVisible(false);

    }
}
