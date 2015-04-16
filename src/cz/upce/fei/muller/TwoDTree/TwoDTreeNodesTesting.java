package cz.upce.fei.muller.TwoDTree;

import cz.commons.example.AbstractExample;
import cz.upce.fei.muller.TwoDTree.animations.builders.BuilderHelper;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
        ParallelTransition transition = new ParallelTransition();
        transition.setDelay(Duration.seconds(3));
        SequentialTransition st = new SequentialTransition(
                transition,
                BuilderHelper.getColorinShape(node.getRect(), Color.BLACK,Color.GREEN),
        BuilderHelper.getColorinShape(node.getRect(), Color.GREEN,Color.BLACK)
        );
        st.play();
    }

    @Override
    protected String getTitle() {
        return "Default Node Element Example";
    }

    public static void main(String[] args) {launch(args);}
}
