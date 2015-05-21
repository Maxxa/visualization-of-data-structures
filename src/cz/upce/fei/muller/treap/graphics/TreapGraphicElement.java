package cz.upce.fei.muller.treap.graphics;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class TreapGraphicElement extends BinaryNodeWithLine implements ITreapBinaryNodesElements {

    private final TreapNodeImpl node;
    private Rectangle backgroundRectangle;
    private Label labelKey = new Label();
    private Label labelPriority = new Label();

    private Tooltip tooltip;

    public TreapGraphicElement(TreapNodeImpl node, int x, int y) {
        this(node,x,y,false);
    }

    public TreapGraphicElement(TreapNodeImpl node, int x, int y, boolean isSearchBlock) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);
        initBackground(isSearchBlock);

        VBox labelsBox = new VBox();
        labelsBox.getChildren().addAll(initLabel());
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

    private void initBackground(boolean isSearchBlock) {
        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(isSearchBlock?BG_SEARCH_COLOR:BG_COLOR);
    }

    private Node initLabel() {
        String textKey = String.valueOf(node.getKey());
        String textPriority = String.valueOf(node.getPriority());
        installTooltip(textKey, textPriority);
        labelKey.setText(textKey);
        labelPriority.setText(textPriority);

        Double size = Double.valueOf((WIDTH / 2));
        labelKey.setMinWidth(size);
        labelPriority.setMinWidth(size);

        VBox hBox = new VBox();
        hBox.setAlignment(Pos.CENTER);
        labelKey.setAlignment(Pos.CENTER);
        labelPriority.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(WIDTH);
        labelKey.setStyle("-fx-font-weight: bold;");

        hBox.getChildren().addAll(
                labelKey, new Separator(Orientation.HORIZONTAL), labelPriority
        );
        return hBox;
    }

    private void installTooltip(String textKey, String textPriority) {
        this.tooltip = new Tooltip("Klíč: "+textKey+"\nPriorita: "+textPriority);
        Tooltip.install(this,tooltip);
    }

    public Shape getRect() {
        return backgroundRectangle;
    }
}
