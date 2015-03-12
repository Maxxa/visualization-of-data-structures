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
        addBlock(id,new Point2D(widthCanvas / 2 - ((setting.getMinNodeWidth() * (TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN)) / 2) , setting.getPaddingTop()));
        for (int i = 0; i < keys.length; i++) {
            blocksAtRow.get(id).addCharacterInfo(keys[i], i);
        }
    }

    public BlockManager get(Integer id) {
        return blocksAtRow.get(id);
    }

    public void createFirstBlock(Integer id, Point2D parentPosition, Character insertedChar) {
        addBlock(id, new Point2D(parentPosition.getX(), parentPosition.getY() + setting.getNodeHeight() + setting.getVerticalSpace()));
        blocksAtRow.get(id).addCharacterInfo(insertedChar, 0);
    }

    public Point2D getPositionKey(Integer id, Character key) {
        BlockManager blockManager = get(id);
        BlockKeyInfo info = blockManager.get(key);
        return new Point2D(blockManager.blockPosition.getX() + info.positionAtBlock * setting.getMinNodeWidth(), blockManager.blockPosition.getY());
    }

    public void insertToRow(Integer id, BlockManager parentBlock, Character currentCharacter, Character parentCharacter){
        //najdu prvniho leveho bratra
        BlockManager leftBlock = get(parentBlock.getLeftChild(parentCharacter));
        //najdu prvniho praveho bratra
        BlockManager rightBlock = get(parentBlock.getRightChild(parentCharacter));

        PositionNewNodeHelper helper= new PositionNewNodeHelper(
                leftBlock,rightBlock,
                setting,
                parentBlock.blockPosition.getX()+parentBlock.get(parentCharacter).positionAtBlock*setting.getMinNodeWidth(), // pozice vůči otci
                parentBlock.blockPosition.getY()+setting.getVerticalSpace()+setting.getNodeHeight(), // y pozice
                setting.getMinNodeWidth() //width
                );
        helper.build();

        addBlock(id, helper.getPoint());
        System.out.println(helper.getPoint());
        // propojeni na radku...
        if(helper.existLeft()){
            leftBlock.rightBLock = blocksAtRow.get(id);
            blocksAtRow.get(id).leftBlock = leftBlock;
        }
        if(helper.existRight()){
            rightBlock.leftBlock= blocksAtRow.get(id);
            blocksAtRow.get(id).rightBLock= rightBlock;
        }

        blocksAtRow.get(id).addCharacterInfo(currentCharacter, 0);
    }

    private void addBlock(Integer id,Point2D position) {
        BlockManager rootBlock = new BlockManager(id, position, setting.eventBus);
        blocksAtRow.put(id, rootBlock);
    }

}
