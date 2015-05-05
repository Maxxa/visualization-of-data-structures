package cz.upce.fei.muller.treap.animations.handlers;

import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.upce.fei.muller.treap.animations.builders.SwapHelper;
import javafx.event.ActionEvent;

import java.util.List;


/**
 * @author Vojtěch Müller
 */
public class FadesTransitionHandler extends StepEventHandler{

    private final List<SwapHelper> helpers;
    private final boolean isBeforeSwap;

    public FadesTransitionHandler(List<SwapHelper> helpers,boolean isBeforeSwap) {
        this.helpers = helpers;
        this.isBeforeSwap = isBeforeSwap;
    }

    @Override
    protected void handleForward(ActionEvent actionEvent) {
        if(!isBeforeSwap){
            buildFadesAfterEnd(true);
        }
    }

    @Override
    protected void handleBack(ActionEvent actionEvent) {
        if(isBeforeSwap){
            buildFadesAfterEnd(false);
        }
    }

    private void buildFadesAfterEnd(boolean isForward){
        for (SwapHelper helper : helpers){
            if(!isForward&&!helper.isForwardVisible()
                    ||
               isForward && !helper.isBackVisible()
            ){
                changeVisible(helper.getLine());
            }
        }
    }

    void changeVisible(LineElement line){
        line.setOpacity(0);
        line.setVisible(false);
    }
}
