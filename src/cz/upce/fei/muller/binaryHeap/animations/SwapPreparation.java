package cz.upce.fei.muller.binaryHeap.animations;

import cz.commons.layoutManager.*;
import cz.commons.graphics.NodePosition;
import cz.upce.fei.common.core.IAnimationBuilder;
import cz.upce.fei.common.core.IPreparation;
import cz.upce.fei.muller.binaryHeap.animations.builders.BuilderSwapNode;
import cz.upce.fei.muller.binaryHeap.animations.builders.BuilderSwapRoot;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
 * @author Vojtěch Müller
 */
public class SwapPreparation implements IPreparation{

    private  WorkBinaryNodeInfo infoFirst;
    private  WorkBinaryNodeInfo infoSecond;
    private ITreeLayoutManager manager;

    private boolean isSwapRootForRemove = false;

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

    private IAnimationBuilder getBuilderSwapRoot() {
        BinaryHeapNode parent= infoSecond.getParent().getElement();;
        return  new BuilderSwapRoot(infoFirst,infoSecond,
                manager.getNodePosition(infoFirst.get().getElement().getElementId()),
                manager.getNodePosition(infoSecond.get().getElement().getElementId()),
                parent,getParentPosition(infoSecond.getParent().getIndexAtRow(), infoSecond.get().getIndexAtRow())
        );
    }

    private IAnimationBuilder getBuilderDefaultSwap() {
        BinaryHeapNode parent = infoFirst.getParent()!=null? (BinaryHeapNode) infoFirst.getParent().getElement() :null;
        return   new BuilderSwapNode(infoFirst, infoSecond,
                     manager.getNodePosition(infoFirst.get().getElement().getElementId()),
                     manager.getNodePosition(infoSecond.get().getElement().getElementId()),
                     getParentPosition(infoFirst.get().getIndexAtRow(), infoSecond.get().getIndexAtRow()),
                     parent!=null?getParentPosition(infoFirst.getParent().getIndexAtRow(), infoFirst.get().getIndexAtRow()):null
            );
    }

    private NodePosition getParentPosition(Integer indexAtRow, Integer indexAtRowChild) {
        return BinaryTreeHelper.getLeftChildIndex(indexAtRow)==indexAtRowChild?NodePosition.LEFT:NodePosition.RIGHT;
    }
}
