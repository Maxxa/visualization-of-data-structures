package cz.upce.fei.muller.TwoDTree.core;

import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreeController extends Controller {

    public TwoDTreeController(ToolBarControlsContainer controlsContainer) {
        super(controlsContainer);
    }

    @Override
    protected EventHandler<ActionEvent> getHelpHandler() {
        return null;
    }

    @Override
    protected EventHandler<ActionEvent> getPatternHandler() {
        return null;
    }

    @Override
    protected EventHandler<ActionEvent> getResetHandler() {
        return null;
    }
}
