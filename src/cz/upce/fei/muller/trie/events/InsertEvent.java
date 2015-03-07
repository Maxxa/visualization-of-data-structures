package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class InsertEvent extends AbstractEvent {

    private final Character currentCharacter;

    public InsertEvent(Character current, TrieNode element) {
        super(element);
        this.currentCharacter = current;
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public TrieNode getInsertedNode(){
        return (TrieNode) element;
    }

    @Override
    public String toString() {
        return " ... ["+ currentCharacter +"] "+this.element.toString();
    }
}
