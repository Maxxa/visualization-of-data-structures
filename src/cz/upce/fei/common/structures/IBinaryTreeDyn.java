package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.AbstractStructureElement;

/**
 * @author Vojtěch Müller
 */
public interface IBinaryTreeDyn<T extends AbstractStructureElement> extends IBinaryTree<T,BinaryNode<T>> {

    BinaryNode<T> getRootNode();

    BinaryNode<T> getLeftNode(BinaryNode<T> parent);

    BinaryNode<T> getRightNode(BinaryNode<T>  parent);

}
