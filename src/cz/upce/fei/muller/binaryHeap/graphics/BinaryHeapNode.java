package cz.upce.fei.muller.binaryHeap.graphics;

import cz.commons.graphics.BinaryNodeElement;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;
import javafx.beans.binding.Bindings;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapNode extends BinaryNodeElement implements IBinaryNodesElements{

    private final HeapNode node;
    private Rectangle backgroundRectangle;

    public BinaryHeapNode(HeapNode node, int x, int y) {
        super(node.getId(), WIDTH, HEIGHT);
        this.node = node;
        setTranslateX(x);
        setTranslateY(y);

        backgroundRectangle = new Rectangle(WIDTH, HEIGHT);
        backgroundRectangle.setStrokeType(StrokeType.INSIDE);
        backgroundRectangle.setStroke(BG_STROKE);
        backgroundRectangle.setFill(BG_COLOR);
        this.getChildren().add(backgroundRectangle);
    }

    @Override
    protected void doParentBindings() {
        centerX.bind(Bindings.add(Bindings.divide(backgroundRectangle.widthProperty(), 2), translateXProperty()));
        centerY.bind(Bindings.add(translateYProperty(), 1));
    }
}
