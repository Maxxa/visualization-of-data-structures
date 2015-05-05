package cz.upce.fei.muller.treap.animations.builders;

import javafx.animation.FillTransition;
import javafx.animation.FillTransitionBuilder;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderHelper {

    public static Transition getColorinShape(Shape rect,Color fromColor,Color toColor){
        FillTransition st = FillTransitionBuilder.create()
                .duration(Duration.seconds(1))
                .shape(rect)
                .fromValue(fromColor)
                .toValue(toColor)
                .build();
        return st;
}
    }
