package cz.upce.fei.common.gui.step;

import com.sun.javafx.event.EventHandlerManager;
import cz.upce.fei.common.events.EventListenersList;
import cz.upce.fei.common.gui.builders.DefaultToolBarControlsBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public class StepControls implements IStepControls {

    private CheckBox stepCheckBox;
    private Button nextBtn;
    private Button previousBtn;

    EventListenersList<IStepListener> listenersList = new EventListenersList<>();

    public StepControls() {
        init();
    }

    private void init() {
        stepCheckBox = new CheckBox("krokovat");
        stepCheckBox.setSelected(false);

        nextBtn = new Button("Další");
        previousBtn =  new Button("Zpět");
        nextBtn.setDisable(true);
        previousBtn.setDisable(true);
        initListeners();
    }

    private void initListeners() {

        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fireNextStep();
            }
        });

        previousBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                firePreviousStep();
            }
        });

        stepCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                setEnableBtn(!newValue);
                fireChangeStepEnable(newValue);
            }
        });
    }

    private void fireChangeStepEnable(Boolean newValue) {
        for (IStepListener listener : listenersList.getListeners()){
            listener.isStepChange(newValue);
        }
    }

    private void fireNextStep() {
        for (IStepListener listener : listenersList.getListeners()){
            listener.next();
        }
    }

    private void firePreviousStep() {
        for (IStepListener listener : listenersList.getListeners()){
            listener.previous();
        }
    }

    @Override
    public HBox getToolBarHBox() {
        return DefaultToolBarControlsBuilder.getStepControls(stepCheckBox,nextBtn,previousBtn);
    }

    @Override
    public void addStepListener(IStepListener listener) {
        listenersList.addListener(listener);
    }

    private void setEnableBtn(boolean disable){
        nextBtn.setDisable(disable);
        previousBtn.setDisable(disable);
    }
}
