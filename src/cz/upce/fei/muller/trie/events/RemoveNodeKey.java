package cz.upce.fei.muller.trie.events;

import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class RemoveNodeKey {
    private final TrieNode removed;
    private final char character;

    public RemoveNodeKey(TrieNode removed, char character) {
        this.removed = removed;
        this.character = character;
    }

    public TrieNode getRemoved() {
        return removed;
    }

    public char getCharacter() {
        return character;
    }
}
