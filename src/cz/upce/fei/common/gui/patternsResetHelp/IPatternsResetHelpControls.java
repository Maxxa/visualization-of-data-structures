package cz.upce.fei.common.gui.patternsResetHelp;

import cz.upce.fei.common.gui.toolBar.IToolBarControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public interface IPatternsResetHelpControls extends IToolBarControl {

    void addPatternHandler(EventHandler<ActionEvent> handler);
    void addResetHandler(EventHandler<ActionEvent> handler);
    void addHelpHandler(EventHandler<ActionEvent> handler);

}
