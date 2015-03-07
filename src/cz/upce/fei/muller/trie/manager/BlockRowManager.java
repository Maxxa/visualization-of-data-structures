package cz.upce.fei.muller.trie.manager;

import javafx.beans.property.SimpleDoubleProperty;
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

    public void addRoot(Integer id, double widthCanvas) {
        BlockManager rootBlock = new BlockManager(id, new SimpleDoubleProperty(0),
                new Point2D(
                        widthCanvas / 2 - ((setting.getMinNodeWidth() * (TrieUtils.LOWER_CASE_END - TrieUtils.LOWER_CASE_BEGIN)) / 2) //ROOT X
                        , setting.getPaddingTop() //ROOT Y
                ), setting.eventBus
        );
        blocksAtRow.put(id,rootBlock);
    }

    public void addFirstBlock(){

    }

   // public void add

    public BlockManager get(Integer id){
        return blocksAtRow.get(id);
    }

}
