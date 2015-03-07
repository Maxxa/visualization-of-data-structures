package cz.upce.fei.muller.trie.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vojtěch Müller
 */
abstract class AbstractTrieManager<T> {

    private Map<Character, T> data = new HashMap<>();
    private Integer maxSize = TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN;
    protected Character[] blocks = new Character[maxSize];

    protected boolean exist(Character character) {
        return data.containsKey(character);
    }

    protected int getCharacterPosition(Character character) {
        int position = TrieUtils.getCharacterPositionAtArray(character);
        if (position < 0 || position >= maxSize) {
            throw new IllegalArgumentException("Character:" + character + " is not allowed");
        }
        return position;
    }

    protected boolean isEmpty() {
        return data.isEmpty();
    }

    protected void add(Character key, T val) {
        data.put(key, val);
    }

    protected T get(Character key) {
        return data.get(key);
    }

    protected T remove(Character key) {
        return data.remove(key);
    }


}
