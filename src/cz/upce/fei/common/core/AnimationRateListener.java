package cz.upce.fei.common.core;

import cz.commons.animation.AnimationControl;
import cz.upce.fei.common.gui.animation.IAnimationListener;
import javafx.event.Event;

/**
 * @author Vojtěch Müller
 */
public class AnimationRateListener implements IAnimationListener {

    private final AnimationControl animationControl;

    public AnimationRateListener(AnimationControl animationControl) {
        this.animationControl = animationControl;
    }

    @Override
    public void playAnimation() {
//        animationControl.
    }

    @Override
    public void pauseAnimation() {

    }

    @Override
    public void speedChange(Number speed) {

    }

}
