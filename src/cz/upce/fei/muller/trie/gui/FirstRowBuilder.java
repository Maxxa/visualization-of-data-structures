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

    @Override
    public TrieKeysBlock getRootBlock(){
        Character[] upperCaseCharacters = TrieUtils.getLowerCaseCharacters();
        TrieKeysBlock root = new TrieKeysBlock(1, new Point2D(0, 0));
        for (int i = 0; i < upperCaseCharacters.length; i++) {
            root.addKey(upperCaseCharacters[i], new TrieKey(String.valueOf(upperCaseCharacters[i]), i * ITrieNodesSetting.KEY_WIDTH));
        }
        TrieKey one = root.getKey('a');
        System.out.println(one);
        return root;
    }

    @Override
    public Character[] getKeys() {
        return new Character[0];
    }

}
