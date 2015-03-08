package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class InsertEvent extends AbstractEvent {

    private final Character currentCharacter;
    private final Character parentKey;

    public InsertEvent(Character current, TrieNode element, Character parentKey) {
        super(element);
        this.currentCharacter = current;
        this.parentKey = parentKey;
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public TrieNode getInsertedNode(){
        return (TrieNode) element;
    }

    public Character getParentKey() {
        return parentKey;
    }

    @Override
    public String toString() {
        return " ... ["+ currentCharacter +"] "+this.element.toString()+(parentKey!=null?String.format("[%s]",parentKey):"");
    }
}
