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
        //set line from parent leaf
        lineElementToLeaf.setEnd((BinaryHeapNode)oRoot.get().getElement());

        //set line from new root
        LineElement leftNew = ((BinaryHeapNode)nRoot.get().getElement()).getChildLine(NodePosition.LEFT);
        LineElement rightNew = ((BinaryHeapNode)nRoot.get().getElement()).getChildLine(NodePosition.RIGHT);

        LineElement leftOld = ((BinaryHeapNode)oRoot.get().getElement()).getChildLine(NodePosition.LEFT);
        LineElement rightOld =((BinaryHeapNode)oRoot.get().getElement()).getChildLine(NodePosition.RIGHT);

        leftNew.setEnd(leftOld.getEnd());
        rightNew.setEnd(rightOld.getEnd());

//        boolean tmp1= leftNew.isVisible();
//        boolean tmp2= rightNew.isVisible();
//
//        leftNew.setVisible(true);
//        rightNew.setVisible(true);

        leftOld.setEnd((BinaryHeapNode)oRoot.get().getElement());
        rightOld.setEnd((BinaryHeapNode)oRoot.get().getElement());
//        leftOld.setVisible(tmp1);
//        rightOld.setVisible(tmp2);

    }
}
