package cz.upce.fei.muller.treap.structure;

import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.commons.layoutManager.helpers.TreeStructure;
import cz.upce.fei.common.core.AbstractStructureElement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vojtěch Müller
 */
public class TreeStructureBuilder<K extends Comparable<K>, T extends AbstractStructureElement & IPriorityKeyContainer<K>> {

    Map<Integer, ITreeStructure> nodes = new HashMap<>();

    private ITreeStructure root;

    public TreeStructureBuilder(TreapNode<K, T> root, boolean isLeft) {
        build(root, isLeft);
    }

    private void build(TreapNode<K, T> root, boolean isLeft) {
        WidthIterator iterator = new WidthIterator(root, isLeft);
        boolean first = true;
        for (; iterator.hasNext(); ) {
            ExtendData<K, T> data = iterator.next();
            ITreeStructure node = new TreeStructure(data.node.key.getId(), data.isLeft);
            if (first) {
                this.root = node;
                first = false;
            }
            initChilds(node, data.node);

            nodes.put(node.getId(), node);
        }
    }

    private void initChilds(ITreeStructure structureNode, TreapNode<K, T> treapNode) {
        if (!treapNode.isRoot()) {
            structureNode.setIdParent(treapNode.parent.key.getId());
            if (nodes.containsKey(structureNode.getIdParent())) {
                ITreeStructure parent = nodes.get(structureNode.getIdParent());
                if (structureNode.isLeftChild()) {
                    parent.setLeftChild(structureNode);
                    structureNode.setIsLeftChild(true);
                } else {
                    parent.setRightChild(structureNode);
                }
            }
        }
    }

    public ITreeStructure getRoot() {
        return root;
    }
}
