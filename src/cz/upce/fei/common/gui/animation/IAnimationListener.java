package cz.upce.fei.common.gui.animation;

/**
 * @author Vojtěch Müller
 */
public interface IAnimationListener{

    public void playAnimation();

    void pauseAnimation();

    void speedChange(Number speed);

}