package cz.upce.fei.muller.binaryHeap.gui;

import cz.commons.graphics.IGraphics;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.upce.fei.common.gui.AbstractApplication;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.common.gui.utils.SceneInfo;
import cz.upce.fei.muller.binaryHeap.core.BinaryHeapController;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapGUI extends AbstractApplication<BinaryHeapController> {

    public static final int CANVAS_WIDTH = (int) (8400* IGraphics.PLATFORM_SCALE);
    public static final int CANVAS_HEIGHT = (int) (450* IGraphics.PLATFORM_SCALE);

    private ToolBarControlsContainer containerControls = new ToolBarControlsContainer(new StructureControls());

    private BinaryTreeLayoutManager binaryTreeLayoutManager;

    @Override
    protected void beforeStart(Stage stage) {
        getCanvas().setPrefWidth(CANVAS_WIDTH);
        getCanvas().setPrefHeight(CANVAS_HEIGHT);
    }

    @Override
    protected BinaryHeapController getController() {
        binaryTreeLayoutManager = new BinaryTreeLayoutManager(TreeLayoutSettingBuilder.getSetting(),getCanvas());
        return new BinaryHeapController(containerControls, binaryTreeLayoutManager);
    }

    @Override
    protected ToolBar initToolbar() {
        return containerControls.getToolBar();
    }

    @Override
    protected void addShortcuts(Scene scene) {
        //TODO add shortcuts?
    }

    @Override
    protected SceneInfo initSceneInfo() {
        return new BinaryHeapSceneInfo();
    }

    protected void onShow() {
    }

    public static void main(String[] args) {
        launch(args);
    }

}
