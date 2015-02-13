package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.trie.structure.TrieNode;

/**
 * @author Vojtěch Müller
 */
public class GoToNode extends AbstractEvent{
    public GoToNode(TrieNode temp) {
        super(temp);
    }

    public TrieNode getToNode(){
        return (TrieNode) this.element;
    }

}
