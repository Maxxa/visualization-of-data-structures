package cz.upce.fei.muller.trie.animations.builders;

import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

/**
 * @author Vojtěch Müller
 */
public class BuilderInsertNode {
    private final TrieKeysBlock newBlock;
    private final TrieKey blockKey;
    private final TrieKey keyToColoring;
    private final IBlocksPositions pointPosition;

    public BuilderInsertNode(TrieKeysBlock newBlock, TrieKey blockKey, TrieKey keyToColoring, IBlocksPositions pointPosition) {
        this.newBlock = newBlock;
        this.blockKey = blockKey;
        this.keyToColoring = keyToColoring;
        this.pointPosition = pointPosition;

        System.out.println(pointPosition.getPositionBlock());


    }

    public Transition getTransition(){
        ParallelTransition pt=  new ParallelTransition();

        return pt;
    }

}
