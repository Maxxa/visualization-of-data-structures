package cz.upce.fei.muller.trie.manager;

import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public class MoveKeyEvent {
    private final Integer blockId;
    private final Character characterAtBlock;
    private final Point2D oldPoint;
    private final Point2D newPoint;

    public MoveKeyEvent(Integer blockId,Character characterAtBlock, Point2D oldPoint, Point2D newPoint) {
        this.blockId = blockId;
        this.characterAtBlock = characterAtBlock;
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
    }

    public Character getCharacterAtBlock() {
        return characterAtBlock;
    }

    public Point2D getOldPoint() {
        return oldPoint;
    }

    public Point2D getNewPoint() {
        return newPoint;
    }

    public Integer getBlockId() {
        return blockId;
    }

    @Override
    public String toString() {
        return "MoveKeyEvent{" +
                "blockId=" + blockId +
                ", characterAtBlock=" + characterAtBlock +
                ", oldPoint=" + oldPoint +
                ", newPoint=" + newPoint +
                '}';
    }
}
