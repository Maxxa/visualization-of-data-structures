package cz.upce.fei.muller.treap.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.event.ActionEvent;

/**
 * @author Vojtěch Müller
 */
public class InsertingEndEventHandler extends StepEventHandler {

    private final boolean isLeft;
    private TwoDGraphicsNode parent;
    private TwoDGraphicsNode newElement;
    private LineElement line;

    public InsertingEndEventHandler(WorkBinaryNodeInfo currentInformation, boolean isLeft) {
        this.isLeft = isLeft;
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
        line.setEnd(!isLeft ? parent.getLeftChildConnector():parent.getRightChildConnector());
        line.setOpacity(0);
        line.setVisible(false);
    }
}
