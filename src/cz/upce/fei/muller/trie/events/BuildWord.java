package cz.upce.fei.muller.trie.events;

import cz.upce.fei.muller.trie.structure.Description;

/**
 * @author Vojtěch Müller
 */
public class BuildWord{

    private final Description inserted;
    private final boolean isNew;

    public BuildWord(Description inserted, boolean isNew) {
        this.inserted = inserted;
        this.isNew = isNew;
    }

    public Description getWord() {
        return inserted;
    }
}
