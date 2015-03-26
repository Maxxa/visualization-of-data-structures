package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderRemoveNodeKey {


    private final IBlocksPositions positions;
    private final TrieKeysBlock block;
    private final char character;
    private final List<Transition> moveKeysTransitions;

    public BuilderRemoveNodeKey(IBlocksPositions positions, TrieKeysBlock block, char character, List<Transition> moveKeysTransitions) {

        this.positions = positions;
        this.block = block;
        this.character = character;
        this.moveKeysTransitions = moveKeysTransitions;
    }

    public Transition getTransition() {
        SequentialTransition pt=  new SequentialTransition();
        pt.getChildren().addAll(
                new ParallelTransition(getMovingKeys(),BuilderHelper.resizeBlock(block, (int) positions.getWidthBefore(), (int) positions.getWidthAfter()),disableKey())
        );
        return pt;
    }

    private Animation disableKey() {
        return FadesTransitionBuilder.getTransition(block.getKey(character), Duration.seconds(1), 1, 0);
    }

    public Animation getMovingKeys() {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(moveKeysTransitions);
        return pt;
    }


}
