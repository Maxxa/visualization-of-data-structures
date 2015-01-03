package cz.upce.fei.common.core;

import cz.commons.animation.AnimationControl;
import cz.upce.fei.common.gui.animation.AnimationListenerAdapter;
import cz.upce.fei.common.gui.step.StepListenerAdapter;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public abstract class MyController {

    protected AnimationControl animationControl = new AnimationControl();

    protected ToolBarControlsContainer controlsContainer;

    public MyController(ToolBarControlsContainer controlsContainer) {
        this.controlsContainer = controlsContainer;
        initListeners();
    }

    private void initListeners() {
        controlsContainer.getStepControls().addStepListener(new StepListenerAdapter(){

            @Override
            public void next() {
                animationControl.goForth();
            }

            @Override
            public void previous() {
                animationControl.goBack();
            }

        });

        controlsContainer.getAnimationsControls().addChangesListener(
                new AnimationListenerAdapter() {
                    @Override
                    public void speedChange(Number speed) {
                        animationControl.setRate((Double) speed);
                    }
                });

        controlsContainer.getPaternControls().addHelpHandler(getHelpHandler());
        controlsContainer.getPaternControls().addPatternHandler(getPatternHandler());
        controlsContainer.getPaternControls().addResetHandler(getResetHandler());

    }

    protected abstract EventHandler<ActionEvent> getHelpHandler();

    protected abstract EventHandler<ActionEvent> getPatternHandler();

    protected abstract EventHandler<ActionEvent> getResetHandler();
}
