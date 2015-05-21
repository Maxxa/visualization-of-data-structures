package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class ClearTerminalSymbol extends AbstractEvent{
    private final Character character;

    public ClearTerminalSymbol(AbstractStructureElement removeNode, Character character) {
        super(removeNode);
        this.character = character;
    }

    public TrieNode getRemovedTerminalSymbol(){
        return (TrieNode) this.element;
    }

    public Character getCharacter() {
        return character;
    }
}
