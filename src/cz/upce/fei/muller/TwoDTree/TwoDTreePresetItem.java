package cz.upce.fei.muller.TwoDTree;


import cz.commons.utils.presets.PresetItem;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

/**
* @author Vojtěch Müller
*/
public class TwoDTreePresetItem implements PresetItem<Coordinate> {

    private final String name;
    private final Coordinate[] items;

    public TwoDTreePresetItem(String name, Coordinate[] items) {
        this.name = name;
        this.items=items;
    }

    @Override
    public Coordinate[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name;
    }

}
