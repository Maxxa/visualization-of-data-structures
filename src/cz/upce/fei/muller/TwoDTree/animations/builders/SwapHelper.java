package cz.upce.fei.muller.TwoDTree.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.ElementInfo;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;

/**
 * @author Vojtěch Müller
 */
public class SwapHelper {

    protected final ElementInfo currentElement;
    private final NodePosition position;

    private ConnectorHelper connectors;

    private boolean isBackVisible = true;
    private boolean isForwardVisible = true;

    public SwapHelper(ElementInfo currentElement, NodePosition position) {
        this.currentElement = currentElement;
        this.position = position;
        connectors = new ConnectorHelper();
    }

    public SwapHelper(ElementInfo currentElement,NodePosition position, ConnectorHelper connectors) {
        this.currentElement = currentElement;
        this.position = position;
        this.connectors = connectors;
    }

    public LineElement getLine(){
        return ((TwoDGraphicsNode)currentElement.getElement()).getChildLine(position);
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
