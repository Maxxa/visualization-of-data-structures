package cz.upce.fei.muller.binaryHeap.gui;

import cz.commons.layoutManager.TreeLayoutManager;
import cz.upce.fei.common.gui.AbstractApplication;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.common.gui.utils.SceneInfo;
import cz.upce.fei.muller.binaryHeap.core.BinaryHeapController;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapGUI extends AbstractApplication<BinaryHeapController> {

    private ToolBarControlsContainer containerControls = new ToolBarControlsContainer(new StructureControls());

    private TreeLayoutManager treeLayoutManager;

    @Override
    protected void beforeStart(Stage stage) {  }

    @Override
    protected BinaryHeapController getController() {
        treeLayoutManager = new TreeLayoutManager(TreeLayoutSettingBuilder.getSetting(),getCanvas());
        return new BinaryHeapController(getCanvas(),containerControls, treeLayoutManager);
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

    public static void main(String[] args) {launch(args);}
}
