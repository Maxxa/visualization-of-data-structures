package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.BinaryTreeHelper;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ElementInfo;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class RemovePreparation {

    private final ElementInfo removedElementInfo;
    private final BinaryTreeLayoutManager manager;

    public RemovePreparation(ElementInfo removedElement, BinaryTreeLayoutManager manager) {
        this.removedElementInfo = removedElement;
        this.manager = manager;
    }

    public BinaryHeapNode getRemovedElement(){
        return (BinaryHeapNode) removedElementInfo.getElement();
    }

    public boolean isLineToRemoved(){
        return removedElementInfo.getIdParent()!=null;
    }

    public LineElement getLineToRemoved(){
        if(isLineToRemoved()){
            ElementInfo infoParent =manager.getElementInfo(removedElementInfo.getIdParent());
            if(BinaryTreeHelper.getLeftChildIndex(infoParent.getIndexAtRow())==removedElementInfo.getIndexAtRow()){
                return ((BinaryHeapNode)infoParent.getElement()).getChildLine(NodePosition.LEFT);
            }else{
                return ((BinaryHeapNode)infoParent.getElement()).getChildLine(NodePosition.RIGHT);
            }
        }
        return null;
    }

    public void executeRemove(){
        if(isLineToRemoved()){
            getLineToRemoved().setEnd((BinaryHeapNode) manager.getElementInfo(removedElementInfo.getIdParent()).getElement());
        }
        manager.getCanvas().getChildren().remove(removedElementInfo.getElement());
        manager.getCanvas().getChildren().remove(((BinaryHeapNode)removedElementInfo.getElement()).getChildLine(NodePosition.LEFT));
        manager.getCanvas().getChildren().remove(((BinaryHeapNode)removedElementInfo.getElement()).getChildLine(NodePosition.RIGHT));
    }

}
