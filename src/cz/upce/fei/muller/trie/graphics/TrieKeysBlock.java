package cz.upce.fei.muller.trie.graphics;

import cz.commons.graphics.BranchNodeElement;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.HashMap;

/**
 * @author Vojtěch Müller
 */
public class TrieKeysBlock extends BranchNodeElement implements ITrieNodesSetting{

    private static ImagePattern backgroundPattern;

    private HashMap<Character,TrieKey> keys = new HashMap<>();

    private final Rectangle background;

    public TrieKeysBlock(int id,Point2D position) {
        super(id, MAX_KEYS, KEY_WIDTH/2, HEIGHT);
        setTranslateX(position.getX());
        setTranslateY(position.getY());
        background = new Rectangle(KEY_WIDTH*keys.size(), HEIGHT);
        background.setStrokeType(StrokeType.INSIDE);
        background.setStroke(Color.BLACK);
        background.setFill(getPattern());
        getChildren().addAll(background);
        doParentBindings();
    }


    @Override
    protected void doParentBindings() {
        centerX.bind(Bindings.add(background.getX(), translateXProperty()));
        centerY.bind(Bindings.add(translateYProperty(), 1));
    }


    public void addKey(Character key,TrieKey node){
        if(!keys.containsKey(key)){
            keys.put(key,node);
            this.getChildren().add(node);
            background.setWidth(keys.size()*KEY_WIDTH);
        }
    }


    public TrieKey getKey(Character key){
        if(keys.containsKey(key)){
            return keys.get(key);
        }
        return null;
    }

    /**
     * Vytvori vzor pozadi.
     * @return
     */
    private static ImagePattern getPattern() {
        if (backgroundPattern == null) {
            Canvas c = new Canvas(KEY_WIDTH, HEIGHT);
            GraphicsContext gc = c.getGraphicsContext2D();
            gc.setFill(BG_COLOR);
            gc.fillRect(0, 0, c.getWidth() - 1, c.getHeight()); // rozsah zkontrolovan
            gc.setFill(Color.BLACK);
            gc.fillRect(c.getWidth() - 1, 0, c.getWidth() - 1, c.getHeight());
            Image image = c.snapshot(null, null);
            backgroundPattern = new ImagePattern(image, 0, 0, c.getWidth(), c.getHeight(), false);
        }
        return backgroundPattern;
    }


    public DoubleProperty widthProperty() {
        return background.widthProperty();
    }
}
