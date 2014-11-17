package cz.upce.fei.muller.binaryHeap.gui;

import cz.commons.utils.ControlUtils;
import cz.upce.fei.common.gui.structure.IStructureControls;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public class StructureControls implements IStructureControls {

    Label prvekLabel = new Label("Prvek:");
    TextField text = new TextField();

    Button add = new Button("Vložit");

    Button removeRoot = new Button("Odebrat kořen");

    Button clear  = new Button("Vyčistit");;

    public StructureControls() {
        text.setAlignment(Pos.CENTER);
        text.setMaxWidth(50);
    }

    @Override
    public HBox getToolBarHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().addAll(prvekLabel,text,add,new Separator(Orientation.VERTICAL),removeRoot,clear);
        return hBox;
    }
}
