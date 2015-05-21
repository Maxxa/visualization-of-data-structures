package cz.upce.fei.muller.treap.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import javafx.event.ActionEvent;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class SwitchElementEndEventHandler extends StepEventHandler {

    private final List<SwitchConnectorHelper> helperList;

    public SwitchElementEndEventHandler(List<SwitchConnectorHelper> helperList) {
        this.helperList = helperList;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        buildHandle(true);
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        buildHandle(false);
    }

    private void buildHandle(boolean isForward) {
        for (SwitchConnectorHelper helper : helperList) {
            if (isForward) {
                helper.setForward();
            } else {
                helper.setBack();
            }
        }
    }

}
