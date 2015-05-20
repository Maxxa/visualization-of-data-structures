package cz.upce.fei.muller.TwoDTree.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.layoutManager.ElementInfo;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import cz.upce.fei.muller.TwoDTree.animations.builders.DefaultSwapInformation;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.event.ActionEvent;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class SwapElementEndEventHandler extends StepEventHandler {


    private final List<SwitchConnectorHelper> helperList;
    private final DefaultSwapInformation swapInformation;

    public SwapElementEndEventHandler(List<SwitchConnectorHelper> helperList,DefaultSwapInformation swapInformation) {
        this.helperList = helperList;
        this.swapInformation = swapInformation;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        buildHandle(true);
        controlAndChange(true);
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        buildHandle(false);
        controlAndChange(false);
    }

    private void buildHandle(boolean isForward){
        for (SwitchConnectorHelper helper:helperList){
             if (isForward){
                helper.setForward();
             }else {
                 helper.setBack();
             }
        }
    }

    private void controlAndChange(boolean isForward) {
        ElementInfo infoFirst = swapInformation.infoFirstElement.get();
        ElementInfo infoSecond = swapInformation.infoSecondElement.get();
        boolean isXNew = infoFirst.getDepth()%2<=0;
        boolean isXOld = infoSecond.getDepth()%2<=0;
        ((TwoDGraphicsNode)infoFirst.getElement()).setLabelBold(isForward?isXNew:isXOld, true);
        ((TwoDGraphicsNode)infoSecond.getElement()).setLabelBold(isForward?isXOld:isXNew, true);
    }

}
