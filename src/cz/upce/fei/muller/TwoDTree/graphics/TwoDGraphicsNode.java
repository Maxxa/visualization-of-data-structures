package cz.upce.fei.muller.TwoDTree.graphics;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
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
        this(node,x,y,false);
    }

    public TwoDGraphicsNode(Coordinate node, int x, int y,boolean isSearchBlock) {
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

    private void initBackground(boolean isSearchBlock) {
        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(isSearchBlock?BG_COLOR_SEARCH:BG_COLOR);
    }

    private Node initLabel() {
        String x = String.valueOf(node.getX());
        String y = String.valueOf(node.getY());
        instalTooltip(x, y);
        labelX.setText(x);
        labelY.setText(y);
        Double size = Double.valueOf((WIDTH / 2));
        labelX.setMinWidth(size);
        labelY.setMinWidth(size);
        VBox hBox = new VBox();
        hBox.setAlignment(Pos.CENTER);
        labelX.setAlignment(Pos.CENTER);
        labelY.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(WIDTH);

        hBox.getChildren().addAll(
                labelX, new Separator(Orientation.HORIZONTAL), labelY
        );

        return hBox;

    }

    private void instalTooltip(String x, String y) {
        this.tooltip = new Tooltip(String.format("[ %s ; %s ]", x, y));
        Tooltip.install(this, tooltip);
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

    public void setLabelBold(boolean x,boolean bold){
        Label label = x?labelX:labelY;
        if(bold){
            label.setStyle("-fx-font-weight: bold");
        }else{
            label.setStyle("-fx-font-weight: normal");
        }
    }




}
