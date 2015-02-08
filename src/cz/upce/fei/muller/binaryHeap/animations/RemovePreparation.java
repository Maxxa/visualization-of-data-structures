package cz.upce.fei.muller.binaryHeap.animations;

import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.*;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class RemovePreparation{

    private final WorkBinaryNodeInfo workerInfo;
    private final BinaryTreeLayoutManager manager;

    private LineElement lineElement = null;

    public RemovePreparation(Integer idElement, BinaryTreeLayoutManager manager) {
        this.workerInfo = WorkBinaryNodeInfoBuilder.getWorkInfo(idElement,manager);
        this.manager = manager;
        if(isLineToRemoved()){
            boolean isLeft= BinaryTreeHelper.getLeftChildIndex(workerInfo.getParent().getIndexAtRow())== workerInfo.get().getIndexAtRow();
            lineElement =((BinaryHeapNode) workerInfo.getParent().getElement()).getChildLine(isLeft?NodePosition.LEFT:NodePosition.RIGHT);
        }
    }

    public BinaryHeapNode getRemovedElement(){
        return (BinaryHeapNode) workerInfo.get().getElement();
    }

    public boolean isLineToRemoved(){
        return workerInfo.hasParent();
    }

    public LineElement getLineToRemoved(){
        return lineElement;
    }

    public void executeRemove(){
        if(isLineToRemoved()){
            LineElement lineFromParentToRemoved =getLineToRemoved();
            lineFromParentToRemoved.setVisible(false);
            lineFromParentToRemoved.setOpacity(0);
            lineFromParentToRemoved.setEnd((BinaryHeapNode) workerInfo.getParent().getElement());
        }
        manager.getCanvas().getChildren().remove(workerInfo.get().getElement());
        manager.getCanvas().getChildren().remove(((BinaryHeapNode) workerInfo.get().getElement()).getChildLine(NodePosition.LEFT));
        manager.getCanvas().getChildren().remove(((BinaryHeapNode) workerInfo.get().getElement()).getChildLine(NodePosition.RIGHT));
    }

}
