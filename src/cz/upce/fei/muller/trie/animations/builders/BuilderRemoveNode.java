package cz.upce.fei.muller.trie.animations.builders;

import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

/**
 * @author Vojtěch Müller
 */
public class BuilderRemoveNode {

    private final IBlocksPositions positions;
    private final TrieKeysBlock block;

    public BuilderRemoveNode(IBlocksPositions positions, TrieKeysBlock block) {

        this.positions = positions;
        this.block = block;
    }

    public Transition getTransition(){
        ParallelTransition pt = new ParallelTransition();
        return pt;
    }
}
