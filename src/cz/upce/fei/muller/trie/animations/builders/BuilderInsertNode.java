package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import javafx.animation.*;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderInsertNode {

    private final TrieKeysBlock newBlock;
    private final TrieKey blockKey;
    private final TrieKey keyWord;
    private final IBlocksPositions pointPosition;
    private final Character currentCharacter;
    private final LineElement lineElement;

    public BuilderInsertNode(TrieKeysBlock newBlock, TrieKey blockKey, TrieKey keyToColoring, IBlocksPositions pointPosition, Character currentCharacter, LineElement lineElement) {
        this.newBlock = newBlock;
        this.blockKey = blockKey;
        this.keyWord = keyToColoring;
        this.pointPosition = pointPosition;
        this.currentCharacter = currentCharacter;
        this.lineElement = lineElement;
    }

    public Transition getTransition() {
        SequentialTransition pt = new SequentialTransition();
        pt.getChildren().addAll(
                BuilderHelper.colorWordChar(keyWord),
                moveBlock(),
                new ParallelTransition(BuilderHelper.showBlock(newBlock, 0), showKey()),
                BuilderHelper.colorKeyAtBlock(newBlock, currentCharacter),
                showLine()
        );
        return pt;
    }

    private Transition showLine() {
        return FadesTransitionBuilder.getTransition(lineElement, Duration.seconds(1), 0, 1);
    }

    private Transition moveBlock() {
        TranslateTransition tt = TranslateTransitionBuilder.create()
                .node(newBlock)
                .duration(Duration.ONE)
                .fromX(0).fromY(0).toX(pointPosition.getPositionBlock().getX()).toY(pointPosition.getPositionBlock().getY())
                .build();
        return tt;
    }

    private Animation showKey() {
        return FadesTransitionBuilder.getTransition(blockKey, Duration.seconds(1), 0, 1);
    }

}
