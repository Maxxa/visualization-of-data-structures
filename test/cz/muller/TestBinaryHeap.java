package cz.muller;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.structures.BinaryTreeToArray;
import cz.upce.fei.common.structures.IBinaryTreeToArray;
import cz.upce.fei.muller.binaryHeap.structure.BinaryHeap;
import cz.upce.fei.muller.binaryHeap.structure.HeapType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Vojtěch Müller
 */
public class TestBinaryHeap {

    EventBus bus;

    BinaryHeap heap;

    @Before
    public void init(){
        bus = new EventBus();

    }

    @Test
    public void createAndInserMax(){
        heap = new BinaryHeap(bus, HeapType.MAX);

        initParamHeap();

    }

    private void initParamHeap() {
        heap.insert(15);
        heap.insert(6);
        heap.insert(36);
        heap.insert(9);
        heap.insert(7);
        heap.insert(7);
        heap.insert(47);
        heap.insert(52);
        heap.insert(3);
    }

    @Test
    public void createAndInserMin(){
        heap = new BinaryHeap(bus, HeapType.MIN);
        initParamHeap();

    }

    @Test
    public void removeFromMin(){
        createAndInserMin();

       heap.removeRoot();
       heap.removeRoot();
       heap.removeRoot();


    }

    @Test
    public void removeFromMax(){
        createAndInserMax();
        heap.removeRoot();
        heap.removeRoot();
        heap.removeRoot();
    }

}
