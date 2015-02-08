package cz.upce.fei.common.gui.animation;

import cz.commons.resources.CommonResources;
import cz.upce.fei.common.events.EventListenersList;
import cz.upce.fei.common.gui.builders.DefaultToolBarControlsBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public final class AnimationControls implements IAnimationsControls {

    private Button playPauseButton;

    private Slider speedSlider;

    private final ImageView pauseImage = new ImageView(CommonResources.getResource("icons/control_pause.png").toExternalForm());
    private final ImageView playImage = new ImageView(CommonResources.getResource("icons/control_play.png").toExternalForm());

    EventListenersList<IAnimationListener> listenerList = new EventListenersList<>();

    public AnimationControls() {
        init();
    }

    private void init() {
        playPauseButton = new Button();
        playPauseButton.setGraphic(pauseImage);
        playPauseButton.setDisable(true);

        speedSlider = new Slider();
        speedSlider.setMin(0.3);
        speedSlider.setMax(2.5);
        speedSlider.setValue(1);
        speedSlider.setPrefWidth(120);

        initListeners();
    }

    private void initListeners() {
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                fireSpeedChange(newValue);
            }
        });

        playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playPauseButton.getGraphic().equals(pauseImage)) {
                    fireStart();
                    playPauseButton.setGraphic(playImage);
                } else {
                    fireStop();
                    playPauseButton.setGraphic(pauseImage);
                }
            }
        });
    }

    public HBox getToolBarHBox(){
        return DefaultToolBarControlsBuilder.getAnimationControls(speedSlider, playPauseButton);
    }

    @Override
    public void enable() {
        playPauseButton.setDisable(false);
    }

    @Override
    public void disable() {
        playPauseButton.setDisable(true);
    }

    @Override
    public void addChangesListener(IAnimationListener listener) {
        listenerList.addListener(listener);
    }

    @Override
    public void removeChangesListener(IAnimationListener listener) {
        listenerList.removeListener(listener);
    }

    private void fireStart(){
        if(!playPauseButton.isDisable()){
            for(IAnimationListener listener : listenerList.getListeners()){
                listener.playAnimation();
            }
        }
    }

    private void fireStop(){
        if(!playPauseButton.isDisable()){
            for(IAnimationListener listener : listenerList.getListeners()){
                listener.pauseAnimation();
            }
        }
    }

    private void fireSpeedChange(Number newValue){
        if(playPauseButton.isDisable()){
            for(IAnimationListener listener : listenerList.getListeners()){
                listener.speedChange(newValue);
            }
        }
    }
}

