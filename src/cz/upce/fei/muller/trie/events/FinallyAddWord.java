package cz.upce.fei.muller.trie.events;

import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class FinallyAddWord {
    private final TrieNode wordNode;
    private final Character character;

    public FinallyAddWord(TrieNode wordNode, Character character) {
        this.wordNode = wordNode;
        this.character = character;
    }

    public TrieNode getWordNode() {
        return wordNode;
    }

    public Character getCharacter() {
        return character;
    }
}
