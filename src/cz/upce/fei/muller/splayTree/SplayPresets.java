package cz.upce.fei.muller.splayTree;

import cz.commons.utils.Generator;
import cz.commons.utils.presets.Preset;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vojtěch Müller
 */
public class SplayPresets implements Preset<SplayNodeImpl, SplayPresetItem> {
    
    private static final int GENERATE_MIN_NUMBERS = 10;
    private static final int GENERATE_MAX_NUMBERS = 20;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 999;

    @Override
    public ArrayList<SplayPresetItem> getAll() {
        ArrayList<SplayPresetItem> presets = new ArrayList<>();
        
        presets.add(new SplayPresetItem("Sada 1", new Integer[]{24,4,9,23,69,45,21,94,6,2}));
//        presets.add(new SplayPresetItem("Sada 2", new Integer[]{40,11,9,35,2,30,18,34,17,15,7}));
//        presets.add(new SplayPresetItem("Sada 3", new Integer[]{12,7,6,10,8,20,23,46,11,9,13,28,49,13,15}));
//        presets.add(new SplayPresetItem("Sada 4", new Integer[]{19,13,3,1,4,36,5,9,19,25,42,17,47,9}));
//        presets.add(generate());
//        presets.add(generate());

        return presets;
    }
    
    private SplayPresetItem generate() {
        int numbersResult = Generator.generate(GENERATE_MIN_NUMBERS, GENERATE_MAX_NUMBERS);

        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < numbersResult; i++) {
            numbers.add(Generator.generate(MIN_NUMBER, MAX_NUMBER));
        }
        
        return new SplayPresetItem("Náhodná", numbers.toArray(new Integer[]{}));
    }

}
