package cz.upce.fei.muller.TwoDTree.animations.builders;

import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderShowFindElement implements IAnimationBuilder{

    private final TwoDGraphicsNode node;

    public BuilderShowFindElement(TwoDGraphicsNode node) {
        this.node = node;
    }

    @Override
    public Transition getAnimation() {
        System.out.println("build");

        ParallelTransition transition = new ParallelTransition();
        transition.setDelay(Duration.seconds(1));
        return new SequentialTransition(
                BuilderHelper.getColorinShape(node.getRect(), Color.BLACK, Color.GREEN),
                transition,
                BuilderHelper.getColorinShape(node.getRect(), Color.GREEN,Color.BLACK)
        );
    }
}
