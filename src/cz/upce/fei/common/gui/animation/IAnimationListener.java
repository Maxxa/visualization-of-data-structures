package cz.upce.fei.common.gui.animation;

import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public interface IAnimationListener extends EventHandler{

    public void playAnimation();

    void pauseAnimation();

    void speedChange(Number speed);

}
