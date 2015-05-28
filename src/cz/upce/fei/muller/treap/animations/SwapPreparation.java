package cz.upce.fei.muller.treap.animations;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.ConnectibleElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeHelper;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.common.animations.ConnectorHelper;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.treap.animations.builders.BuilderSwapNode;
import cz.upce.fei.muller.treap.animations.builders.DefaultSwapInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class SwapPreparation implements IPreparation {

    private final DefaultSwapInformation information;

    private final List<SwitchConnectorHelper> helpers = new ArrayList<>();

    public SwapPreparation(ITreeLayoutManager manager, WorkBinaryNodeInfo infoFirstElement, WorkBinaryNodeInfo infoSecondElement) {
        information = new DefaultSwapInformation(
                infoFirstElement, infoSecondElement,
                manager.getNodePosition(infoFirstElement.get().getElement().getElementId()),
                manager.getNodePosition(infoSecondElement.get().getElement().getElementId())
        );
        initParent(infoFirstElement, infoSecondElement);
        initParent(infoSecondElement, infoFirstElement);
        initChilds();
    }

    private void initParent(WorkBinaryNodeInfo first, WorkBinaryNodeInfo second) {
        if (first.hasParent() && first.get().getIdParent()!= second.get().getElement().getElementId()) {
            NodePosition position = getParentPosition(first.getParent().getIndexAtRow(), first.get().getIndexAtRow());
            SwitchConnectorHelper helper = new SwitchConnectorHelper(first.getParent(), position,
                    new ConnectorHelper(
                            (ConnectibleElement) second.get().getElement(),
                            (ConnectibleElement) first.get().getElement()));
            helpers.add(helper);
        }

    }

    //THIS METHOD IS BIG SHIT>...>but i dont know how to create easy:(
    private void initChilds() {
        WorkBinaryNodeInfo first = information.infoFirstElement;
        WorkBinaryNodeInfo second = information.infoSecondElement;
        SwitchConnectorHelper helpFirstLeft = new SwitchConnectorHelper(first.get(), NodePosition.LEFT);
        SwitchConnectorHelper helpFirstRight = new SwitchConnectorHelper(first.get(), NodePosition.RIGHT);
        SwitchConnectorHelper helpSecondLeft = new SwitchConnectorHelper(second.get(), NodePosition.LEFT);
        SwitchConnectorHelper helpSecondRight = new SwitchConnectorHelper(second.get(), NodePosition.RIGHT);
        ///////////////////////////
        /// FIRST LEFT first-back second-forward,
        ///////////////////////////
        if (first.hasLeft()) {
            if (first.getLeftChild().getElement().getElementId() == second.get().getElement().getElementId()) {
                //jedna se o leveho syna stejny atp
                helpFirstLeft.getConnectors().back = ((BinaryNodeWithLine) second.get().getElement());
                helpSecondLeft.getConnectors().forward = ((BinaryNodeWithLine) first.get().getElement());
            } else {
                helpFirstLeft.getConnectors().back = ((BinaryNodeWithLine) first.getLeftChild().getElement());
                helpSecondLeft.getConnectors().forward = ((BinaryNodeWithLine) first.getLeftChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstLeft.getConnectors().back = ((BinaryNodeWithLine) first.get().getElement()).getRightChildConnector();
            helpSecondLeft.getConnectors().forward = ((BinaryNodeWithLine) second.get().getElement()).getRightChildConnector();
            helpFirstLeft.disableVisibleBack();
            helpSecondLeft.disableVisibleForward();
        }

        ///////////////////////////
        /// FIRST RIGHT first-back second-forward,
        ///////////////////////////
        if (first.hasRight()) {
            if (first.getRightChild().getElement().getElementId() == second.get().getElement().getElementId()) {
                helpFirstRight.getConnectors().back = ((BinaryNodeWithLine) second.get().getElement());
                helpSecondRight.getConnectors().forward = ((BinaryNodeWithLine) first.get().getElement());
            } else {
                helpFirstRight.getConnectors().back = ((BinaryNodeWithLine) first.getRightChild().getElement());
                helpSecondRight.getConnectors().forward = ((BinaryNodeWithLine) first.getRightChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstRight.getConnectors().back = ((BinaryNodeWithLine) first.get().getElement()).getLeftChildConnector();
            helpSecondRight.getConnectors().forward = ((BinaryNodeWithLine) second.get().getElement()).getLeftChildConnector();
            helpFirstRight.disableVisibleBack();
            helpSecondRight.disableVisibleForward();
        }


        ///////////////////////////
        /// SECOND LEFT first-forward second-back,
        ///////////////////////////
        if (second.hasLeft()) {
            if (second.getLeftChild().getElement().getElementId() == first.get().getElement().getElementId()) {
                //jedna se o leveho syna stejny atp
                helpFirstLeft.getConnectors().forward = ((BinaryNodeWithLine) first.get().getElement());
                helpSecondLeft.getConnectors().back = ((BinaryNodeWithLine) second.get().getElement());
            } else {
                helpFirstLeft.getConnectors().forward = ((BinaryNodeWithLine) second.getLeftChild().getElement());
                helpSecondLeft.getConnectors().back = ((BinaryNodeWithLine) second.getLeftChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstLeft.getConnectors().forward = ((BinaryNodeWithLine) first.get().getElement()).getRightChildConnector();
            helpSecondLeft.getConnectors().back = ((BinaryNodeWithLine) second.get().getElement()).getRightChildConnector();
            helpFirstLeft.disableVisibleForward();
            helpSecondLeft.disableVisibleBack();
        }
        ///////////////////////////
        /// SECOND RIGHT first-forward second-back,
        ///////////////////////////
        if (second.hasRight()) {
            if (second.getRightChild().getElement().getElementId() == first.get().getElement().getElementId()) {
                helpFirstRight.getConnectors().forward = ((BinaryNodeWithLine) first.get().getElement());
                helpSecondRight.getConnectors().back = ((BinaryNodeWithLine) second.get().getElement());
            } else {
                helpFirstRight.getConnectors().forward = ((BinaryNodeWithLine) second.getRightChild().getElement());
                helpSecondRight.getConnectors().back = ((BinaryNodeWithLine) second.getRightChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstRight.getConnectors().forward = ((BinaryNodeWithLine) first.get().getElement()).getLeftChildConnector();
            helpSecondRight.getConnectors().back = ((BinaryNodeWithLine) second.get().getElement()).getLeftChildConnector();
            helpFirstRight.disableVisibleForward();
            helpSecondRight.disableVisibleBack();
        }

        helpers.add(helpFirstLeft);
        helpers.add(helpFirstRight);
        helpers.add(helpSecondLeft);
        helpers.add(helpSecondRight);
    }

    @Override
    public IAnimationBuilder getBuilder() {
        return new BuilderSwapNode(information,helpers);
    }

    private NodePosition getParentPosition(Integer indexAtRow, Integer indexAtRowChild) {
        return BinaryTreeHelper.getLeftChildIndex(indexAtRow).equals(indexAtRowChild) ? NodePosition.LEFT : NodePosition.RIGHT;
    }
}
