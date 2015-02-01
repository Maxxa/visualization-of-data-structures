package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import cz.upce.fei.muller.binaryHeap.structure.BinaryHeap;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class SwapRootEndEventHandler extends StepEventHandler {

    final BinaryHeapNode parentLeaf;
    final LineElement lineElementToLeaf;

    final BinaryHeapNode oldRoot;
    final BinaryHeapNode newRoot;

    public SwapRootEndEventHandler(LineElement lineElement, BinaryHeapNode parentLeaf, BinaryHeapNode first, BinaryHeapNode second) {
        this.lineElementToLeaf = lineElement;
        this.parentLeaf = parentLeaf;
        this.oldRoot = first;
        this.newRoot = second;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        System.out.println("moving end - forward ");
        buildHandle(oldRoot,newRoot);
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        System.out.println("moving end - back");
        buildHandle(newRoot,oldRoot);
    }

    private void buildHandle(BinaryHeapNode nRoot,BinaryHeapNode oRoot){
        //set line from parent leaf
        lineElementToLeaf.setEnd(nRoot);

        //set line from new root
        LineElement left = nRoot.getChildLine(NodePosition.LEFT);
        LineElement right = nRoot.getChildLine(NodePosition.RIGHT);
        left.setStart(oRoot.getLeftChildConnector());
        right.setStart(oRoot.getRightChildConnector());
        oRoot.setChildLine(left, NodePosition.LEFT);
        oRoot.setChildLine(right, NodePosition.RIGHT);

//        //show line to leaf
//        showLine(lineElementToLeaf);
//        //show line from root
//        showLine(left);
//        showLine(right);
    }

    private void showLine(LineElement line){
//        FadesTransitionBuilder.getTransition(line,Duration.millis(1),0,1).play();
    }

}
