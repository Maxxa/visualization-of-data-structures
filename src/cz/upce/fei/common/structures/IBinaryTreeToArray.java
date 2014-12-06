package cz.upce.fei.common.structures;

/**
 *
 * @author Vojtěch Müller
 */
public interface IBinaryTreeToArray<T> extends IBinaryTree<T,Integer> {

    T getBrother(Integer item);

    T removeLast();

    T getLast();
}
