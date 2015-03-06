package cz.upce.fei.muller.trie.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

import java.util.Hashtable;

/**
 * @author Vojtěch Müller
 */
public class TrieNode<T extends Description> extends AbstractStructureElement {

    public Hashtable<Character, TrieNode> next;
    public T object;
    public TrieNode parent;

    TrieNode() {
        next = new Hashtable<>();
        object = (T) T.EMPTY;
        parent = null;
    }

    public TrieNode(TrieNode parent) {
        this();
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("Current[%d][%s]%s", getId(),object,parent!=null?" - parent ["+parent.getId().toString()+"]":"");
    }
}
