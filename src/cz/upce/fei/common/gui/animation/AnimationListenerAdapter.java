package cz.upce.fei.common.gui.animation;

/**
 * @author Vojtěch Müller
 */
public abstract class AnimationListenerAdapter implements IAnimationListener {

    @Override
    public void playAnimation() { }

    @Override
    public void pauseAnimation() { }

    @Override
    public void speedChange(Number speed) { }
}
