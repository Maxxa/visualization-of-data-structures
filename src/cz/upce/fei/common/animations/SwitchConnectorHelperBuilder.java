package cz.upce.fei.common.animations;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.ElementInfo;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.common.events.ReferenceHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class SwitchConnectorHelperBuilder {

    private final ITreeLayoutManager manager;
    private final List<ReferenceHelper> referenceHelperList;
    private List<SwitchConnectorHelper> helpers = new LinkedList<>();

    public SwitchConnectorHelperBuilder(ITreeLayoutManager manager, List<ReferenceHelper> referenceHelperList) {
        this.manager = manager;
        this.referenceHelperList = referenceHelperList;
        initSwitchHelpers();
    }

    private void initSwitchHelpers() {
        for (ReferenceHelper referenceHelper : referenceHelperList) {
            System.out.println(referenceHelper);
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

    public List<SwitchConnectorHelper> getHelpers() {
        return helpers;
    }
}
