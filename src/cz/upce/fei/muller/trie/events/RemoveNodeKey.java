package cz.upce.fei.muller.trie.events;

import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class RemoveNodeKey {
    private final TrieNode removed;
    private final char character;
    private Character parentKey;

    public RemoveNodeKey(TrieNode removed, char character, Character parentKey) {
        this.removed = removed;
        this.character = character;
        this.parentKey = parentKey;
    }

    public TrieNode getRemoved() {
        return removed;
    }

    public char getCharacter() {
        return character;
    }

    public Character getParentKey() {
        return parentKey;
    }
}
