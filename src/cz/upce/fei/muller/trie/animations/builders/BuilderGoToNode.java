package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.animation.StepEventHandler;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import javafx.animation.ParallelTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.StrokeTransitionBuilder;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
        pt.getChildren().addAll(colorWordChar(), colorKeyAtBlock());
        return pt;
    }

    private Transition colorWordChar() {
        ParallelTransition pt = new ParallelTransition();
        pt.setDelay(Duration.seconds(1));
        pt.setOnFinished(new StepEventHandler() {
            @Override
            protected void handleForward(ActionEvent actionEvent) {
                keyWord.coloredText();
            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                keyWord.defaultText();
            }
        });
        return pt;
    }

    private Transition colorKeyAtBlock() {
        StrokeTransition st = StrokeTransitionBuilder.create()
                .duration(Duration.seconds(2))
                .shape(block.getKey(character).getRect())
                .fromValue(Color.TRANSPARENT)
                .toValue(Color.RED)
                .build();
        return st;
    }


}
