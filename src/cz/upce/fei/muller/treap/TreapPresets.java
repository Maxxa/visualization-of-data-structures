package cz.upce.fei.muller.treap;

import cz.commons.utils.Generator;
import cz.commons.utils.presets.Preset;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vojtěch Müller
 */
public class TreapPresets implements Preset<TreapNodeImpl, TreapPresetItem> {
    
    private static final int GENERATE_MIN_NUMBERS = 10;
    private static final int GENERATE_MAX_NUMBERS = 20;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 200;

    @Override
    public ArrayList<TreapPresetItem> getAll() {
        ArrayList<TreapPresetItem> presets = new ArrayList<>();
        
        presets.add(new TreapPresetItem("Sada 1", new Integer[]{24,4,9,23,69,45,21,94,6,2},
                                        new Integer[]{961,932,788,26,536,878,471,688,59,500}));
        presets.add(new TreapPresetItem("Sada 2", new Integer[]{40,11,9,35,2,30,18,34,17,15,7}));
        presets.add(new TreapPresetItem("Sada 3", new Integer[]{12,7,6,10,8,20,23,46,11,9,13,28,49,13,15}));
        presets.add(new TreapPresetItem("Sada 4", new Integer[]{19,13,3,1,4,36,5,9,19,25,42,17,47,9}));
        presets.add(generate());

        return presets;
    }
    
    private TreapPresetItem generate() {
        int numbersResult = Generator.generate(GENERATE_MIN_NUMBERS, GENERATE_MAX_NUMBERS);

        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < numbersResult; i++) {
            numbers.add(Generator.generate(MIN_NUMBER, MAX_NUMBER));
        }
        
        return new TreapPresetItem("Náhodná", numbers.toArray(new Integer[]{}));
    }

}
