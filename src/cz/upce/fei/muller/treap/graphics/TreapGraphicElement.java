package cz.upce.fei.muller.treap.graphics;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
    private Label label = new Label();

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
        initLabel();
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

    private void initBackground(boolean isSearchBlock) {
        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(isSearchBlock?BG_SEARCH_COLOR:BG_COLOR);
    }

    private void initLabel() {
        String text = String.valueOf(node.getKey());
        label.setText(text);
        label.setMaxWidth(WIDTH);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-weight: bold;");
        this.tooltip = new Tooltip(node.getId().toString());
        Tooltip.install(this,tooltip);
    }

    public Shape getRect() {
        return backgroundRectangle;
    }
}
