package cz.upce.fei.muller.binaryHeap.graphics;

import cz.commons.graphics.BinaryNodeElement;
import javafx.beans.binding.Bindings;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapNode extends BinaryNodeElement implements IBinaryNodesElements{

    private Rectangle backgroundRectangle;

    public BinaryHeapNode(int id, int x, int y, String val) {
        super(id, WIDTH, HEIGHT);

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
