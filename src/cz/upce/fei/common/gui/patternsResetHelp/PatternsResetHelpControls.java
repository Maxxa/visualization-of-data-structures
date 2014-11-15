package cz.upce.fei.common.gui.patternsResetHelp;

import cz.commons.resources.CommonResources;
import cz.upce.fei.common.gui.builders.DefaultToolBarControlsBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public class PatternsResetHelpControls implements IPatternsResetHelpControls {

    ImageView helpImage = new ImageView(CommonResources.getResource("icons/help.png").toExternalForm());

    Button reset = new Button("Reset");
    Button pattern = new Button("Vzory");
    Button helpButton = new Button();

    public PatternsResetHelpControls() {
        helpButton.setGraphic(helpImage);
    }

    @Override
    public HBox getToolBarHBox() {

        return DefaultToolBarControlsBuilder.getResetHelpControls(reset,pattern,helpButton);
    }

    @Override
    public void addPatternHandler(EventHandler<ActionEvent> handler) {
        pattern.setOnAction(handler);
    }

    @Override
    public void addResetHandler(EventHandler<ActionEvent> handler) {
        reset.setOnAction(handler);
    }

    @Override
    public void addHelpHandler(EventHandler<ActionEvent> handler) {
        helpButton.setOnAction(handler);
    }
}
