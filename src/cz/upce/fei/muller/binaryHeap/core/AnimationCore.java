package cz.upce.fei.muller.binaryHeap.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.muller.binaryHeap.events.*;

/**
 * @author Vojtěch Müller
 */
public class AnimationCore {

    private final ITreeLayoutManager manager;

    public AnimationCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.manager = manager;
    }

    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent ce) {
        System.out.println("_Handle Create root_");
    }

    @Subscribe
    public void handleCompareNodeEvent(CompareNodeEvent ce) {
        System.out.println("_Handle compare_");
    }

    @Subscribe
    public void handleEndEvent(LastEvent ce) {
        System.out.println("_Handle LAST EVENT_\n\n");
    }

    @Subscribe
    public void handleInsertNodeEvent(InsertNodeEvent ce) {
        System.out.println("_Handle Insert Node_");
    }

    @Subscribe
    public void handleRemoveRootEvent(RemoveRootEvent ce) {
        System.out.println("_Handle Remove Root_");
    }

    @Subscribe
    public void handleSwapNodeEvent(SwapNodeEvent ce) {
        System.out.println("_Handle Swap Node_");
    }

}
