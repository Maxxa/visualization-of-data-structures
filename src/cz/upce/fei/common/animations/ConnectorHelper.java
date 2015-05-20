package cz.upce.fei.common.animations;

import cz.commons.graphics.ConnectibleElement;

/**
 * @author Vojtěch Müller
 */
public class ConnectorHelper {

    public ConnectibleElement forward;
    public ConnectibleElement back;

    public ConnectorHelper() {
    }

    public ConnectorHelper(ConnectibleElement forward, ConnectibleElement back) {
        this.forward = forward;
        this.back = back;
    }



}
