package cz.upce.fei.muller.trie.manager;

import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.structure.TrieNode;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Vojtěch Müller
 */
public class LayoutManager {

    private final Pane canvas;
    private final LayoutManagerSetting setting;

    private final Map<Integer, ElementInfo> elementsInfo = new HashMap<>();

    private final List<BlockRowManager> rowsManagers = new LinkedList<>();

    private final static Integer CUSTOM_ROOT = 0;
    private Integer idRoot;

    public LayoutManager(Pane canvas, LayoutManagerSetting setting, IRootBuilder builder) {
        this.canvas = canvas;
        this.setting = setting;
        initFirstRow(builder);
    }

    public void add(Character character, TrieNode node) {
        if (rowsManagers.size() == 1) {
            idRoot = node.getId();// its disable adding to root element.
            return;
        }
        if (node.getParent() == null) return;
        Integer idParent = node.getParent().getId();
        if (idRoot == idParent) { // where is parent root, have FIXED ID for manager...
            idParent = CUSTOM_ROOT;
        }
        ElementInfo infoCurrent = elementsInfo.get(idParent);
        if(infoCurrent.indexRow+1>=rowsManagers.size()){
            BlockRowManager rowManager = new BlockRowManager(setting);
            rowsManagers.add(rowManager);
            //add new row
        }else{
            // add to exist row
        }

//        BlockRowManager row = rowsManagers.get(infoCurrent.indexRow);
//        BlockManager blockManager = row.get(idParent);
//        System.out.println("");

    }

    private void initFirstRow(IRootBuilder builder) {
        //create row
        BlockRowManager rootRow = new BlockRowManager(setting);
        rootRow.addRoot(CUSTOM_ROOT, canvas.getWidth());
        this.rowsManagers.add(rootRow);
        //create block and init
        TrieKeysBlock rootBlock = builder.getRootBlock();
        Point2D p = rootRow.get(CUSTOM_ROOT).blockPosition;
        rootBlock.setTranslateX(p.getX());
        rootBlock.setTranslateY(p.getY());
        // save block info
        canvas.getChildren().addAll(rootBlock);
        ElementInfo info = new ElementInfo();
        info.graphicsBlock = rootBlock;
        info.indexRow = 0;
        elementsInfo.put(CUSTOM_ROOT, info);
    }

    public void clear() {
        //temporary root
        BlockRowManager rootRow = rowsManagers.get(0);
        ElementInfo info = elementsInfo.get(CUSTOM_ROOT);
        //clearing
        rowsManagers.clear();
        elementsInfo.clear();
        canvas.getChildren().clear();
        //add root
        rowsManagers.add(rootRow);
        elementsInfo.put(CUSTOM_ROOT, info);
        Point2D p = rootRow.get(CUSTOM_ROOT).blockPosition;
        info.graphicsBlock.setTranslateX(p.getX());
        info.graphicsBlock.setTranslateY(p.getY());
        canvas.getChildren().addAll(info.graphicsBlock);
    }

    public Pane getCanvas() {
        return canvas;
    }

    public LayoutManagerSetting getSetting() {
        return setting;
    }

    public ElementInfo get(Integer id) {
        if (id == idRoot || elementsInfo.size()==1) {
            return elementsInfo.get(CUSTOM_ROOT);
        }
        return elementsInfo.get(id);
    }

}
