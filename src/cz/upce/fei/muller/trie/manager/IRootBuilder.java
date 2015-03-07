package cz.upce.fei.muller.trie.manager;

import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;

/**
 * @author Vojtěch Müller
 */
public interface IRootBuilder {
    TrieKeysBlock getRootBlock();

    Character[] getKeys();
}
