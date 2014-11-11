package cz.upce.fei.common.gui.animation;

import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public interface IAnimationsControls {

    HBox getToolbarHBox();

    void enable();

    void disable();

    void addChangesListener(IAnimationListener listener);

}
