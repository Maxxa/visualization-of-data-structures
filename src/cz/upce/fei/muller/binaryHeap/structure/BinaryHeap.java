package cz.upce.fei.muller.binaryHeap.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.structures.BinaryNode;
import cz.upce.fei.common.structures.BinaryTreeDyn;
import cz.upce.fei.common.structures.IHeap;
import cz.upce.fei.muller.binaryHeap.events.CreateRoot;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeap implements IHeap<Integer> {

    private final EventBus eventBus;
    private final HeapPositionType positionType;
    private final HeapType type;

    private BinaryNode<HeapNode> actualNode;

    BinaryTreeDyn<HeapNode> tree = new BinaryTreeDyn<>();

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
            actualNode = tree.getRootNode();
            eventBus.post(new CreateRoot(newNode));
        }else{
            boolean isLeft =true;
            if(tree.getLeft(actualNode)==null){
                tree.insertLeftChild(actualNode,newNode);
            }else{
                isLeft=false;
                tree.insertRightChild(actualNode,newNode);
            }
            changeActual(isLeft);



        }
    }

    private void changeActual(boolean isLeft) {

    }

    private void setActualForNewDepth(){
        BinaryNode<HeapNode> node = tree.getRootNode();
        BinaryNode<HeapNode> temp = null;
        while (true){
            if(positionType.equals(HeapPositionType.LEFTWAR)){
                temp = tree.getLeftNode(node);
            }else{
                temp = tree.getRightNode(node);
            }
            if(temp!=null){
                node = temp;
            }else{
                break;
            }
        }
        actualNode = node;
    }

    @Override
    public Integer removeRoot() {
        return 0;
    }


}
