package cz.upce.fei.muller.trie.core;

import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class RemoveHelper {

    Character character;
    private final Pane canvas;
    TrieKeysBlock block;

    public RemoveHelper(Pane canvas,TrieKeysBlock block) {
        this.canvas = canvas;
        this.block = block;
    }

    public RemoveHelper(Pane canvas,TrieKeysBlock block,Character character) {
        this.canvas = canvas;
        this.character = character;
        this.block = block;
    }

    public void remove(){
        if(character==null){
            canvas.getChildren().remove(block);
            block = null;
        }else{
            TrieKey key = block.getKey(character);
            canvas.getChildren().remove(key);
            block.removeKey(character);
        }
    }

}
