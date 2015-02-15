package cz.upce.fei.muller.trie;

import cz.commons.example.AbstractExample;
import cz.commons.graphics.LineElement;
import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.TrieUtils;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class TestGraphics extends AbstractExample {


    @Override
    protected void initPane(Pane canvas) {
        Character[] upperCaseCharacters = TrieUtils.getUpperCaseCharacters();
        TrieKeysBlock block = new TrieKeysBlock(1,new Point2D(10,50));
        for (int i = 0; i < upperCaseCharacters.length; i++) {
            block.addKey(upperCaseCharacters[i],new TrieKey(String.valueOf(upperCaseCharacters[i]),i* ITrieNodesSetting.KEY_WIDTH));
        }

        TrieKeysBlock block2 = new TrieKeysBlock(1,new Point2D(50,100));
        block2.addKey('A',new TrieKey("A",0));
        block2.addKey('K',new TrieKey("K",20));

        LineElement lineElement = new LineElement(block.getChildConnector(5),block2);
        canvas.getChildren().addAll(block,block2,lineElement);

    }

    @Override
    protected String getTitle() {
        return "TeST";
    }

    public static void main(String[] args) {launch(args);}
}
