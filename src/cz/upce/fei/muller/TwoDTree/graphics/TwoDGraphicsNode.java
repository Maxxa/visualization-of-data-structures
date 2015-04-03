package cz.upce.fei.muller.TwoDTree.graphics;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class TwoDGraphicsNode extends BinaryNodeWithLine implements ITwoDNodesElements {

    private final Coordinate node;
    private Rectangle backgroundRectangle;
    private Label labelX = new Label();
    private Label labelY = new Label();

    private Tooltip tooltip;

    public TwoDGraphicsNode(Coordinate node, int x, int y) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);
        initBackground();

        VBox labelsBox = new VBox();
        labelsBox.getChildren().addAll(initLabel());
        labelsBox.setStyle("-fx-padding: 5px;");
        StackPane sp = new StackPane();
        sp.getChildren().addAll(backgroundRectangle, labelsBox);
        doParentBindings();
        this.getChildren().add(sp);
    }

    private void initBackground() {
        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(BG_COLOR);
    }

    private HBox initLabel() {
        String x = String.valueOf(node.getX());
        String y = String.valueOf(node.getY());
        labelX.setText(x);
        labelY.setText(y);
        Double size = Double.valueOf((WIDTH / 2));
        labelX.setMinWidth(size);
        labelY.setMinWidth(size);
        this.tooltip = new Tooltip(String.format("[ %s ; %s ]", x, y));
        Tooltip.install(this, tooltip);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        labelX.setAlignment(Pos.CENTER);
        labelY.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(WIDTH);
        hBox.setStyle("-fx-font-weight: bold;");

        hBox.getChildren().addAll(
                labelX, new Separator(Orientation.VERTICAL), labelY
        );

        return hBox;

    }

    @Override
    protected void doParentBindings() {
        centerX.bind(Bindings.add(Bindings.divide(backgroundRectangle.widthProperty(), 2), translateXProperty()));
        centerY.bind(Bindings.add(translateYProperty(), 1));
    }

    public Coordinate getNode() {
        return node;
    }

    public void setLabelXColor(boolean isDefault) {
        setLabel(labelX, isDefault);
    }

    public void setLabelYColor(boolean isDefault) {
        setLabel(labelY, isDefault);
    }

    private void setLabel(Label label, boolean isDefault) {
        if (isDefault) {
            label.setStyle("-webkit-fx-color: black;-moz-fx-color: black;-ms-fx-color: black;-o-fx-color: black;fx-color: black;");
        } else {
            label.setStyle("-webkit-fx-color: red;-moz-fx-color: red;-ms-fx-color: red;-o-fx-color: red;fx-color: red;");

        }
    }

}
