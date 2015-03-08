package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.animation.StepEventHandler;
import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
class BuilderHelper {

    public static Transition colorWordChar(final TrieKey keyWord) {
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

    public static Transition colorKeyAtBlock(TrieKeysBlock block,Character character) {
        StrokeTransition st = StrokeTransitionBuilder.create()
                .duration(Duration.seconds(2))
                .shape(block.getKey(character).getRect())
                .fromValue(Color.TRANSPARENT)
                .toValue(Color.RED)
                .build();
        return st;
    }

    public static Transition showBlock(TrieKeysBlock block,int beginSize) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(
                        Duration.ZERO
                        , new KeyValue(block.widthProperty(), beginSize)
                ),
                new KeyFrame(
                        Duration.seconds(1)
                        , new KeyValue(block.widthProperty(), beginSize+ITrieNodesSetting.KEY_WIDTH)
                )
        );
        return new ParallelTransition(timeline);
    }
}
