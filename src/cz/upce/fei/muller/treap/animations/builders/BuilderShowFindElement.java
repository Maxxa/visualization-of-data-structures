package cz.upce.fei.muller.treap.animations.builders;

import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.treap.graphics.ITreapBinaryNodesElements;
import cz.upce.fei.muller.treap.graphics.TreapGraphicElement;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderShowFindElement implements IAnimationBuilder,ITreapBinaryNodesElements {

    private final TreapGraphicElement node;

    public BuilderShowFindElement(TreapGraphicElement node) {
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
