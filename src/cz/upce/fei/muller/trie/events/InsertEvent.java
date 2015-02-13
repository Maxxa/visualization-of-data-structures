package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class InsertEvent extends AbstractEvent {

    public InsertEvent(AbstractStructureElement element) {
        super(element);
    }

    public InsertEvent(Character current, TrieNode temp) {
        super(temp);
    }
}
