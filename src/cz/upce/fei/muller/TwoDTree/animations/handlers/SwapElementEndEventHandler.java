package cz.upce.fei.muller.TwoDTree.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.upce.fei.muller.TwoDTree.animations.builders.SwapHelper;
import javafx.event.ActionEvent;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class SwapElementEndEventHandler extends StepEventHandler {


    private final List<SwapHelper> helperList;

    public SwapElementEndEventHandler(List<SwapHelper> helperList) {
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

    private void buildHandle(boolean isForward){
        for (SwapHelper helper:helperList){
             if (isForward){
                helper.setForward();
             }else {
                 helper.setBack();
             }
        }
    }

}
