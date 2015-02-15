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

/**
 * @author Vojtěch Müller
 */
public class TrieKey extends Element implements ITrieNodesSetting,ConnectibleElement {

    private DoubleProperty centerX = new SimpleDoubleProperty();
    private DoubleProperty centerY = new SimpleDoubleProperty();

    private final StackPane stackPane = new StackPane();
    private final Label textLabel;

    private LineElement lineToChilds;


    public TrieKey(String text,Integer positionAtBlocX) {
        textLabel = new Label(text);
        setTranslateX(positionAtBlocX);
        initElement();
        doBindings();
    }

    private void initElement() {
        stackPane.setMinSize(KEY_WIDTH,HEIGHT);
        stackPane.setPrefSize(KEY_WIDTH,HEIGHT);

        textLabel.setMaxWidth(KEY_WIDTH);
        textLabel.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(textLabel);
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
        centerY.bind(Bindings.add(HEIGHT, translateYProperty()));
    }
}
