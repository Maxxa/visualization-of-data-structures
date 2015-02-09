package cz.upce.fei.muller.trie.gui;

import cz.upce.fei.common.gui.utils.ButtonContainer;
import cz.upce.fei.common.gui.structure.IStructureControls;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public final class StructureDefaultControl implements IStructureControls {

    ButtonContainer container = new ButtonContainer();

    public StructureDefaultControl() {
        build();
    }

    private void build() {
        //TODO build
    }

    @Override
    public HBox getToolBarHBox() {
        HBox hBox = new HBox();
        //TODO
        return hBox;
    }

    @Override
    public void toggleEnableButtons() {

    }

    @Override
    public void enableButtons() {

    }

    @Override
    public void disableButtons() {

    }
}
