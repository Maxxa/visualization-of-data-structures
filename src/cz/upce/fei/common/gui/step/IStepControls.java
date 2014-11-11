package cz.upce.fei.common.gui.step;

import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public interface IStepControls {

    HBox getToolbarHBox();

    void addStepListener(IStepListener listener);
}
