package cz.upce.fei.muller.splayTree.gui;

import cz.upce.fei.common.gui.structure.IStructureControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public class SplayStructureControls implements IStructureControls {

    Label nodeLabel = new Label("Prvek:");
    TextField text = new TextField();

    Button add = new Button("Vložit");
    Button find = new Button("Najít");
    Button remove = new Button("Odebrat");

    public SplayStructureControls() {
        text.setAlignment(Pos.CENTER);
        text.setMaxWidth(50);
    }

    @Override
    public HBox getToolBarHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().addAll(nodeLabel,text,add,find, remove);
        return hBox;
    }

    public void addInsertHandler(EventHandler<ActionEvent> handler) {
        add.setOnAction(handler);
    }

    public void addRemoveHandler(EventHandler<ActionEvent> handler) {
        remove.setOnAction(handler);
    }
    public void addFindHandler(EventHandler<ActionEvent> handler) {
        find.setOnAction(handler);
    }

    public String getTextValue() {
        return text.getText();
    }

    @Override
    public void toggleEnableButtons() {
        if(add.isDisable()){
            enableButtons();
        }else{
            disableButtons();
        }
    }

    @Override
    public void enableButtons() {
        setEnablingBtn(false);
    }

    @Override
    public void disableButtons() {
        setEnablingBtn(true);
    }

    private void setEnablingBtn(boolean isDisable){
        add.setDisable(isDisable);
        find.setDisable(isDisable);
        remove.setDisable(isDisable);
    }
}
