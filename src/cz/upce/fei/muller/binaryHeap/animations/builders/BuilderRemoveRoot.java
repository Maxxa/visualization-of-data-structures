package cz.upce.fei.muller.binaryHeap.animations.builders;

import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.muller.binaryHeap.animations.RemovePreparation;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderRemoveRoot implements IAnimationBuilder {

    private final RemovePreparation removePreparation;

    public BuilderRemoveRoot(RemovePreparation removePreparation) {
        this.removePreparation = removePreparation;
    }

    @Override
    public Transition getAnimation() {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().add(FadesTransitionBuilder.getTransition(removePreparation.getLineToRemoved(), Duration.seconds(1), 1, 0));
        pt.getChildren().add(FadesTransitionBuilder.getTransition(removePreparation.getRemovedElement(), Duration.seconds(1), 1, 0));
        return pt;
    }

}
