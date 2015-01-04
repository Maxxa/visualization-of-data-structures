package cz.upce.fei.muller.binaryHeap.gui;

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

    private ToolBarControlsContainer containerControls = new ToolBarControlsContainer(new StructureControls());

    private BinaryTreeLayoutManager binaryTreeLayoutManager;

    @Override
    protected void beforeStart(Stage stage) {  }

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

    protected void onShow() {}

    public static void main(String[] args) {
        launch(args);
    }

}
