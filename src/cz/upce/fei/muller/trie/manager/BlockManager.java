package cz.upce.fei.muller.trie.manager;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
class BlockManager extends AbstractTrieManager<BlockKeyInfo> implements ITrieNodesSetting{

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
            return leftBlock.iterationLeft(blocks.length - 1);
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
     * Moving all right block at row
     */
    protected void moveToLeft(double diffX,double minimalSpace) {
        move(diffX, false);
        if (leftBlock != null) {
            double dif = blockPosition.getX() -(leftBlock.blockPosition.getX() + leftBlock.getWidth());
            if (dif < minimalSpace) {
                leftBlock.moveToLeft(dif - minimalSpace, minimalSpace);
            }
        }
    }

    protected void moveToRight(double diffX,double minimalSpace) {
        move(diffX, false);
        if (rightBLock != null) {
            double dif = rightBLock.blockPosition.getX()-(blockPosition.getX()+getWidth());
            if(dif < minimalSpace){
                rightBLock.moveToRight(minimalSpace-dif,minimalSpace);
            }

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

    public double getWidth(){
        return size()*KEY_WIDTH;
    }

    public void clear() {
        for (int i = 0; i < blocks.length; i++) {
            if(exist(blocks[i])){
                get(blocks[i]).childBlockId=null;
            }
        }
        
    }
}
