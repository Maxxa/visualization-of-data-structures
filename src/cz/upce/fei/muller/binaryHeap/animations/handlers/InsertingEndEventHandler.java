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
public class InsertingEndEventHandler extends StepEventHandler {

    private BinaryHeapNode parent;
    private BinaryHeapNode newElement;
    private LineElement line;

    public InsertingEndEventHandler(WorkBinaryNodeInfo currentInformation,boolean isLeft) {
        parent= currentInformation.getParent().getElement();
        newElement = currentInformation.get().getElement();
        line = parent.getChildLine(isLeft? NodePosition.LEFT:NodePosition.RIGHT);
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        line.setEnd(newElement);
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        line.setEnd(parent);
        line.setOpacity(0);
        line.setVisible(false);
    }
}
