package cz.upce.fei.muller.binaryHeap.animationsRename;

import cz.commons.layoutManager.*;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.binaryHeap.animationsRename.builders.BuilderSwapNode;
import cz.upce.fei.muller.binaryHeap.animationsRename.builders.BuilderSwapRoot;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class SwapPreparation implements IPreparation{

    private  WorkBinaryNodeInfo infoFirst;
    private  WorkBinaryNodeInfo infoSecond;
//    private BinaryHeapNode nodeOne;
//    private BinaryHeapNode nodeSecond;
    private ITreeLayoutManager manager;
//    private SwapNodeEvent event;

    private boolean isSwapRootForRemove = false;

//    public SwapPreparation(BinaryTreeLayoutManager manager, BinaryHeapNode nodeFirst, BinaryHeapNode nodeSecond, SwapNodeEvent event) {
//        this.manager = manager;
////        this.nodeOne = nodeFirst
////        this.nodeSecond = nodeSecond;
//      //  this.event = event;
//        initIsSwapRoot();
//    }
    public SwapPreparation(ITreeLayoutManager manager,WorkBinaryNodeInfo infoFirstElement, WorkBinaryNodeInfo infoSecondElement){
        this.manager = manager;
        this.infoFirst = infoFirstElement;
        this.infoSecond = infoSecondElement;
        initIsSwapRoot();
    }

    private void initIsSwapRoot() {
        isSwapRootForRemove = !infoFirst.hasParent() &&
                infoSecond.get().getIdParent()!= infoFirst.get().getElement().getElementId();
        System.out.print(String.format("Prohazuji[%s => %s]", infoFirst.get().getElement().getElementId(), infoSecond.get().getElement().getElementId()));
        System.out.println(" swap root: " + isSwapRootForRemove);
    }

    @Override
    public IAnimationBuilder getBuilder() {
        return isSwapRootForRemove ?
                getBuilderSwapRoot() : getBuilderDefaultSwap();
    };

//    private BinaryHeapNode getParent(BinaryHeapNode node) {
//        ElementInfo elementInfo = manager.getElementInfo(node.getElementId());
//        if(elementInfo.getIdParent()==null){
//            return null;
//        }
//        return (BinaryHeapNode) manager.getElementInfo(elementInfo.getIdParent()).getElement();
//    }

    private IAnimationBuilder getBuilderSwapRoot() {
        BinaryHeapNode parent= (BinaryHeapNode) infoSecond.getParent().getElement();;
        return  new BuilderSwapRoot(infoFirst,infoSecond,
                manager.getNodePosition(infoFirst.get().getElement().getElementId()),
                manager.getNodePosition(infoSecond.get().getElement().getElementId()),
                parent,getParentPosition(infoSecond.getParent().getIndexAtRow(), infoSecond.get().getIndexAtRow())
        );
    }

//    private NodePosition getParentPosition(BinaryHeapNode parent, BinaryHeapNode nodeSecond) {
//        Integer indexAtRow = manager.getElementInfo(parent.getElementId()).getIndexAtRow();
//        Integer indexAtRowChild = manager.getElementInfo(nodeSecond.getElementId()).getIndexAtRow();
//        return BinaryTreeHelper.getLeftChildIndex(indexAtRow)==indexAtRowChild?NodePosition.LEFT:NodePosition.RIGHT;
//    }

    private IAnimationBuilder getBuilderDefaultSwap() {
        BinaryHeapNode parent = infoFirst.getParent()!=null? (BinaryHeapNode) infoFirst.getParent().getElement() :null;
        return   new BuilderSwapNode(infoFirst, infoSecond,
                     manager.getNodePosition(infoFirst.get().getElement().getElementId()),
                     manager.getNodePosition(infoSecond.get().getElement().getElementId()),
                     parent,
                     getParentPosition(infoFirst.get().getIndexAtRow(), infoSecond.get().getIndexAtRow()),
                     parent!=null?getParentPosition(infoFirst.getParent().getIndexAtRow(), infoFirst.get().getIndexAtRow()):null
            );
    }

    private NodePosition getParentPosition(Integer indexAtRow, Integer indexAtRowChild) {
        return BinaryTreeHelper.getLeftChildIndex(indexAtRow)==indexAtRowChild?NodePosition.LEFT:NodePosition.RIGHT;
    }
}
