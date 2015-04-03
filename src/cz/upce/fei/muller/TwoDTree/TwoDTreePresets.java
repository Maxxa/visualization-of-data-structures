package cz.upce.fei.muller.TwoDTree;

import cz.commons.utils.Generator;
import cz.commons.utils.presets.Preset;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;

import java.util.ArrayList;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreePresets implements Preset<Coordinate, TwoDTreePresetItem> {

    private static final int GENERATE_MIN_NUMBERS = 10;
    private static final int GENERATE_MAX_NUMBERS = 31;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 200;

    @Override
    public ArrayList<TwoDTreePresetItem> getAll() {
        ArrayList<TwoDTreePresetItem> presets = new ArrayList<>();
        presets.add(new TwoDTreePresetItem("Sada 1", getSet1()));
        presets.add(new TwoDTreePresetItem("Sada 2", getSet2()));
        presets.add(new TwoDTreePresetItem("Sada 3", getSet3()));
        presets.add(generate());
        presets.add(generate());
        return presets;
    }

    private TwoDTreePresetItem generate() {
        int numbersResult = Generator.generate(GENERATE_MIN_NUMBERS, GENERATE_MAX_NUMBERS);
        Coordinate[] nodes = new Coordinate[numbersResult];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Coordinate(
                    Generator.generate(MIN_NUMBER, MAX_NUMBER),
                    Generator.generate(MIN_NUMBER, MAX_NUMBER)
            );
        }

        return new TwoDTreePresetItem("Náhodná", nodes);
    }

    private Coordinate[] getSet1() {
        return new Coordinate[]{
            new Coordinate(26,31),
            new Coordinate(52,46),
            new Coordinate(92,12),
        };
    }

    private Coordinate[] getSet2() {
        return new Coordinate[]{

        };
    }

    private Coordinate[] getSet3() {
        return new Coordinate[]{

        };
    }

}
