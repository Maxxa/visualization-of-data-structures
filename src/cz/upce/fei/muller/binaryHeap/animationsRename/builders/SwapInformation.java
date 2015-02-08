package cz.upce.fei.muller.binaryHeap.animationsRename.builders;

import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.upce.fei.muller.binaryHeap.graphics.BinaryHeapNode;

/**
* @author Vojtěch Müller
*/
public class SwapInformation {
    WorkBinaryNodeInfo first;
    WorkBinaryNodeInfo second;
//        BinaryHeapNode first;
//        BinaryHeapNode second;

    NodePosition positionFromFirst;

    BinaryHeapNode firstParent;

    NodePosition firstParentPosition;

    public WorkBinaryNodeInfo getFirst() {
        return first;
    }

    public WorkBinaryNodeInfo getSecond() {
        return second;
    }

    public NodePosition getPositionFromFirst() {
        return positionFromFirst;
    }

    public BinaryHeapNode getFirstParent() {
        return firstParent;
    }

    public NodePosition getFirstParentPosition() {
        return firstParentPosition;
    }
}
