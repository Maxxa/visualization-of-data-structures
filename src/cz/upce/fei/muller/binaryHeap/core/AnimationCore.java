package cz.upce.fei.muller.binaryHeap.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.upce.fei.muller.binaryHeap.events.*;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class AnimationCore {

    private final AnimationControl animationControl;
    private final BinaryTreeLayoutManager manager;

    public AnimationCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        manager.getDepthManager().addEventConsumer(this);
    }

    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent ce) {
        System.out.println("_Handle Create root_");
        manager.addElement(new BinaryHeapNode(ce.getHeapNode(),0,0),null,false);
    }

    @Subscribe
    public void handleCompareNodeEvent(CompareNodeEvent ce) {
        System.out.println("_Handle compare_");
    }

    @Subscribe
    public void handleEndEvent(LastEvent ce) {
        System.out.println("_Handle LAST EVENT_\n\n");
        manager.rebuildElements();
    }

    @Subscribe
    public void handleInsertNodeEvent(InsertNodeEvent ce) {
        System.out.println("_Handle Insert Node_");
        manager.addElement(new BinaryHeapNode(ce.getNewNode(),0,0),ce.getParentNode().getId(),ce.isLeftChild());
    }

    @Subscribe
    public void handleRemoveRootEvent(RemoveRootEvent ce) {
        System.out.println("_Handle Remove Root_");
    }

    @Subscribe
    public void handleSwapNodeEvent(SwapNodeEvent ce) {
        System.out.println("_Handle Swap Node_");
    }

    @Subscribe
    public void handleMoveElementNodeEvent(MoveElementEvent ce) {
        System.out.println("_Handle move elemenent Node_");
    }

}
