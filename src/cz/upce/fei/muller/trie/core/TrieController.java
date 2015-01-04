package cz.upce.fei.muller.trie.core;

import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class TrieController extends Controller{
    

    public TrieController(Pane pane, ToolBarControlsContainer toolBarControlsContainer) {
        super(toolBarControlsContainer);
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
