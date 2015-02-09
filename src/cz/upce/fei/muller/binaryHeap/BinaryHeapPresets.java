package cz.upce.fei.muller.binaryHeap;

import cz.commons.utils.Generator;
import cz.commons.utils.presets.Preset;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;

import java.util.ArrayList;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapPresets implements Preset<HeapNode, BinaryHeapPresetItem> {
    
    private static final int GENERATE_MIN_NUMBERS = 10;
    private static final int GENERATE_MAX_NUMBERS = 31;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 999;

    @Override
    public ArrayList<BinaryHeapPresetItem> getAll() {
        ArrayList<BinaryHeapPresetItem> presets = new ArrayList<>();
        
        presets.add(new BinaryHeapPresetItem("Sada 1", new Integer[]{1,1,1}));
        presets.add(new BinaryHeapPresetItem("Sada 1", new Integer[]{40,11,9,35,2,30,18,34,17,15,7}));
        presets.add(new BinaryHeapPresetItem("Sada 2", new Integer[]{12,7,6,10,8,20,23,46,11,9,13,28,49,13,15}));
        presets.add(new BinaryHeapPresetItem("Sada 3", new Integer[]{19,13,3,9,1,4,36,5,9,19,25,42,17,47,9}));
        presets.add(generate());

        return presets;
    }
    
    private BinaryHeapPresetItem generate() {
        int numbersResult = Generator.generate(GENERATE_MIN_NUMBERS, GENERATE_MAX_NUMBERS);

        Integer[] numbers = new Integer[numbersResult];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Generator.generate(MIN_NUMBER, MAX_NUMBER);
        }
        
        return new BinaryHeapPresetItem("Náhodná", numbers);
    }

}
