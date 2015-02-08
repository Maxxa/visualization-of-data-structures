package cz.upce.fei.common.core;

import cz.commons.animation.AnimationControl;
import cz.upce.fei.common.gui.step.IStepListener;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;

/**
 * @author Vojtěch Müller
 */
public class StepListener implements IStepListener {

    private AnimationControl animationControl;

    private ToolBarControlsContainer controlsContainer;

    public StepListener(AnimationControl animationControl, ToolBarControlsContainer controlsContainer) {
        this.animationControl= animationControl;
        this.controlsContainer = controlsContainer;
    }

    @Override
    public void isStepChange(Boolean newValue) {
        animationControl.markAsStepping(newValue);
        if (newValue) {
            controlButtons();
        } else {
            controlsContainer.getAnimationsControls().disable();
            controlsContainer.getStepControls().disableBtnAll();
            animationControl.playForward();
        }
    }

    @Override
    public void next() {
        animationControl.goForth();
        controlButtons();
        controlsContainer.getAnimationsControls().enable();
    }

    @Override
    public void previous() {
        controlsContainer.getAnimationsControls().enable();
        animationControl.goBack();
        controlButtons();
    }

    private void controlButtons(){
        if(animationControl.isPreviousTransition()){
            controlsContainer.getStepControls().enableBtnBack();
        }else{
            controlsContainer.getStepControls().disableBtnBack();
        }

        if(animationControl.isNextTransition()){
            controlsContainer.getStructureControls().disableButtons();
            controlsContainer.getStepControls().enableBtnNext();
        }else {
            controlsContainer.getStructureControls().enableButtons();
            controlsContainer.getStepControls().disableBtnNext();
        }

    }

}
