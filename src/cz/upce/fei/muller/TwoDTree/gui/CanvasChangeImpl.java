package cz.upce.fei.muller.TwoDTree.gui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Vojtěch Müller
 */
public class CanvasChangeImpl implements CanvasChange {

    private final ScrollPane treeView;
    private final Pane gridView;
    private final StackPane pane;

    public CanvasChangeImpl(ScrollPane treeView, Pane gridView,StackPane pane) {
        this.treeView = treeView;
        this.gridView = gridView;
        this.pane = pane;
    }

    @Override
    public void swapCanvas() {
        changeView(treeView.isVisible());
    }

    @Override
    public Pane getGridView() {
        return gridView;
    }

    private void changeView(boolean isGrid) {
        gridView.setVisible(isGrid);
        treeView.setVisible(!isGrid);
    }
}
