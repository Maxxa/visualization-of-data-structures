package cz.upce.fei.muller.TwoDTree.gui;

import cz.commons.graphics.IGraphics;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.upce.fei.common.gui.AbstractApplication;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.common.gui.utils.SceneInfo;
import cz.upce.fei.muller.TwoDTree.core.TwoDTreeController;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * @author Vojtěch Müller
 */
public class TwoDTreeGUI extends AbstractApplication<TwoDTreeController> {

    public static final int CANVAS_WIDTH = (int) (4000* IGraphics.PLATFORM_SCALE);
    public static final int CANVAS_HEIGHT = (int) (450* IGraphics.PLATFORM_SCALE);

    public static final int GRID_VIEW_WIDTH = (int) (900* IGraphics.PLATFORM_SCALE);

    ToolBarControlsContainer controlsContainer = new ToolBarControlsContainer(new TwoDTreeStructureControl());
    private BinaryTreeLayoutManager binaryTreeLayoutManager;

    private Pane gridView = new Pane();

    @Override
    protected void beforeStart(Stage stage) {
        getCanvas().setPrefWidth(CANVAS_WIDTH);
        getCanvas().setPrefHeight(CANVAS_HEIGHT);

    }

    @Override
    protected TwoDTreeController getController() {
        binaryTreeLayoutManager = new BinaryTreeLayoutManager(TwoDTreeLayoutSetting.getSetting(),getCanvas(), BinaryTreeLayoutManager.PositionsChange.CALC_ONLY_INSERTED_NODES);
        return new TwoDTreeController(controlsContainer,binaryTreeLayoutManager,new CanvasChangeImpl(getScrollPane(),gridView,getStackPane()));
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
        return new TwoDTreeSceneInfo();
    }

    @Override
    protected void onShow() {
        gridView.setPrefWidth(GRID_VIEW_WIDTH);
        gridView.setPrefHeight(CANVAS_HEIGHT);
        gridView.setVisible(false);
        getStackPane().getChildren().addAll(gridView);
    }

    public static void main(String[] args) {launch(args);}
}
