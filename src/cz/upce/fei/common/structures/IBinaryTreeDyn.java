package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.IStructureElement;

/**
 * @author Vojtěch Müller
 */
public interface IBinaryTreeDyn<T extends IStructureElement> extends IBinaryTree<T,BinaryNode<T>> {

    BinaryNode<T> getRootNode();

    BinaryNode<T> getLeftNode(BinaryNode<T> parent);

    BinaryNode<T> getRightNode(BinaryNode<T>  parent);

}
