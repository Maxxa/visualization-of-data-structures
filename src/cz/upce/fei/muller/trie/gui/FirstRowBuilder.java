package cz.upce.fei.muller.trie.gui;

import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IRootBuilder;
import cz.upce.fei.muller.trie.manager.TrieUtils;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class FirstRowBuilder implements IRootBuilder {

    Character[] characters = TrieUtils.getLowerCaseCharacters();

    @Override
    public TrieKeysBlock getRootBlock(){
        TrieKeysBlock root = new TrieKeysBlock(1, new Point2D(0, 0));
        for (int i = 0; i < characters.length; i++) {
            root.addKey(characters[i], new TrieKey(String.valueOf(characters[i]), i * ITrieNodesSetting.KEY_WIDTH));
        }
        return root;
    }

    @Override
    public Character[] getKeys() {
        return characters;
    }

}
