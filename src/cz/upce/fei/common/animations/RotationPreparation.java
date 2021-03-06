package cz.upce.fei.common.animations;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.NodePosition;
import cz.commons.graphics.RotationDirectionElement;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ElementInfo;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.layoutManager.RepairmanLayoutManager;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.common.animations.builders.BuilderRotationElement;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.common.events.ReferenceHelper;
import cz.upce.fei.common.events.RotationEvent;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class RotationPreparation implements IPreparation {

    private final RotationEvent event;
    private final BinaryTreeLayoutManager manager;
    private final List<Transition> moveParentsElements;
    private ParallelTransition reconstructionMoves;
    private RotationDirectionElement rotationDirectionElement;
    private final List<SwitchConnectorHelper> helpers = new ArrayList<>();

    public RotationPreparation(RotationEvent event, BinaryTreeLayoutManager manager, List<Transition> moveParentsElements, RotationDirectionElement element) {
        this.event = event;
        event.getTreeRestructure().getId();
        this.manager = manager;
        this.rotationDirectionElement = element;
        this.moveParentsElements = moveParentsElements;
        prepare();
    }

    private void prepare() {
        //repair tree layout structure...
        RepairmanLayoutManager repairman = new RepairmanLayoutManager(manager, event.getTreeRestructure());
        reconstructionMoves = getElementMoves(repairman.reconstruction());
        initHelpers();
    }

    private void initHelpers() {
        for (ReferenceHelper referenceHelper : event.getReferenceHelperList()) {
            ElementInfo info = manager.getElementInfo(referenceHelper.getNode());
            BinaryNodeWithLine graphicsElement = info.getElement();
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
                BinaryNodeWithLine oldRefGraphics = manager.getElementInfo(oldRefId).getElement();
                connectors.back = oldRefGraphics;
            }

            if (newRefId == null) {
                helper.disableVisibleForward();
                connectors.forward = !referenceHelper.isLeftNodePosition() ?
                        graphicsElement.getLeftChildConnector() : graphicsElement.getRightChildConnector();
            } else {
                BinaryNodeWithLine newRefGraphics = manager.getElementInfo(newRefId).getElement();
                connectors.forward = newRefGraphics;
            }
            helpers.add(helper);
        }

    }

    @Override
    public IAnimationBuilder getBuilder() {
        return new BuilderRotationElement(reconstructionMoves, helpers, moveParentsElements, rotationDirectionElement);
    }

    private ParallelTransition getElementMoves(List<MoveElementEvent> moves) {
        ParallelTransition pt = new ParallelTransition();
        for (MoveElementEvent move : moves) {
            BuilderAnimMoveNode builderAnimMoveNode = new BuilderAnimMoveNode(move.getOldPoint(), move.getNewPoint(), manager.getElementInfo(move.getElementId()).getElement());
            pt.getChildren().add(builderAnimMoveNode.getTranslateTransition());
        }
        return pt;
    }
}
