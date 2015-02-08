package cz.upce.fei.muller.binaryHeap.animationsRename;

import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.binaryHeap.animationsRename.builders.BuilderAddElement;
import cz.upce.fei.muller.binaryHeap.events.InsertNodeEvent;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class InsertPreparation implements IPreparation {

    private final WorkBinaryNodeInfo currentInformation;
    private final InsertNodeEvent event;
    private final BinaryTreeLayoutManager manager;
    private final Point2D creatingPoint;

    public InsertPreparation(InsertNodeEvent event, BinaryTreeLayoutManager manager, Point2D creatingPoint) {
        this.event = event;
        this.manager = manager;
        this.creatingPoint = creatingPoint;
        this.currentInformation = WorkBinaryNodeInfoBuilder.getWorkInfo(event.getNewNode().getId(),manager);
        preparation();
    }

    private void preparation() {
        BinaryHeapNode parent= currentInformation.getParent().getElement();
        LineElement element= parent.getChildLine(event.isLeftChild() ? NodePosition.LEFT : NodePosition.RIGHT);
        element.setOpacity(0);
        BinaryHeapNode current= currentInformation.get().getElement();
        current.setOpacity(0);
    }

    @Override
    public IAnimationBuilder getBuilder() {
        return new BuilderAddElement(manager.getNodePosition(event.getNewNode().getId()), creatingPoint,currentInformation,event.isLeftChild());
    }
}
