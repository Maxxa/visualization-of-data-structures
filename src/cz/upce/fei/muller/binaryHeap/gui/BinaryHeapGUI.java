package cz.upce.fei.muller.binaryHeap.gui;

import cz.commons.graphics.LineBuilder;
import cz.commons.graphics.LineElement;
import cz.upce.fei.common.gui.AbstractApplication;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.common.gui.utils.SceneInfo;
import cz.upce.fei.muller.binaryHeap.core.BinaryHeapController;
import cz.upce.fei.muller.binaryHeap.graphics.NodeElement;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapGUI extends AbstractApplication<BinaryHeapController> {

    private ToolBarControlsContainer containerControls = new ToolBarControlsContainer(new StructureControls());

    @Override
    protected void beforeStart(Stage stage) {

    }

    @Override
    protected BinaryHeapController getController() {
        return null;
    }

    @Override
    protected ToolBar initToolbar() {
        return containerControls.getToolBar();
    }

    @Override
    protected void addShortcuts(Scene scene) {

    }

    @Override
    protected SceneInfo initSceneInfo() {
        return new BinaryHeapSceneInfo();
    }

    @Override
    protected void onShow() {
        NodeElement n = new NodeElement(1,10,50,50);
        NodeElement n1 = new NodeElement(2,100,500,150);

        LineElement l= LineBuilder.getBlueLine(n, n1);
        getCanvas().getChildren().addAll(n,n1,l);

    }

    public static void main(String[] args) {launch(args);}
}
