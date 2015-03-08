package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class GoToNode extends AbstractEvent{

    private final Character current;

    public GoToNode(Character current, TrieNode temp) {
        super(temp);
        this.current = current;
    }

    public Character getCurrent() {
        return current;
    }

    public TrieNode getNode() {
        return (TrieNode) element;
    }

    @Override
    public String toString() {
        return this.element.toString();
    }
}
