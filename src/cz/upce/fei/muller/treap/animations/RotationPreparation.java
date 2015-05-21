package cz.upce.fei.muller.treap.animations;

import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ElementInfo;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.layoutManager.RepairmanLayoutManager;
import cz.upce.fei.common.animations.ConnectorHelper;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.treap.animations.builders.BuilderRotationElement;
import cz.upce.fei.muller.treap.events.ReferenceHelper;
import cz.upce.fei.muller.treap.events.RotationEvent;
import cz.upce.fei.muller.treap.graphics.TreapGraphicElement;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class RotationPreparation implements IPreparation {

    private final RotationEvent event;
    private final BinaryTreeLayoutManager manager;
    private final List<TranslateTransition> moveParentsElements;
    private ParallelTransition reconstructionMoves;

    private final List<SwitchConnectorHelper> helpers = new ArrayList<>();

    public RotationPreparation(RotationEvent event, BinaryTreeLayoutManager manager, List<TranslateTransition> moveParentsElements) {
        this.event = event;
        this.manager = manager;
        this.moveParentsElements = moveParentsElements;
        prepare();
    }

    private void prepare() {
        //repair tree layout structure...
        RepairmanLayoutManager repairment = new RepairmanLayoutManager(manager, event.getTreeRestructure());
        reconstructionMoves = getElementMovings(repairment.reconstruction());
        initHelpers();
    }

    private void initHelpers() {
        for (ReferenceHelper referenceHelper : event.getReferenceHelperList()) {
            System.out.println(referenceHelper);
            ElementInfo info = manager.getElementInfo(referenceHelper.getNode());
            TreapGraphicElement graphicsElement = info.getElement();
            SwitchConnectorHelper helper = new SwitchConnectorHelper(info,
                    referenceHelper.isLeftNodePosition() ? NodePosition.LEFT : NodePosition.RIGHT);

            Integer newRefId = referenceHelper.getNewReference();
            Integer oldRefId = referenceHelper.getOldReference();

            ConnectorHelper connectors = helper.getConnectors();

            if (oldRefId == null) {
                helper.disableVisibleBack();
                connectors.back = !referenceHelper.isLeftNodePosition() ?
                        graphicsElement.getLeftChildConnector() : graphicsElement.getRightChildConnector();
            } else {
                TreapGraphicElement oldRefGraphics = manager.getElementInfo(oldRefId).getElement();
                connectors.back =oldRefGraphics;
            }

            if (newRefId == null) {
                helper.disableVisibleForward();
                connectors.forward = !referenceHelper.isLeftNodePosition() ?
                        graphicsElement.getLeftChildConnector() : graphicsElement.getRightChildConnector();
            } else {
                TreapGraphicElement newRefGraphics = manager.getElementInfo(newRefId).getElement();
                connectors.forward = newRefGraphics;
            }
            helpers.add(helper);
        }

    }

    @Override
    public IAnimationBuilder getBuilder() {
        return new BuilderRotationElement(reconstructionMoves, helpers,moveParentsElements);
    }

    private ParallelTransition getElementMovings(List<MoveElementEvent> moves) {
        ParallelTransition pt = new ParallelTransition();
        for (MoveElementEvent move : moves) {
            BuilderAnimMoveNode buildeMove = new BuilderAnimMoveNode(move.getOldPoint(), move.getNewPoint(), manager.getElementInfo(move.getElementId()).getElement());
            pt.getChildren().add(buildeMove.getTranslateTransition());
        }
        return pt;
    }
}
