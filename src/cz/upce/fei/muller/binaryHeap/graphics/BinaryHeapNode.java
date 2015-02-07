package cz.upce.fei.muller.binaryHeap.graphics;

import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;
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
public class BinaryHeapNode extends BinaryNodeWithLine implements IBinaryNodesElements{

    private final HeapNode node;
    private Rectangle backgroundRectangle;
    private Label label = new Label();

    public BinaryHeapNode(HeapNode node, int x, int y) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);

        label.setText(String.valueOf("("+node.getId()+") "+node.getValue()));
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

}
