package cz.upce.fei.muller.TwoDTree.gui;

import cz.commons.utils.handlers.NumberValidationHandler;
import cz.upce.fei.common.gui.structure.IStructureControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public final class TwoDTreeStructureControl implements IStructureControls {

    Label labelX = new Label("X:");
    TextField x = new TextField();
    Label labelY = new Label("Y:");
    TextField y = new TextField();

    Button add = new Button("Vložit");
    Button search = new Button("Najít");
    Button remove = new Button("Odebrat");

    public TwoDTreeStructureControl() {
        build();
    }

    private void build() {
        x.setAlignment(Pos.CENTER);
        x.setMaxWidth(50);
        x.addEventFilter(KeyEvent.KEY_TYPED, new NumberValidationHandler(10));

        y.setAlignment(Pos.CENTER);
        y.setMaxWidth(50);
        y.addEventFilter(KeyEvent.KEY_TYPED, new NumberValidationHandler(10));
    }

    @Override
    public HBox getToolBarHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().addAll(labelX, x,labelY,y,
                add, search, remove);
        return hBox;
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
            search.setDisable(isDisable);
            remove.setDisable(isDisable);
    }

    public void addInsertHandler(EventHandler<ActionEvent> handler) {
        add.setOnAction(handler);
    }

    public void addRemoveHandler(EventHandler<ActionEvent> handler) {
        remove.setOnAction(handler);
    }

    public void addSearchHandler(EventHandler<ActionEvent> handler) {
        search.setOnAction(handler);
    }

    public String getX() {
        return x.getText();
    }
    public String getY() {
        return y.getText();
    }

}
