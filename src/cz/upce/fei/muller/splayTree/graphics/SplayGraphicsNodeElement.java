package cz.upce.fei.muller.splayTree.graphics;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class SplayGraphicsNodeElement extends BinaryNodeWithLine implements ISplayNodesElements {

    private final SplayNodeImpl node;
    private Rectangle backgroundRectangle;
    private Label label = new Label();

    private Tooltip tooltip;

    public SplayGraphicsNodeElement(SplayNodeImpl node, int x, int y) {
        this(node, x, y, false);
    }

    public SplayGraphicsNodeElement(SplayNodeImpl node, int x, int y, boolean isSearchBlock) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);
        String valueText = String.valueOf(node.getKey());
        label.setText(valueText);
        label.setMaxWidth(WIDTH);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-weight: bold;");

        installTooltip(valueText,node.getId());

        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(isSearchBlock?BG_SEARCH_COLOR:BG_COLOR);

        VBox labelsBox = new VBox();
        labelsBox.getChildren().addAll(label);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(backgroundRectangle, labelsBox);
        doParentBindings();
        this.getChildren().add(sp);
    }

    @Override
    protected void doParentBindings() {
        centerX.bind(Bindings.add(Bindings.divide(backgroundRectangle.widthProperty(), 2), translateXProperty()));
        centerY.bind(Bindings.add(translateYProperty(), 1));
    }

    private void installTooltip(String textKey, Integer id) {
        this.tooltip = new Tooltip("Klíč: " + textKey + "\nID: " + id);
        Tooltip.install(this, tooltip);
    }

}
