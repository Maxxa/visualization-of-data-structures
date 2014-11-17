package cz.upce.fei.common.structures;

import cz.upce.fei.common.core.IStructureElement;

/**
 *
 * @author Vojtěch Müller
 */
public interface IBinaryTreeToArray<T extends IStructureElement> extends IBinaryTree<T,Integer> {


    T getBrother(Integer item);

    T removeLast();

}
