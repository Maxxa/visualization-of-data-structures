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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeapType heapType = (HeapType) o;

        if (compareValue != null ? !compareValue.equals(heapType.compareValue) : heapType.compareValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return compareValue != null ? compareValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return compareValue==-1?"MIN-halda":"MAX-halda";
    }
}
