package cz.upce.fei.muller.binaryHeap.core;

import cz.commons.layoutManager.BinaryTreeHelper;
import cz.commons.layoutManager.BinaryTreeLayoutManager;
import cz.commons.layoutManager.ElementInfo;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.upce.fei.common.graphics.NodePosition;
import cz.upce.fei.muller.binaryHeap.events.SwapNodeEvent;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class SwapPreparation {

    private BinaryHeapNode nodeOne;
    private BinaryHeapNode nodeSecond;
    private ITreeLayoutManager manager;
    private SwapNodeEvent event;

    private boolean isSwapRootForRemove = false;

    public SwapPreparation(BinaryTreeLayoutManager manager, BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond, SwapNodeEvent event) {
        this.manager = manager;
        this.nodeOne = nodeFirst;
        this.nodeSecond = nodeSecond;
        this.event = event;
        initIsSwapRoot();
    }

    private void initIsSwapRoot() {
        System.out.print(String.format("Prohazuji[%s => %s]", nodeOne.getElementId(), nodeSecond.getElementId()));
        ElementInfo elementInfo = manager.getElementInfo(nodeSecond.getElementId());
        isSwapRootForRemove = elementInfo.getIdParent()!=nodeOne.getElementId();
        System.out.println(" swap root: "+isSwapRootForRemove);
        if(isSwapRootForRemove){

        System.out.println(String.format("Parent top Node element id "));
        }
    }

    protected IAnimationBuilder getCreator() {
        return isSwapRootForRemove ?
                getBuilderSwapRoot() : getBuilderDefaultSwap();
    };

    private BinaryHeapNode getParent(BinaryHeapNode node) {
        ElementInfo elementInfo = manager.getElementInfo(node.getElementId());
        if(elementInfo.getIdParent()==null){
            return null;
        }
        return (BinaryHeapNode) manager.getElementInfo(elementInfo.getIdParent()).getElement();
    }

    public IAnimationBuilder getBuilderSwapRoot() {
        BinaryHeapNode parent=getParent(nodeSecond);
        return  new BuilderSwapRoot(nodeOne, nodeSecond,
                manager.getNodePosition(event.getFirstNode().getId()),
                manager.getNodePosition(event.getSecondNode().getId()),
                parent,getParentPosition(parent, nodeSecond)
        );
    }

    private NodePosition getParentPosition(BinaryHeapNode parent, BinaryHeapNode nodeSecond) {
        Integer indexAtRow = manager.getElementInfo(parent.getElementId()).getIndexAtRow();
        Integer indexAtRowChild = manager.getElementInfo(nodeSecond.getElementId()).getIndexAtRow();
        return BinaryTreeHelper.getLeftChildIndex(indexAtRow)==indexAtRowChild?NodePosition.LEFT:NodePosition.RIGHT;
    }

    public IAnimationBuilder getBuilderDefaultSwap() {
        BinaryHeapNode parent = getParent(nodeOne);
        return   new BuilderSwapNode(nodeOne, nodeSecond,
                    manager.getNodePosition(event.getFirstNode().getId()),
                    manager.getNodePosition(event.getSecondNode().getId()),
                    parent,
                    getParentPosition(nodeOne, nodeSecond),
                    parent!=null?getParentPosition(parent, nodeOne):null

            );
    }
}
