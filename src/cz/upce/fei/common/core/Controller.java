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
public abstract class Controller {

    protected AnimationControl animationControl = new AnimationControl();

    protected ToolBarControlsContainer controlsContainer;

    public Controller(ToolBarControlsContainer controlsContainer) {
        this.controlsContainer = controlsContainer;
        initStepListeners();
        initAnimationListeners();
        initHelpResetListeners();
    }


    protected void initHelpResetListeners() {
        controlsContainer.getPaternControls().addHelpHandler(getHelpHandler());
        controlsContainer.getPaternControls().addPatternHandler(getPatternHandler());
        controlsContainer.getPaternControls().addResetHandler(getResetHandler());
    }

    protected void initAnimationListeners() {
        controlsContainer.getAnimationsControls().addChangesListener(
                new AnimationListenerAdapter() {

                    @Override
                    public void pauseAnimation() {
                        animationControl.togglePlaying();
                    }

                    @Override
                    public void playAnimation() {
                        animationControl.togglePlaying();
                    }

                    @Override
                    public void speedChange(Number speed) {
                        animationControl.setRate((Double) speed);
                    }
                });
    }

    protected void initStepListeners() {
        controlsContainer.getStepControls().addStepListener(new StepListenerAdapter(){

            @Override
            public void isStepChange(Boolean newValue) {
                animationControl.markAsStepping(newValue);
            }

            @Override
            public void next() {
                animationControl.goForth();
            }

            @Override
            public void previous() {
                animationControl.goBack();
            }

        });
    }

    protected abstract EventHandler<ActionEvent> getHelpHandler();

    protected abstract EventHandler<ActionEvent> getPatternHandler();

    protected abstract EventHandler<ActionEvent> getResetHandler();
}
