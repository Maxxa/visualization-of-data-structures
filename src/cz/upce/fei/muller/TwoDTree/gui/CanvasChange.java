package cz.upce.fei.muller.TwoDTree.gui;

import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public interface CanvasChange {

    void swapCanvas();

    Pane getGridView();

}