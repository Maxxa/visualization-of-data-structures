package cz.upce.fei.muller.trie.manager;

import com.google.common.eventbus.EventBus;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
class BlockManager extends AbstractTrieManager<BlockKeyInfo> {

    private final Integer id;
    /**
     * This is X position of parent
     */
    private DoubleProperty parentXPosition;
    private final EventBus eventBus;

    protected Point2D blockPosition;

    protected BlockManager leftBlock = null;
    protected BlockManager rightBLock = null;

    BlockManager(Integer id, DoubleProperty parentXPosition,Point2D blockPosition, EventBus eventBus) {
        this.id = id;
        this.parentXPosition = parentXPosition;
        this.eventBus = eventBus;
        this.blockPosition = blockPosition;
    }






    /**
     * Moving all left block at row
     */
    protected void moveCurrent(double diffX) {
        if (leftBlock != null) leftBlock.moveToLeft(-diffX);
        if (rightBLock != null) rightBLock.moveToRight(diffX);
        double halfDiffX = diffX / 2;
        move(-halfDiffX,true);
    }

    /**
     * Moving all right block at row
     */
    private void moveToLeft(double diffX) {
        move(diffX,false);
        if (leftBlock != null) {
            leftBlock.moveToLeft(diffX);
        }
    }

    private void moveToRight(double diffX) {
        move(diffX,false);
        if (rightBLock != null) {
            rightBLock.moveToRight(diffX);
        }
    }

    private void move(double diffX, boolean changeBlock) {
        Point2D tmp = new Point2D(blockPosition.getX() + diffX, blockPosition.getY());
        eventBus.post(new MoveBlockEvent(id, blockPosition, tmp, changeBlock));
        blockPosition = tmp;
    }

}
