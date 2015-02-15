package cz.upce.fei.muller.trie.manager;

import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class FirstRowBuilder {

    private final Pane canvas;
    private final LayoutManagerSetting setting;

    public FirstRowBuilder(Pane canvas,LayoutManagerSetting setting) {
        this.canvas = canvas;
        this.setting = setting;
    }


    public TrieKeysBlock getRootBlock(){
        TrieKeysBlock root = new TrieKeysBlock(0,null);
        Character[] characters = TrieUtils.getLowerCaseCharacters();

        return root;
    }




}
