package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class SwapRootEndEventHandler extends StepEventHandler {

    final BinaryHeapNode parentLeaf;
    final LineElement lineElement;

    final BinaryHeapNode first;
    final BinaryHeapNode second;

    final boolean isLeft = false;

    public SwapRootEndEventHandler(LineElement lineElement, BinaryHeapNode parentLeaf, BinaryHeapNode first, BinaryHeapNode second) {
        this.lineElement = lineElement;
        this.parentLeaf = parentLeaf;
        this.first = first;
        this.second = second;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        System.out.println("handle forward");
        FadesTransitionBuilder.getTransition(first.getChildLine(NodePosition.LEFT),Duration.millis(1),1,0).play();
        FadesTransitionBuilder.getTransition(first.getChildLine(NodePosition.RIGHT),Duration.millis(1),1,0).play();
        lineElement.setStart(parentLeaf.getChildConnector(isLeft?1:0));
        lineElement.setEnd(first);
        FadesTransitionBuilder.getTransition(lineElement,Duration.millis(1),0,1).play();

    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        System.out.println("handle back");
    }
}
