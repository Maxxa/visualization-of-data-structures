package cz.upce.fei.muller.trie.gui;

import cz.upce.fei.common.gui.AbstractApplication;
import cz.upce.fei.common.gui.toolBar.ToolBarControlsContainer;
import cz.upce.fei.common.gui.utils.SceneInfo;
import cz.upce.fei.muller.trie.core.TrieController;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

/**
 * @author Vojtěch Müller
 */
public class TrieGUI extends AbstractApplication<TrieController> {

    StructureDefaultControl defaultControl = new StructureDefaultControl();

    ToolBarControlsContainer toolBarContainer = new ToolBarControlsContainer(defaultControl);

    @Override
    protected void beforeStart(Stage stage) {

    }

    @Override
    protected TrieController getController() {
        return null;
    }

    @Override
    protected ToolBar initToolbar() {
        return toolBarContainer.getToolBar();
    }

    @Override
    protected void addShortcuts(Scene scene) {

    }

    @Override
    protected SceneInfo initSceneInfo() {
        return new TrieSceneInfo();
    }

    @Override
    protected void onShow() {

    }

    public static void main(String[] args) {launch(args);}
}
