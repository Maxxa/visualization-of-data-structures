package cz.upce.fei.muller.splayTree.core;

import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.commons.layoutManager.RepairmanLayoutManager;
import cz.commons.layoutManager.WorkBinaryNodeInfoBuilder;
import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.upce.fei.common.animations.SwitchConnectorHelperBuilder;
import cz.upce.fei.common.events.ReferenceHelper;
import cz.upce.fei.common.gui.FlashMessageViewer;
import cz.upce.fei.muller.splayTree.animations.FlashMessageViewerHelper;
import cz.upce.fei.muller.splayTree.animations.builders.BuilderAddElement;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class Inserting extends AbstractEventPreparation {

    final SplayGraphicsNodeElement element;

    public Inserting(Data data, SplayNodeImpl insertingNode) {
        super(data);
        element = new SplayGraphicsNodeElement(insertingNode, 0, 0);
    }

    public void elementKeyExist() {
        FlashMessageViewer flashMessageViewer = FlashMessageViewerHelper.buildViewer("Zadaný klíč již existuje.", data.flashMessagePosition, data.manager.getCanvas());
        int size = data.animationControl.getTransitions().size();
        viewers.add(flashMessageViewer);
        data.animationControl.getTransitions().add(size - 1, FlashMessageViewerHelper.showViewer(flashMessageViewer));
    }

    @Override
    protected void clear() {
    }

    public void insert(ITreeStructure root, List<ReferenceHelper> referenceHelperList) {
        data.manager.addElement(element, null, false);
        data.manager.getCanvas().getChildren().addAll(element.getChildLine(NodePosition.LEFT), element.getChildLine(NodePosition.RIGHT));
        RepairmanLayoutManager repairman = new RepairmanLayoutManager((BinaryTreeLayoutManager) data.manager, root);
        repairman.addIgnoreId(element.getElementId());
        List<MoveElementEvent> moveElementEvents = repairman.reconstruction();

        SwitchConnectorHelperBuilder builder = new SwitchConnectorHelperBuilder(data.manager, referenceHelperList);

        //TODO moving events next ??

        data.insertTransition(
                new BuilderAddElement(
                        data.manager.getNodePosition(element.getElementId()),
                        data.creatingPoint, data.getNode(element.getElementId()),
                        WorkBinaryNodeInfoBuilder.getWorkInfo(element.getElementId(), data.manager),
                        getElementMovings(moveElementEvents), builder.getHelpers(),
                        this.moveParentsElements
                )
        );
        moveElementEvents.clear();
    }

}
