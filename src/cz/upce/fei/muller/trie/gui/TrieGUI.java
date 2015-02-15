package cz.upce.fei.muller.trie.gui;

import cz.commons.graphics.IGraphics;
import cz.upce.fei.common.gui.AbstractApplication;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.common.gui.utils.SceneInfo;
import cz.upce.fei.muller.trie.core.TrieController;
import cz.upce.fei.muller.trie.manager.LayoutManager;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;


/**
 * @author Vojtěch Müller
 */
public class TrieGUI extends AbstractApplication<TrieController> {

    public static final int CANVAS_WIDTH = (int) (4000* IGraphics.PLATFORM_SCALE);
    public static final int CANVAS_HEIGHT = (int) (450* IGraphics.PLATFORM_SCALE);

    ToolBarControlsContainer controlsContainer = new ToolBarControlsContainer(new  TrieStructureControl());

    @Override
    protected void beforeStart(Stage stage) {
        getCanvas().setPrefWidth(CANVAS_WIDTH);
        getCanvas().setPrefHeight(CANVAS_HEIGHT);
    }

    @Override
    protected TrieController getController() {
        return new TrieController(controlsContainer,new LayoutManager(getCanvas(),TrieLayoutSetting.getSetting()));
    }

    @Override
    protected ToolBar initToolbar() {
        return controlsContainer.getToolBar();
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
        int count = 0;
        for (int i = 97; i < 123; i++) {
            count++;
            Character character = new Character((char)i);
            System.out.println(character);

        }
        System.out.println(count);

    }

    public static void main(String[] args) {launch(args);}
}
