package cz.upce.fei.muller.TwoDTree.structure;

import java.util.Comparator;

/**
* @author Vojtěch Müller
*/
class ComparatorX<T extends ICoordinate> implements Comparator<T> {

    @Override
    public int compare(T t, T t1) {
        return t.getX() - t1.getX();
    }

}
