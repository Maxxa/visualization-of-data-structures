package cz.upce.fei.muller.binaryHeap.structure;

/**
 * @author Vojtěch Müller
 */
public class HeapCompare {

    private final HeapType type;

    public HeapCompare(HeapType type) {
        this.type = type;
    }

    public boolean compare(HeapNode first,HeapNode second){
        if(first != null && second != null) {
            return type.equals(HeapType.MIN) && first.compareTo(second) == 1 ||
                   type.equals(HeapType.MAX) && first.compareTo(second) == -1;
        }
        return false;
    }

    public int compareTo(HeapNode first,HeapNode second){
        if(first != null && second!= null) {

            int res = first.compareTo(second);
            if(res==0)
                return res;

            if(type.equals(HeapType.MIN)){ // Minimal heap
                return res == -1? 1:-1;
            }else{ // Maximal heap
                return res == 1? 1:-1;
            }

        }else if(first!=null && second==null){
            return type.equals(HeapType.MIN)? -1:1;
        }else if(first==null && second!=null){
            return type.equals(HeapType.MIN)? -1:1;
        }else{
            return 0;
        }

    }

    public int getCompareValue(){
        return type.compareValue;
    }

}
