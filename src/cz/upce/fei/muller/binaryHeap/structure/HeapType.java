package cz.upce.fei.muller.binaryHeap.structure;

/**
 * @author Vojtěch Müller
 */
public final class HeapType {

    public final Integer compareValue;

    public static final HeapType MIN = new HeapType(-1);

    public static final HeapType MAX = new HeapType(1);

    private HeapType(Integer compareValue) {
        this.compareValue = compareValue;
    }
}
