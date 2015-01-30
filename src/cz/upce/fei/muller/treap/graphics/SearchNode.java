package cz.upce.fei.muller.treap.graphics;

import cz.commons.graphics.Element;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * @author Vojtěch Müller
 */
public class SearchNode extends Element implements IBinaryNodesElements {

    private Circle backgroundCircle;

    public SearchNode(int id, int x, int y, String searchString) {
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
