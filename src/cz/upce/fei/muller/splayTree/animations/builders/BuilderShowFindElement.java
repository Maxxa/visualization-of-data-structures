package cz.upce.fei.muller.splayTree.animations.builders;

import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.splayTree.graphics.ISplayNodesElements;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import cz.upce.fei.muller.treap.animations.builders.BuilderHelper;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderShowFindElement implements IAnimationBuilder,ISplayNodesElements {

    private final SplayGraphicsNodeElement node;

    public BuilderShowFindElement(SplayGraphicsNodeElement node) {
        this.node = node;
    }

    @Override
    public Transition getAnimation() {
        ParallelTransition transition = new ParallelTransition();
        transition.setDelay(Duration.seconds(1));
        return new SequentialTransition(
                BuilderHelper.getColorinShape(node.getRect(), BG_COLOR, Color.LIGHTGREEN),
                transition,
                BuilderHelper.getColorinShape(node.getRect(), Color.LIGHTGREEN, BG_COLOR)
        );
    }
}
