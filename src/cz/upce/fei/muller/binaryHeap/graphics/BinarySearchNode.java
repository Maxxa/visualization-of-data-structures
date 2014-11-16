package cz.upce.fei.muller.binaryHeap.graphics;

import cz.commons.graphics.BinaryNodeElement;
import cz.commons.graphics.Element;
import cz.commons.graphics.NodeElement;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class BinarySearchNode extends Element implements IBinaryNodesElements{

    private Circle backgroundCircle;

    public BinarySearchNode(int id,int x,int y,String searchString) {
        this.id=id;
        setTranslateX(x);
        setTranslateY(y);

        backgroundCircle = new Circle();

        backgroundCircle.setRadius(HEIGHT/2);
        backgroundCircle.setStrokeType(StrokeType.INSIDE);
        backgroundCircle.setStroke(BG_STROKE);
        backgroundCircle.setFill(BG_SEARCH_COLOR);

        this.getChildren().addAll(backgroundCircle);
    }
}
