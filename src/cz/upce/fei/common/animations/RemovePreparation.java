package cz.upce.fei.common.animations;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeHelper;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;

/**
 * @author Vojtěch Müller
 */
public class RemovePreparation {

    private final WorkBinaryNodeInfo workerInfo;
    private final BinaryTreeLayoutManager manager;

    private LineElement lineElement = null;
    private boolean isLeftLine = false;

    public RemovePreparation(Integer idElement, BinaryTreeLayoutManager manager) {
        this.workerInfo = WorkBinaryNodeInfoBuilder.getWorkInfo(idElement,manager);
        this.manager = manager;
        if(isLineToRemoved()){
            isLeftLine = BinaryTreeHelper.getLeftChildIndex(workerInfo.getParent().getIndexAtRow())== workerInfo.get().getIndexAtRow();
            lineElement =((BinaryNodeWithLine) workerInfo.getParent().getElement()).getChildLine(isLeftLine ?NodePosition.LEFT:NodePosition.RIGHT);
        }
    }

    public BinaryNodeWithLine getRemovedElement(){
        return (BinaryNodeWithLine) workerInfo.get().getElement();
    }

    public boolean isLineToRemoved(){
        return workerInfo.hasParent();
    }

    public LineElement getLineToRemoved(){
        return lineElement;
    }

    public void executeRemove(){
        if(isLineToRemoved()){
            WorkBinaryNodeInfo parent = WorkBinaryNodeInfoBuilder.getWorkInfo(workerInfo.getParent().getElement().getElementId(),manager);
            if(isLeftLine && !parent.hasLeft() || !isLeftLine&&!parent.hasRight()){
                LineElement lineFromParentToRemoved =getLineToRemoved();
                lineFromParentToRemoved.setVisible(false);
                lineFromParentToRemoved.setOpacity(0);
                lineFromParentToRemoved.setEnd((BinaryNodeWithLine) workerInfo.getParent().getElement());
            }
        }
        manager.getCanvas().getChildren().remove(workerInfo.get().getElement());
        manager.getCanvas().getChildren().remove(((BinaryNodeWithLine) workerInfo.get().getElement()).getChildLine(NodePosition.LEFT));
        manager.getCanvas().getChildren().remove(((BinaryNodeWithLine) workerInfo.get().getElement()).getChildLine(NodePosition.RIGHT));
    }

}
