package cz.upce.fei.muller.treap;


import cz.commons.utils.presets.PresetItem;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

/**
* @author Vojtěch Müller
*/
public class TreapPresetItem implements PresetItem<TreapNodeImpl> {

    private final String name;
    private final TreapNodeImpl[] items;

    public TreapPresetItem(String name, Integer[] items) {
        this.name = name;
        this.items = new TreapNodeImpl[items.length];
        for (int i = 0; i <items.length; i++) {
            this.items[i] = new TreapNodeImpl(items[i]);
        }

    }

    @Override
    public TreapNodeImpl[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name;
    }

}
