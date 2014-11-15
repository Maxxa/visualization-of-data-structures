package cz.upce.fei.muller.binaryHeap.gui;

import cz.upce.fei.common.gui.structure.IStructureControls;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public class StructureControls implements IStructureControls {
    @Override
    public HBox getToolBarHBox() {
        return new HBox();
    }
}
