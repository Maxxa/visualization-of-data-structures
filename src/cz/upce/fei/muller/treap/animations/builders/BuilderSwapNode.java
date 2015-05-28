package cz.upce.fei.muller.treap.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.commons.utils.transitions.RelativeTranslateTransition;
import cz.upce.fei.common.animations.SwitchConnectorHelper;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.treap.animations.handlers.FadesTransitionHandler;
import cz.upce.fei.muller.treap.animations.handlers.SwapElementEndEventHandler;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderSwapNode implements IAnimationBuilder {

    protected final DefaultSwapInformation information;
    private final List<SwitchConnectorHelper> helpers;

    public BuilderSwapNode(DefaultSwapInformation information, List<SwitchConnectorHelper> helpers) {
        this.information = information;
        this.helpers = helpers;
    }

    @Override
    public ParallelTransition getAnimation() {
        ParallelTransition pt = getMovingElements();
        SequentialTransition sq = new SequentialTransition(getLinesFades(true), pt, getLinesFades(false));
        pt.setOnFinished(setFinishedHandler());
        return new ParallelTransition(sq);
    }

    private EventHandler<ActionEvent> setFinishedHandler() {
        return new SwapElementEndEventHandler(helpers);
    }

    private ParallelTransition getLinesFades(boolean visibility) {
        ParallelTransition pt = new ParallelTransition();

        for (SwitchConnectorHelper element : helpers) {
            addFadeToTransition(pt, element.getLine(), visibility);
        }

        pt.setOnFinished(new FadesTransitionHandler(helpers, visibility));
        return pt;
    }

    private void addFadeToTransition(ParallelTransition pt, LineElement element, boolean visibility) {
        pt.getChildren().add(getFadeTransition(element, visibility));
    }

    private FadeTransition getFadeTransition(LineElement element, boolean visibility) {
        int from = visibility ? 1 : 0;
        int to = visibility ? 0 : 1;
        return FadesTransitionBuilder.getTransition(element, Duration.ONE, from, to);
    }

    protected ParallelTransition getMovingElements() {
        TranslateTransition t1 = RelativeTranslateTransition.build(information.infoSecondElement.get().getElement(), information.secondNodePosition, information.firstNodePosition, new Duration(1000));
        TranslateTransition t2 = RelativeTranslateTransition.build(information.infoFirstElement.get().getElement(), information.firstNodePosition, information.secondNodePosition, new Duration(1000));
        return new ParallelTransition(t1, t2);
    }


}
