package cz.upce.fei.muller.treap.structure;

import java.util.Comparator;

/**
 * @author Vojtěch Müller
 */
public interface IPriorityKeyContainer<T>{

    public Integer getPriority();

    public T getKey();

    public Comparator<T> getComparator();

}
