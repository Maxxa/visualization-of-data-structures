package cz.upce.fei.muller.splayTree.animations;

import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.gui.FlashMessageViewer;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class FlashMessageViewerHelper {

    public static FlashMessageViewer buildViewer(String text,Point2D creatingPoint,Pane canvas) {
        FlashMessageViewer viewer = new FlashMessageViewer(text);
        viewer.setTranslateX(creatingPoint.getX());
        viewer.setTranslateY(creatingPoint.getY());
        canvas.getChildren().addAll(viewer);
        viewer.setOpacity(0);
        return viewer;
    }

    public static Transition showViewer(Node element) {
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(
                FadesTransitionBuilder.getTransition(element, Duration.ONE, 0, 1),
                ParallelTransitionBuilder.create().delay(Duration.seconds(2)).build(),
                FadesTransitionBuilder.getTransition(element, Duration.ONE, 1, 0)
        );
        return st;
    }
}
