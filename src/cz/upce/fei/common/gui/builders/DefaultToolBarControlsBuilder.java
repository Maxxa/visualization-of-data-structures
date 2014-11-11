package cz.upce.fei.common.gui.builders;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

import static cz.upce.fei.common.graphics.Graphics.*;

/**
 * @author Vojtěch Müller
 */
public final class DefaultToolBarControlsBuilder {

    public static HBox getStepControls(CheckBox stepCheckBox, Button nextStepButton, Button previousStepButton){
        HBox stepHBox = new HBox();
        stepHBox.setAlignment(Pos.CENTER);
        stepHBox.setSpacing(5* PLATFORM_SCALE);
        stepHBox.getChildren().addAll(stepCheckBox, previousStepButton, nextStepButton);
        return stepHBox;
    }

    public static HBox getAnimationControls(Slider speedSlider,Button startStop){
        HBox animationControlsHBox = new HBox();
        animationControlsHBox.setAlignment(Pos.CENTER);
        Label speedLabel = new Label("Rychlost:");
        speedLabel.setLabelFor(speedSlider);
        animationControlsHBox.setSpacing(5* PLATFORM_SCALE);
        animationControlsHBox.getChildren().addAll(speedLabel, speedSlider,startStop);
        return animationControlsHBox;
    }

    public static HBox getResetHelpControls(Button reset, Button patterns, Button help){
        HBox helpHBox = new HBox();
        helpHBox.setAlignment(Pos.CENTER);
        helpHBox.setSpacing(5* PLATFORM_SCALE);
        helpHBox.getChildren().addAll(reset, patterns, help);
        return helpHBox;
    }

}
