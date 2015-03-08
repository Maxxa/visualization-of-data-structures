package cz.upce.fei.muller.trie;

import cz.commons.example.AbstractExample;
import cz.commons.graphics.LineElement;
import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.TrieUtils;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class TestGraphics extends AbstractExample {


    @Override
    protected void initPane(Pane canvas) {
        Character[] upperCaseCharacters = TrieUtils.getUpperCaseCharacters();
        TrieKeysBlock block = new TrieKeysBlock(1, new Point2D(10, 50));
        for (int i = 0; i < upperCaseCharacters.length; i++) {
            block.addKey(upperCaseCharacters[i], new TrieKey(String.valueOf(upperCaseCharacters[i]), i * ITrieNodesSetting.KEY_WIDTH));
        }

        TrieKeysBlock block2 = new TrieKeysBlock(1, new Point2D(50, 100));
        block2.addKey('A', new TrieKey("A", 0));
        block2.addKey('K', new TrieKey("K", 20));

        Transition t;

        Timeline timeline = new Timeline();
        Duration duration = Duration.seconds(1);
        if (duration.equals(Duration.ONE)) {
//            background.setWidth(KEY_WIDTH * nKeys);
        }
        KeyValue kv = new KeyValue(block2.widthProperty(), 3 * ITrieNodesSetting.KEY_WIDTH, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(duration, kv);
        timeline.getKeyFrames().addAll(kf);

        ParallelTransition pt = ParallelTransitionBuilder.create().build();
        pt.getChildren().addAll(timeline);

        LineElement lineElement = new LineElement(block.getChildConnector(5), block2);
        canvas.getChildren().addAll(block, block2, lineElement);
        pt.play();

        final DoubleProperty a = new SimpleDoubleProperty(1);
        final DoubleProperty b = new ReadOnlyDoubleWrapper();
        Bindings.bindBidirectional(a,b);
        //a.bindD(b);

        System.out.println(a.get());
        a.setValue(3.0);
        System.out.println(a.get());
        System.out.println(b.get());
        System.out.println(TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN);
         Character[] listBlock = new Character[TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN];
        listBlock[1] = 'A';
        listBlock[3] = 'C';
        timeline.setRate(-1);

        System.out.println(listBlock[2]);
        System.out.println(listBlock[3]);

        TrieKey tl = new TrieKey("L",10);
        TrieKey te = new TrieKey("E",20);
        TrieKey tv = new TrieKey("V",30);
        canvas.getChildren().addAll(tl,te,tv);

    }

    @Override
    protected String getTitle() {
        return "TeST";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
