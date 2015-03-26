package cz.upce.fei.muller.trie.structure;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public interface ITrie<T> {

    void clear();

    void add(T inserted);

    T remove(T key);

    List<T> get(T key);

    Integer getCount();

}
