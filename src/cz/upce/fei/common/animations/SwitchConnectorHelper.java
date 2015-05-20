package cz.upce.fei.common.animations;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.ElementInfo;

/**
 * @author Vojtěch Müller
 */
public class SwitchConnectorHelper {

    protected final ElementInfo currentElement;
    private final NodePosition position;

    private ConnectorHelper connectors;

    private boolean isBackVisible = true;
    private boolean isForwardVisible = true;

    public SwitchConnectorHelper(ElementInfo currentElement, NodePosition position) {
        this.currentElement = currentElement;
        this.position = position;
        connectors = new ConnectorHelper();
    }

    public SwitchConnectorHelper(ElementInfo currentElement, NodePosition position, ConnectorHelper connectors) {
        this.currentElement = currentElement;
        this.position = position;
        this.connectors = connectors;
    }

    public LineElement getLine(){
        return ((BinaryNodeWithLine)currentElement.getElement()).getChildLine(position);
    }

    public void setForward(){
        getLine().setEnd(connectors.forward);
    }

    public void setBack(){
        getLine().setEnd(connectors.back);
    }

    public void disableVisibleForward(){
        isBackVisible=false;
    }
    public void disableVisibleBack(){
        isForwardVisible = false;
    }

    public boolean isBackVisible() {
        return isBackVisible;
    }

    public boolean isForwardVisible() {
        return isForwardVisible;
    }

    public ConnectorHelper getConnectors() {
        return connectors;
    }
}
