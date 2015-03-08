package cz.upce.fei.muller.trie.manager;

import com.google.common.eventbus.EventBus;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
class BlockManager extends AbstractTrieManager<BlockKeyInfo> {

    private final Integer id;

    private final EventBus eventBus;

    protected Point2D blockPosition;

    protected BlockManager leftBlock = null;
    protected BlockManager rightBLock = null;

    BlockManager(Integer id, Point2D blockPosition, EventBus eventBus) {
        this.id = id;
        this.eventBus = eventBus;
        this.blockPosition = blockPosition;
    }

    public Integer getLeftChild(Character fromCharacter) {
        return iterationLeft(getCharacterPosition(fromCharacter));
    }

    public Integer getRightChild(Character fromCharacter) {
        return iterationRight(getCharacterPosition(fromCharacter));
    }

    private Integer iterationLeft(int position) {
        for (int i = position; i >= 0; i--) {
            if (blocks[i] != null && exist(blocks[i])) {
                if (get(blocks[i]).childBlockId != null)
                    return get(blocks[i]).childBlockId;
            }
        }
        if (leftBlock != null) {
            return leftBlock.iterationRight(blocks.length - 1);
        }
        return null;
    }

    private Integer iterationRight(int position) {
        for (int i = position; i < blocks.length; i++) {
            if (blocks[i] != null && exist(blocks[i])) {
                if (get(blocks[i]).childBlockId != null)
                    return get(blocks[i]).childBlockId;
            }
        }
        if (rightBLock != null) {
            return rightBLock.iterationRight(0);
        }
        return null;
    }

    /**
     * Moving all left block at row
     */
    protected void moveCurrent(double diffX) {
        if (leftBlock != null) leftBlock.moveToLeft(-diffX);
        if (rightBLock != null) rightBLock.moveToRight(diffX);
        double halfDiffX = diffX / 2;
        move(-halfDiffX, true);
    }

    /**
     * Moving all right block at row
     */
    protected void moveToLeft(double diffX) {
        move(diffX, false);
        if (leftBlock != null) {
            leftBlock.moveToLeft(diffX);
        }
    }

    protected void moveToRight(double diffX) {
        move(diffX, false);
        if (rightBLock != null) {
            rightBLock.moveToRight(diffX);
        }
    }

    private void move(double diffX, boolean changeBlock) {
        Point2D tmp = new Point2D(blockPosition.getX() + diffX, blockPosition.getY());
        eventBus.post(new MoveBlockEvent(id, blockPosition, tmp, changeBlock));
        blockPosition = tmp;
    }

    public void setIdChildBlock(Character character, Integer idChild) {
        if (exist(character)) {
            get(character).childBlockId = idChild;
        }
    }

    public void addCharacterInfo(Character key, int i) {
        BlockKeyInfo info = new BlockKeyInfo();
        info.positionAtBlock = i;
        this.add(key, info);
        blocks[getCharacterPosition(key)] = key;
    }
}
