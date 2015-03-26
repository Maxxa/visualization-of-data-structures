package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderRemoveNode {

    private final IBlocksPositions positions;
    private final TrieKeysBlock block;
    private final Character character;
    private final LineElement line;

    public BuilderRemoveNode(IBlocksPositions positions, TrieKeysBlock block,Character character, LineElement line) {
        this.positions = positions;
        this.block = block;
        this.character = character;
        this.line = line;
    }

    public Transition getTransition(){
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(hideKey(),hideLine(),hideBlock());
        return pt;
    }

    private Animation hideKey() {
        return FadesTransitionBuilder.getTransition(block.getKey(character), Duration.seconds(1), 1, 0);
    }

    private Animation hideLine() {
        return FadesTransitionBuilder.getTransition(line, Duration.seconds(1), 1, 0);
    }

    private Animation hideBlock() {
        return BuilderHelper.resizeBlock(block, (int) positions.getWidthBefore(), 0);
    }
}
