package cz.upce.fei.common.gui.structure;

import cz.upce.fei.common.gui.toolBars.IToolBarControl;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public interface IStructureControls extends IToolBarControl {

    void toggleEnableButtons();

    void enableButtons();
    void disableButtons();
}
