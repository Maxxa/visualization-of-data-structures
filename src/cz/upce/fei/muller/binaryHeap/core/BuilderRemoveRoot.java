package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.utils.FadesTransitionBuilder;
import javafx.animation.ParallelTransition;
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
    public ParallelTransition getAnimation() {
        ParallelTransition pt = new ParallelTransition();
        System.out.println("build animation remove");

        if(removePreparation.isLineToRemoved()){
            pt.getChildren().add(FadesTransitionBuilder.getTransition(removePreparation.getLineToRemoved(), Duration.seconds(1), 1, 0));
        }

        pt.getChildren().add(FadesTransitionBuilder.getTransition(removePreparation.getRemovedElement(), Duration.seconds(1), 1, 0));

        return pt;
    }

}
