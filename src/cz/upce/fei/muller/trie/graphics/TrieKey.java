package cz.upce.fei.muller.trie.graphics;

import cz.commons.graphics.ConnectibleElement;
import cz.commons.graphics.Element;
import cz.commons.graphics.LineElement;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Vojtěch Müller
 */
public class TrieKey extends Element implements ITrieNodesSetting,ConnectibleElement {

    private DoubleProperty centerX = new SimpleDoubleProperty();
    private DoubleProperty centerY = new SimpleDoubleProperty();

    private final StackPane stackPane = new StackPane();
    private final Label textLabel;

    private LineElement lineToChilds;

    private Rectangle rect;


    public TrieKey(String text,Integer positionAtBlocX) {
        textLabel = new Label(text);
        setTranslateX(positionAtBlocX);
        initRect();
        initElement();
        doBindings();
    }

    private void initRect() {
        rect = new Rectangle(KEY_WIDTH, HEIGHT);
        rect.setFill(null);
        rect.setStroke(Color.TRANSPARENT);
        rect.setStrokeWidth(2);
    }

    public Shape getRect(){
        return rect;
    }

    public void defaultText(){
        textLabel.setTextFill(Color.BLACK);
    }
    public void coloredText(){
        textLabel.setTextFill(Color.RED);
    }

    private void initElement() {
        stackPane.setMinSize(KEY_WIDTH,HEIGHT);
        stackPane.setPrefSize(KEY_WIDTH,HEIGHT);

        textLabel.setMaxWidth(KEY_WIDTH);
        textLabel.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(textLabel,rect);
        this.getChildren().addAll(stackPane);
    }

    public DoubleProperty connectXProperty() {
        return centerX;
    }

    @Override
    public DoubleProperty connectYProperty() {
        return centerY;
    }

    private void doBindings() {
        centerX.bind(Bindings.add(KEY_WIDTH / 2, translateXProperty()));
//        centerY.bind(Bindings.add(HEIGHT, translateYProperty()));
    }


    public LineElement getLine() {
        return lineToChilds;
    }

    public void setLine(LineElement line) {
        this.lineToChilds = line;
    }
}
