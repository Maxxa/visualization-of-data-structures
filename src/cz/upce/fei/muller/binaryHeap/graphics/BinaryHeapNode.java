package cz.upce.fei.muller.binaryHeap.graphics;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TopLevelAttribute;
import cz.commons.graphics.BinaryNodeWithLine;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;
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
public class BinaryHeapNode extends BinaryNodeWithLine implements IBinaryNodesElements{

    private final HeapNode node;
    private Rectangle backgroundRectangle;
    private Label label = new Label();

    private Tooltip tooltip;

    public BinaryHeapNode(HeapNode node, int x, int y) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);

        initLabel(node);
        initBackground();

        VBox labelsBox = new VBox();
        labelsBox.getChildren().addAll(label);
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

    private void initLabel(HeapNode node) {
        String text = String.valueOf(node.getValue());
        label.setText(text);
        label.setMaxWidth(WIDTH);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-weight: bold;");
        this.tooltip = new Tooltip(text);
        Tooltip.install(this,tooltip);
    }

    @Override
    protected void doParentBindings() {
        centerX.bind(Bindings.add(Bindings.divide(backgroundRectangle.widthProperty(), 2), translateXProperty()));
        centerY.bind(Bindings.add(translateYProperty(), 1));
    }

}
