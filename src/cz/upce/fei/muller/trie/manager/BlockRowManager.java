package cz.upce.fei.muller.trie.manager;

import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vojtěch Müller
 */
public class BlockRowManager {

    private final LayoutManagerSetting setting;
    private Map<Integer, BlockManager> blocksAtRow = new HashMap<>();

    public BlockRowManager(LayoutManagerSetting setting) {
        this.setting = setting;
    }

    public void addRoot(Integer id, double widthCanvas, Character[] keys) {
        BlockManager rootBlock = new BlockManager(id,
                new Point2D(
                        widthCanvas / 2 - ((setting.getMinNodeWidth() * (TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN)) / 2) //ROOT X
                        , setting.getPaddingTop() //ROOT Y
                ), setting.eventBus
        );
        blocksAtRow.put(id, rootBlock);
        for (int i = 0; i < keys.length; i++) {
            rootBlock.addCharacterInfo(keys[i], i);
        }
    }

    public BlockManager get(Integer id) {
        return blocksAtRow.get(id);
    }

    public void createFirstBlock(Integer id, Point2D parentPosition, Character insertedChar) {
        BlockManager newBlock = new BlockManager(id,
                new Point2D(parentPosition.getX(), parentPosition.getY() + setting.getNodeHeight() + setting.getVerticalSpace()),
                setting.eventBus
        );
        newBlock.addCharacterInfo(insertedChar, 0);
        blocksAtRow.put(id,newBlock);
    }

    public Point2D getPositionKey(Integer id, Character key) {
        BlockManager blockManager = get(id);
        BlockKeyInfo info = blockManager.get(key);
        return new Point2D(blockManager.blockPosition.getX() + info.positionAtBlock * setting.getMinNodeWidth(), blockManager.blockPosition.getY());
    }
}
