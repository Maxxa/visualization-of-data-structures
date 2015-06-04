package cz.muller;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.muller.binaryHeap.structure.BinaryHeap;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;
import cz.upce.fei.muller.binaryHeap.structure.HeapType;
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
        heap.insert(new HeapNode(15));
        heap.insert(new HeapNode(6));
        heap.insert(new HeapNode(36));
        heap.insert(new HeapNode(9));
        heap.insert(new HeapNode(7));
        heap.insert(new HeapNode(7));
        heap.insert(new HeapNode(47));
        heap.insert(new HeapNode(52));
        heap.insert(new HeapNode(30));
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
