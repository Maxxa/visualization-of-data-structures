package cz.upce.fei.muller.treap.animations;

import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.treap.animations.builders.BuilderAddElement;
import cz.upce.fei.muller.treap.animations.builders.BuilderMoveToChild;
import cz.upce.fei.muller.treap.events.InsertNodeEvent;
import cz.upce.fei.muller.treap.graphics.TreapGraphicElement;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class InsertPreparation implements IPreparation {

    private final WorkBinaryNodeInfo currentInformation;
    private final InsertNodeEvent event;
    private final BinaryTreeLayoutManager manager;
    private final Point2D creatingPoint;
    private final boolean isFindingPlace;

    public InsertPreparation(InsertNodeEvent event, BinaryTreeLayoutManager manager, Point2D creatingPoint,boolean isFindingPlace) {
        this.event = event;
        this.manager = manager;
        this.creatingPoint = creatingPoint;
        this.isFindingPlace = isFindingPlace;
        this.currentInformation = WorkBinaryNodeInfoBuilder.getWorkInfo(event.getNewNode().getId(), manager);
        preparation();
    }

    private void preparation() {
        TreapGraphicElement parent = currentInformation.getParent().getElement();
        LineElement element = parent.getChildLine(event.isLeftChild() ? NodePosition.LEFT : NodePosition.RIGHT);
        element.setOpacity(0);
        TreapGraphicElement current = currentInformation.get().getElement();
        current.setOpacity(0);
    }

    @Override
    public IAnimationBuilder getBuilder() {
        return
                !isFindingPlace?
                        new BuilderAddElement(manager.getNodePosition(event.getNewNode().getId()), creatingPoint, currentInformation, event.isLeftChild()) :
                        new BuilderMoveToChild(
                                manager.getNodePosition(event.getNewNode().getId()),
                                creatingPoint,
                                currentInformation,
                                event.isLeftChild());

    }
}
