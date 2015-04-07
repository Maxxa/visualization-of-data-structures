package cz.upce.fei.muller.TwoDTree.animations;

import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderAddElement;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderMoveToChild;
import cz.upce.fei.muller.TwoDTree.events.InsertNodeEvent;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class InsertPreparation implements IPreparation {

    private final WorkBinaryNodeInfo currentInformation;
    private final InsertNodeEvent event;
    private final BinaryTreeLayoutManager manager;
    private final Point2D creatingPoint;
    private final FindPlacePreparation findPlacePreparator;

    public InsertPreparation(InsertNodeEvent event, BinaryTreeLayoutManager manager, Point2D creatingPoint, FindPlacePreparation findPlacePreparator) {
        this.event = event;
        this.manager = manager;
        this.creatingPoint = creatingPoint;
        this.findPlacePreparator = findPlacePreparator;
        this.currentInformation = WorkBinaryNodeInfoBuilder.getWorkInfo(event.getNewNode().getId(), manager);
        preparation();
    }

    private void preparation() {
        //TODO
        TwoDGraphicsNode parent = currentInformation.getParent().getElement();
        LineElement element = parent.getChildLine(event.isLeftChild() ? NodePosition.LEFT : NodePosition.RIGHT);
        element.setOpacity(0);
        TwoDGraphicsNode current = currentInformation.get().getElement();
        current.setOpacity(0);
    }

    @Override
    public IAnimationBuilder getBuilder() {
        return
                findPlacePreparator == null ?
                        new BuilderAddElement(manager.getNodePosition(event.getNewNode().getId()), creatingPoint, currentInformation, event.isLeftChild()) :
                        new BuilderMoveToChild(
                                manager.getNodePosition(event.getNewNode().getId()),
                                creatingPoint,
                                currentInformation,
                                event.isLeftChild(),
                                findPlacePreparator.getMovings());

    }
}
