package cz.upce.fei.muller.TwoDTree.structure;

/**
* @author Vojtěch Müller
*/
class Node<T extends ICoordinate> {

    public Node left, right;
    T value;

    public Node(Node left, Node right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

}
