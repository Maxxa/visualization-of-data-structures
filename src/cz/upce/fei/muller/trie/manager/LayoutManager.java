package cz.upce.fei.muller.trie.manager;

import cz.upce.fei.muller.trie.graphics.TrieKey;
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

    public void add(Character character, TrieKey key, TrieNode node) {
        //TODO proste to udelema potom...
    }

    public IBlocksPositions add(final Character character, final TrieKeysBlock block, final TrieNode node, Character parentKey) {
        Integer idParent = node.getParent().getId();
        if (idRoot == idParent) { // where is parent root, have FIXED ID for manager...
            idParent = CUSTOM_ROOT;
        }
        final ElementInfo infoParent = elementsInfo.get(idParent);
        final BlockRowManager rowManager;
        if (infoParent.indexRow + 1 >= rowsManagers.size()) { //add new row
            rowManager = new BlockRowManager(setting);
            rowsManagers.add(rowManager);
            rowManager.createFirstBlock(node.getId(), rowsManagers.get(infoParent.indexRow).getPositionKey(idParent,parentKey), character);
        } else { // add to exist row
            rowManager = rowsManagers.get(infoParent.indexRow + 1);
            rowManager.insertToRow(node.getId(),rowsManagers.get(infoParent.indexRow).get(idParent),character,parentKey);
        }

        addBlockInfo(node.getId(), infoParent.indexRow + 1, block);
        canvas.getChildren().add(block);
        // set info info for parent
        BlockManager blockManager = rowsManagers.get(infoParent.indexRow).get(idParent);
        blockManager.setIdChildBlock(parentKey, node.getId());
        return new IBlocksPositions() {

            Point2D blockPosition = rowManager.get(node.getId()).blockPosition;
            Point2D keyPosition =   rowManager.getPositionKey(node.getId(), character);

            @Override
            public Point2D getPositionBlock() {
                return blockPosition;
            }

            @Override
            public Point2D getPositionBlockKey() {
                return keyPosition;
            }
        };
    }

    private void initFirstRow(IRootBuilder builder) {
        //create row
        BlockRowManager rootRow = new BlockRowManager(setting);
        rootRow.addRoot(CUSTOM_ROOT, canvas.getWidth(), builder.getKeys());
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
        rootRow.get(CUSTOM_ROOT).clear();
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

    public boolean existNode(Integer id) {
        return elementsInfo.containsKey(id);
    }

    public Pane getCanvas() {
        return canvas;
    }

    public LayoutManagerSetting getSetting() {
        return setting;
    }

    public ElementInfo get(Integer id) {
        if (id == idRoot || elementsInfo.size() == 1) {
            return elementsInfo.get(CUSTOM_ROOT);
        }
        return elementsInfo.get(id);
    }

    public void setIdRoot(Integer idRoot) {
        this.idRoot = idRoot;
    }

    private void addBlockInfo(Integer id, Integer row, TrieKeysBlock block) {
        ElementInfo info = new ElementInfo();
        info.indexRow = row;
        info.graphicsBlock = block;
        elementsInfo.put(id, info);
    }

    public TrieKey getKey(TrieNode parent, Character key) {
        return get(parent.getId()).getGraphicsBlock().getKey(key);
    }
}
