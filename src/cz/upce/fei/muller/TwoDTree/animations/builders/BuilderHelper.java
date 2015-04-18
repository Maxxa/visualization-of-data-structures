package cz.upce.fei.muller.TwoDTree.animations.builders;

import cz.commons.animation.StepEventHandler;
import javafx.animation.FillTransition;
import javafx.animation.FillTransitionBuilder;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
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
        st.setOnFinished(new StepEventHandler() {
            @Override
            protected void handleForward(ActionEvent actionEvent) {

                System.out.println("for");
            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                System.out.println("back");

            }
        });
        return st;
}
    }
