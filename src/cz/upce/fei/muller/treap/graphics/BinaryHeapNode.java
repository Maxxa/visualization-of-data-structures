package cz.upce.fei.muller.treap.graphics;

import cz.commons.graphics.BinaryNodeElement;
import cz.commons.graphics.LineElement;
import cz.upce.fei.muller.treap.structure.TreapNode;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapNode extends BinaryNodeElement implements IBinaryNodesElements {

    private final TreapNode node;
    private Rectangle backgroundRectangle;
    private Label label = new Label();

    private LineElement[] lineToChild = new LineElement[2];

    public BinaryHeapNode(TreapNode node, int x, int y) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);

        label.setText(String.valueOf(node.getValue()));
        label.setMaxWidth(WIDTH);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-weight: bold;");

        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(BG_COLOR);

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

    public void setChildLine(LineElement element, boolean isLeft){
        lineToChild[isLeft?0:1]= element;
    }

    public LineElement getChildLine(boolean isLeft){
        return lineToChild[isLeft?0:1];
    }







}
