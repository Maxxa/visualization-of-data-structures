package cz.upce.fei.muller.binaryHeap;


import cz.commons.utils.presets.PresetItem;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

/**
* @author Vojtěch Müller
*/
public class BinaryHeapPresetItem implements PresetItem<HeapNode> {

    private final String name;
    private final HeapNode[] items;

    public BinaryHeapPresetItem(String name, Integer[] items) {
        this.name = name;
        this.items = new HeapNode[items.length];
        for (int i = 0; i <items.length; i++) {
            this.items[i] = new HeapNode(items[i]);
        }

    }

    @Override
    public HeapNode[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name;
    }

}
