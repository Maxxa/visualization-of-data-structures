package cz.upce.fei.muller.TwoDTree.animations;

import cz.commons.graphics.ConnectibleElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeHelper;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderSwapNode;
import cz.upce.fei.muller.TwoDTree.animations.builders.ConnectorHelper;
import cz.upce.fei.muller.TwoDTree.animations.builders.DefaultSwapInformation;
import cz.upce.fei.muller.TwoDTree.animations.builders.SwapHelper;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class SwapPreparation implements IPreparation {

    private final DefaultSwapInformation information;

    private final List<SwapHelper> helpers = new ArrayList<>();

    public SwapPreparation(ITreeLayoutManager manager, WorkBinaryNodeInfo infoFirstElement, WorkBinaryNodeInfo infoSecondElement) {
        information = new DefaultSwapInformation(
                infoFirstElement, infoSecondElement,
                manager.getNodePosition(infoFirstElement.get().getElement().getElementId()),
                manager.getNodePosition(infoSecondElement.get().getElement().getElementId())
        );
        System.out.println(infoFirstElement);
        System.out.println(infoSecondElement);
        initParent(infoFirstElement, infoSecondElement);
        initParent(infoSecondElement, infoFirstElement);
        initChilds();
    }

    private void initParent(WorkBinaryNodeInfo first, WorkBinaryNodeInfo second) {
        System.out.print(first.hasParent() + "  " + second.get().getElement().getElementId() + "   ");
        System.out.println(first.hasParent() ? first.get().getIdParent() : "");
        if (!first.hasParent() && first.get().getIdParent().equals(second.get().getElement().getElementId())) {
            NodePosition position = getParentPosition(first.getParent().getIndexAtRow(), first.get().getIndexAtRow());
            SwapHelper helper = new SwapHelper(first.getParent(), position,
                    new ConnectorHelper(
                            (ConnectibleElement) second.get().getElement(),
                            (ConnectibleElement) first.get().getElement()));
            helpers.add(helper);
            System.out.println("has parent");
        }

    }

    //THIS METHOD IS BIG SHIT>...>but i dont now how to create easy:(
    private void initChilds() {
        WorkBinaryNodeInfo first = information.infoFirstElement;
        WorkBinaryNodeInfo second = information.infoSecondElement;
        SwapHelper helpFirstLeft = new SwapHelper(first.get(), NodePosition.LEFT);
        SwapHelper helpFirstRight = new SwapHelper(first.get(), NodePosition.RIGHT);
        SwapHelper helpSecondLeft = new SwapHelper(second.get(), NodePosition.LEFT);
        SwapHelper helpSecondRight = new SwapHelper(second.get(), NodePosition.RIGHT);
        System.out.println("%%INIT lines...");
        ///////////////////////////
        /// FIRST LEFT first-back second-forward,
        ///////////////////////////
        if (first.hasLeft()) {
            System.out.println(String.format("%s %s", first.getLeftChild().getElement().getElementId(), second.get().getElement().getElementId()));
            if (first.getLeftChild().getElement().getElementId() == second.get().getElement().getElementId()) {
                //jedna se o leveho syna stejny atp
                helpFirstLeft.getConnectors().back = ((TwoDGraphicsNode) second.get().getElement());
                helpSecondLeft.getConnectors().forward = ((TwoDGraphicsNode) first.get().getElement());
                System.out.println("FIRST HAS LEFT AND ITS SWAPING ELEMENT");
            } else {
                System.out.println("FIRST HAS LEFT AND ITS NOT SWAPING ELEMENT");
                helpFirstLeft.getConnectors().back = ((TwoDGraphicsNode) first.getLeftChild().getElement());
                helpSecondLeft.getConnectors().forward = ((TwoDGraphicsNode) first.getLeftChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstLeft.getConnectors().back = ((TwoDGraphicsNode) first.get().getElement()).getRightChildConnector();
            helpSecondLeft.getConnectors().forward = ((TwoDGraphicsNode) second.get().getElement()).getRightChildConnector();
            helpFirstLeft.disableVisibleBack();
            helpSecondLeft.disableVisibleForward();
            System.out.println("no child first left");
        }

        ///////////////////////////
        /// FIRST RIGHT first-back second-forward,
        ///////////////////////////
        if (first.hasRight()) {
            System.out.println(String.format("%s %s",first.getRightChild().getElement().getElementId(), second.get().getElement().getElementId()));
            if (first.getRightChild().getElement().getElementId() == second.get().getElement().getElementId()) {
                helpFirstRight.getConnectors().back = ((TwoDGraphicsNode) second.get().getElement());
                helpSecondRight.getConnectors().forward = ((TwoDGraphicsNode) first.get().getElement());
                System.out.println("FIRST HAS RIGHT AND ITS SWAPING ELEMENT");
            } else {
                helpFirstRight.getConnectors().back = ((TwoDGraphicsNode) first.getRightChild().getElement());
                helpSecondRight.getConnectors().forward = ((TwoDGraphicsNode) first.getRightChild().getElement());
                System.out.println("FIRST HAS RIGHT AND ITS NOT SWAPING ELEMENT");
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstRight.getConnectors().back = ((TwoDGraphicsNode) first.get().getElement()).getLeftChildConnector();
            helpSecondRight.getConnectors().forward = ((TwoDGraphicsNode) second.get().getElement()).getLeftChildConnector();
            helpFirstRight.disableVisibleBack();
            helpSecondRight.disableVisibleForward();
            System.out.println("no child first right");
        }


        ///////////////////////////
        /// SECOND LEFT first-forward second-back,
        ///////////////////////////
        if (second.hasLeft()) {
            if (second.getLeftChild().getElement().getElementId() == first.get().getElement().getElementId()) {
                //jedna se o leveho syna stejny atp
                System.out.println("SECOND HAS LEFT AND ITS SWAPING ELEMENT");
                helpFirstLeft.getConnectors().forward = ((TwoDGraphicsNode) first.get().getElement());
                helpSecondLeft.getConnectors().back = ((TwoDGraphicsNode) second.get().getElement());
            } else {
                System.out.println("SECOND HAS LEFT AND ITS NOT SWAPING ELEMENT");
                helpFirstLeft.getConnectors().forward = ((TwoDGraphicsNode) second.getLeftChild().getElement());
                helpSecondLeft.getConnectors().back = ((TwoDGraphicsNode) second.getLeftChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstLeft.getConnectors().forward = ((TwoDGraphicsNode) first.get().getElement()).getRightChildConnector();
            helpSecondLeft.getConnectors().back = ((TwoDGraphicsNode) second.get().getElement()).getRightChildConnector();
            helpFirstLeft.disableVisibleForward();
            helpSecondLeft.disableVisibleBack();
            System.out.println("no child second left");
        }
        ///////////////////////////
        /// SECOND RIGHT first-forward second-back,
        ///////////////////////////
        if (second.hasRight()) {
            if (second.getRightChild().getElement().getElementId() == first.get().getElement().getElementId()) {
                System.out.println("SECOND HAS RIGHT AND ITS SWAPING ELEMENT");
                helpFirstRight.getConnectors().forward = ((TwoDGraphicsNode) first.get().getElement());
                helpSecondRight.getConnectors().back = ((TwoDGraphicsNode) second.get().getElement());
            } else {
                System.out.println("SECOND HAS RIGHT AND ITS NOT SWAPING ELEMENT");
                helpFirstRight.getConnectors().forward = ((TwoDGraphicsNode) second.getRightChild().getElement());
                helpSecondRight.getConnectors().back = ((TwoDGraphicsNode) second.getRightChild().getElement());
            }
        } else {
            // pokud nema syna tak se spoji s druhym koncem a naopak
            helpFirstRight.getConnectors().forward = ((TwoDGraphicsNode) first.get().getElement()).getLeftChildConnector();
            helpSecondRight.getConnectors().back = ((TwoDGraphicsNode) second.get().getElement()).getLeftChildConnector();
            helpFirstRight.disableVisibleForward();
            helpSecondRight.disableVisibleBack();
            System.out.println("no child second right");
        }

        helpers.add(helpFirstLeft);
        helpers.add(helpFirstRight);
        helpers.add(helpSecondLeft);
        helpers.add(helpSecondRight);
        System.out.println(helpers.size());

        System.out.println("%%%%%%%");
    }

    @Override
    public IAnimationBuilder getBuilder() {
        return new BuilderSwapNode(information,helpers);
    }

    private NodePosition getParentPosition(Integer indexAtRow, Integer indexAtRowChild) {
        return BinaryTreeHelper.getLeftChildIndex(indexAtRow).equals(indexAtRowChild) ? NodePosition.LEFT : NodePosition.RIGHT;
    }
}
