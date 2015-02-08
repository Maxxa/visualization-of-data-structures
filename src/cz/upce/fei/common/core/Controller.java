package cz.upce.fei.common.core;

import cz.commons.animation.AnimationControl;
import cz.commons.animation.TransitionEndPositionType;
import cz.commons.animation.TransitionFinishedEvent;
import cz.upce.fei.common.gui.animation.AnimationListenerAdapter;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public abstract class Controller {

    protected AnimationControl animationControl = new AnimationControl();

    protected ToolBarControlsContainer controlsContainer;

    protected BooleanProperty isLoading = new SimpleBooleanProperty(false);

    public Controller(ToolBarControlsContainer controlsContainer) {
        this.controlsContainer = controlsContainer;
        initStepListeners();
        initAnimationListeners();
        initHelpResetListeners();
        initAnimationFinished();
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
        controlsContainer.getStepControls().addStepListener(new StepListener(animationControl,controlsContainer));
    }

    private void initAnimationFinished() {
        animationControl.addTransitionFinishedListener(new TransitionFinishedEvent() {
            @Override
            public void handle(TransitionEndPositionType type) {
                if(type.equals(TransitionEndPositionType.END)){
                    controlsContainer.getStructureControls().enableButtons();
                }
                controlsContainer.getAnimationsControls().disable();

            }
        });
    }

    protected abstract EventHandler<ActionEvent> getHelpHandler();

    protected abstract EventHandler<ActionEvent> getPatternHandler();

    protected abstract EventHandler<ActionEvent> getResetHandler();
}
