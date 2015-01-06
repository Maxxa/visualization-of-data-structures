package cz.upce.fei.muller.binaryHeap.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.graphics.LineElement;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.upce.fei.muller.binaryHeap.animations.*;
import cz.upce.fei.muller.binaryHeap.events.*;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class AnimationsEventsHandlersCore {

    private final AnimationControl animationControl;
    private final BinaryTreeLayoutManager manager;

    /**
     * TODO
     * Varianty
     *  udalosti posunuti si ulozit a pridat je az po dokonceni vlozeni ?
     *
     * */

    public AnimationsEventsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.animationControl = animationControl;
        this.manager = (BinaryTreeLayoutManager) manager;
        manager.getDepthManager().addEventConsumer(this);
    }

    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent event) {
        System.out.println("_Handle Create root_");
        manager.addElement(new BinaryHeapNode(event.getHeapNode(), 0, 0), null, false);
        insertTransition(new CreatorAddElement(manager.getNodePosition(event.getHeapNode().getId()),getNode(event.getHeapNode().getId())));
    }

    @Subscribe
    public void handleCompareNodeEvent(CompareNodeEvent event) {
        System.out.println("_Handle compare_");
        insertTransition(new CreatorCompareNode(getNode(event.getFirstNode().getId()), getNode(event.getSecondNode().getId()), event.isTrue()));
    }

    @Subscribe
    public void handleEndEvent(LastEvent event) {
        System.out.println("_Handle LAST EVENT_\n\n");
        //
        /**
         * TODO
         *  i must control its animation for step
         *  start animation....
         *  MYBE removing? then remove node?
         *
         * */

        manager.rebuildElements();
    }

    @Subscribe
    public void handleInsertNodeEvent(InsertNodeEvent event) {
        System.out.println("_Handle Insert Node_");
        manager.addElement(new BinaryHeapNode(event.getNewNode(), 0, 0), event.getParentNode().getId(), event.isLeftChild());
        BinaryHeapNode parent = getNode(event.getParentNode().getId());
        BinaryHeapNode newNode = getNode(event.getNewNode().getId());
        LineElement line = new LineElement(parent,newNode);
        manager.getCanvas().getChildren().addAll(line);
        insertTransition(new CreatorAddElement(manager.getNodePosition(event.getNewNode().getId()), getNode(event.getNewNode().getId())));
        System.out.println("_END handle insert");
    }

    @Subscribe
    public void handleRemoveRootEvent(RemoveRootEvent event) {
        System.out.println("_Handle Remove Root_");
        insertTransition(new CreatorRemoveRoot(getNode(event.getRootNode().getId())));
        manager.removeElement(event.getRootNode().getId());//TODO MY BE NEED remove element after animation ?
    }

    @Subscribe
    public void handleSwapNodeEvent(SwapNodeEvent event) {
        System.out.println("_Handle Swap Node_");
        insertTransition(new CreatorSwapNode(getNode(event.getFirstNode().getId()), getNode(event.getSecondNode().getId())));
    }

    @Subscribe
    public void handleMoveElementNodeEvent(MoveElementEvent event) {
        System.out.println("_Handle move elemenent Node_");
        insertTransition(new CreatorAnimMoveNode(event.getOldPoint(),event.getNewPoint(),getNode(event.getElementId())));
    }

    private BinaryHeapNode getNode(Integer elementId){
        return (BinaryHeapNode) manager.getElementInfo(elementId).getElement();
    }

    private void insertTransition(IAnimationCreator creator){
        animationControl.getTransitions().add(creator.getAnimation());
    }

}
