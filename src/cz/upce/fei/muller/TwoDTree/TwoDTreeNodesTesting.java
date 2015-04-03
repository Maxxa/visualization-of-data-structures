package cz.upce.fei.muller.TwoDTree;

import cz.commons.example.AbstractExample;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreeNodesTesting extends AbstractExample {

    @Override
    protected void initPane(Pane canvas) {

        TwoDGraphicsNode node = new TwoDGraphicsNode(
                new Coordinate(155555,555555),50,50
        );
        canvas.getChildren().addAll(node);
    }

    @Override
    protected String getTitle() {
        return "Default Node Element Example";
    }

    public static void main(String[] args) {launch(args);}
}
