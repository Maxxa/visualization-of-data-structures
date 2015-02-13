package cz.upce.fei.muller.trie.gui;

import cz.commons.utils.handlers.LetterValidationHandler;
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
public final class TrieStructureControl implements IStructureControls {

    Label label = new Label("Slovo:");
    TextField text = new TextField();

    Button add = new Button("Vložit");
    Button search = new Button("Najít");
    Button remove = new Button("Odebrat");

    public TrieStructureControl() {
        build();
    }

    private void build() {
        text.setAlignment(Pos.CENTER);
        text.setMaxWidth(100);
        text.addEventFilter(KeyEvent.KEY_TYPED, new LetterValidationHandler(10, LetterValidationHandler.CharacterSize.LOWER_CASE));
    }

    @Override
    public HBox getToolBarHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().addAll(label, text, add, search, remove);
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

    public String getTextValue() {
        return text.getText();
    }

}
