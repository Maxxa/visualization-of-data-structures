package cz.upce.fei.muller.trie.animations.builders;

import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import javafx.animation.ParallelTransition;

/**
 * @author Vojtěch Müller
 */
public class BuilderGoToNode {

    private final Character character;
    private final TrieKey keyWord;
    private final TrieKeysBlock block;

    public BuilderGoToNode(Character character, TrieKey keyWord, TrieKeysBlock block) {
        this.character = character;
        this.keyWord = keyWord;
        this.block = block;
    }

    public ParallelTransition getTransition() {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(BuilderHelper.colorWordChar(keyWord), BuilderHelper.colorKeyAtBlock(block,character));
        return pt;
    }




}
