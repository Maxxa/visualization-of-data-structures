package cz.upce.fei.muller.TwoDTree.structure;

import java.util.Comparator;

/**
* @author Vojtěch Müller
*/
class ComparatorY<T extends ICoordinate> implements Comparator<T> {

    @Override
    public int compare(T t, T t1) {
        return t.getY() - t1.getY();
    }

}
