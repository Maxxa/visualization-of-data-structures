package cz.upce.fei.muller.binaryHeap.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.structures.BinaryTree;
import cz.upce.fei.common.structures.IHeap;
import cz.upce.fei.muller.binaryHeap.events.CreateRoot;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeap implements IHeap<Integer> {

    private final EventBus eventBus;
    private final HeapPositionType positionType;
    private final HeapType type;

    BinaryTree<HeapNode> tree = new BinaryTree<>();

    public BinaryHeap(EventBus eventBus,HeapPositionType positionType,HeapType type) {
        this.eventBus = eventBus;
        this.positionType = positionType;
        this.type = type;
    }

    public void clear(){
        tree.clear();
    }

    @Override
    public void insert(Integer value) {
        HeapNode newNode= new HeapNode(value);
        if(tree.isEmpty()){
            tree.insertRoot(newNode);
            eventBus.post(new CreateRoot(newNode));
        }else{
            //TODO
        }
    }

    @Override
    public Integer removeRoot() {
        return 0;
    }


}
