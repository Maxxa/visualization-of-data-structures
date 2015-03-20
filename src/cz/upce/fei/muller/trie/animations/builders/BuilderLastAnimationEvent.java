package cz.upce.fei.muller.trie.animations.builders;

import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class BuilderLastAnimationEvent {

    private final List<TrieKey> currentWord;
    private final List<TrieKey> coloredShapes;

    public BuilderLastAnimationEvent(List<TrieKey> currentWord, List<TrieKey> coloredShapes) {
        this.currentWord = currentWord;
        this.coloredShapes = coloredShapes;
    }

    public Transition getTransition() {
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(buildHiddenWord(), buildHiddenStroke());
        return pt;
    }

    private Animation buildHiddenWord() {
        ParallelTransition pt = new ParallelTransition();
        for (final TrieKey wordKey: currentWord){
            FadeTransition ft = FadesTransitionBuilder.getTransition(wordKey, Duration.ONE, 1, 0);
//            ft.setOnFinished(new StepEventHandler() {
//
//                TrieKey trieKey = wordKey;
//
//                @Override
//                protected void handleForward(ActionEvent actionEvent) {
//                    trieKey.setVisible(false);
//                    trieKey.setOpacity(0);
//                }
//
//                @Override
//                protected void handleBack(ActionEvent actionEvent) {
//                    trieKey.setVisible(true);
//                    trieKey.setOpacity(1);
//                }
//            });
            pt.getChildren().add(ft);
        }
        return pt;
    }

    private Animation buildHiddenStroke() {
        ParallelTransition pt = new ParallelTransition();
        for (TrieKey key: coloredShapes){
            StrokeTransition st = StrokeTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .shape(key.getRect())
                    .fromValue(Color.RED)
                    .toValue(Color.TRANSPARENT)
                    .build();
            pt.getChildren().add(st);
        }
        return pt;
    }
}
