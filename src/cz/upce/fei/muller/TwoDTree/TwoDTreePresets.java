package cz.upce.fei.muller.TwoDTree;

import cz.commons.utils.presets.Preset;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

import java.util.ArrayList;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreePresets implements Preset<Coordinate, TwoDTreePresetItem> {

    @Override
    public ArrayList<TwoDTreePresetItem> getAll() {
        ArrayList<TwoDTreePresetItem> presets = new ArrayList<>();
//        presets.add(new TwoDTreePresetItem("Sada 1", ""));
        return presets;
    }

}
