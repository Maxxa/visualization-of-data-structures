package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import javafx.animation.*;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderInsertKeyToNode {

    private final List<Transition> moveKeysTransitions;
    private final TrieKeysBlock block;
    private final TrieKey trieKey;
    private final IBlocksPositions blocksPositions;
    private final TrieKey keyToColored;

    public BuilderInsertKeyToNode(List<Transition> moveKeysTransitions, TrieKeysBlock block, TrieKey trieKey, IBlocksPositions blocksPositions, TrieKey keyToColored) {
        this.moveKeysTransitions = moveKeysTransitions;
        this.block = block;
        this.trieKey = trieKey;
        this.blocksPositions = blocksPositions;
        this.keyToColored = keyToColored;
    }

    public Transition getTransition() {
        SequentialTransition pt=  new SequentialTransition();
        pt.getChildren().addAll(
                BuilderHelper.colorWordChar(keyToColored),
                new ParallelTransition(getMovingKeys(),BuilderHelper.showBlock(block, (int) blocksPositions.getWidthBefore()),showKey()),
                BuilderHelper.colorKeyAtBlock(trieKey)
        );
        return pt;
    }

    private Animation showKey() {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(
                TranslateTransitionBuilder.create().node(trieKey)
                        .fromX(0).fromY(0).
                        toX(blocksPositions.getPositionBlockKey().getX()-blocksPositions.getPositionBlock().getX()).duration(Duration.ONE).build(),
                FadesTransitionBuilder.getTransition(trieKey, Duration.seconds(1), 0, 1)
        );
            return pt;
    }

    public Animation getMovingKeys() {
        System.err.println(moveKeysTransitions.size());
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(moveKeysTransitions);
        return pt;
    }
}
