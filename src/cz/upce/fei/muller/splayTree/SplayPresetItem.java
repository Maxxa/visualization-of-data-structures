package cz.upce.fei.muller.splayTree;


import cz.commons.utils.presets.PresetItem;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
* @author Vojtěch Müller
*/
public class SplayPresetItem implements PresetItem<SplayNodeImpl> {

    private final String name;
    private final SplayNodeImpl[] items;

    public SplayPresetItem(String name, Integer[] items) {
        this.name = name;
        this.items = new SplayNodeImpl[items.length];
        for (int i = 0; i <items.length; i++) {
            this.items[i] = new SplayNodeImpl(items[i]);
        }
    }

    @Override
    public SplayNodeImpl[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name;
    }

}
