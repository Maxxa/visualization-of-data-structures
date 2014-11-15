package cz.upce.fei.common.gui.step;

import cz.upce.fei.common.gui.toolBars.IToolBarControl;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public interface IStepControls extends IToolBarControl {

    void addStepListener(IStepListener listener);
}
