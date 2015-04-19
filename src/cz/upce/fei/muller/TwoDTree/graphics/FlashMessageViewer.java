package cz.upce.fei.muller.TwoDTree.graphics;

import cz.commons.graphics.Element;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class FlashMessageViewer extends Element{

    private final int WIDTH  = 250;
    private final int HEIGHT = 30;

    private final Label textMessage = new Label("");

    private Rectangle backgroundRectangle;

    public FlashMessageViewer(String text) {
        textMessage.setText(text);
        initRect();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(backgroundRectangle,initLabel());
        getChildren().addAll(sp);
    }

    private Node initLabel() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        textMessage.setAlignment(Pos.CENTER_LEFT);
        vBox.setMaxWidth(WIDTH);
        vBox.getChildren().addAll(textMessage);
        return vBox;
    }


    private void initRect() {
        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setFill(Color.LIGHTGRAY);
        backgroundRectangle.setOpacity(0.2);
    }

    public void setText(String text){
        textMessage.setText(text);
    }

    public void clear(){
        setOpacity(0);
        setText("");
    }

}
