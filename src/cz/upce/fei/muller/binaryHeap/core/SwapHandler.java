package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.muller.binaryHeap.events.SwapNodeEvent;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class SwapHandler {

    private BinaryHeapNode nodeOne;
    private BinaryHeapNode nodeSecond;
    private ITreeLayoutManager manager;
    private SwapNodeEvent event;

    private boolean isSwapRootForRemove = false;

    public SwapHandler(BinaryTreeLayoutManager manager, BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond,SwapNodeEvent event) {
        this.manager = manager;
        this.nodeOne = nodeFirst;
        this.nodeSecond = nodeSecond;
        this.event = event;
        initIsSwapRoot();
    }

    private void initIsSwapRoot() {
        System.out.println(String.format("Prohazuji[%s => %s]", nodeOne.getElementId(), nodeSecond.getElementId()));
        if(!controlIsElementChild(nodeOne,nodeSecond.getElementId())){
            isSwapRootForRemove =true;
        }
        System.out.println("swap root? "+isSwapRootForRemove);
    }

    private boolean controlIsElementChild(BinaryHeapNode node, Integer second){
       BinaryHeapNode left = (BinaryHeapNode) node.getLeftChild();
       BinaryHeapNode right = (BinaryHeapNode) node.getRightChild();
        if(left!=null && left.getElementId()==second || right!=null && right.getElementId()==second){
            return true;
        }
        return false;
    }


    protected IAnimationBuilder getCreator() {
        return isSwapRootForRemove?
            new BuilderSwapRoot(nodeOne, nodeSecond,
                manager.getNodePosition(event.getFirstNode().getId()),
                manager.getNodePosition(event.getSecondNode().getId()),
                (BinaryHeapNode) manager.getElementInfo(manager.getElementInfo(nodeSecond.getElementId()).getIdParent()).getElement()
            ):
            new BuilderSwapNode(nodeOne, nodeSecond,
                    manager.getNodePosition(event.getFirstNode().getId()),
                    manager.getNodePosition(event.getSecondNode().getId())
            );
    };

}
