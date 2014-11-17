package cz.muller;

import cz.commons.utils.GeneratorElementsNumbers;
import cz.upce.fei.common.core.IStructureElement;
import cz.upce.fei.common.structures.BinaryNode;
import cz.upce.fei.common.structures.BinaryTreeDyn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Vojtěch Müller
 */
public class TestBinaryTree {

    BinaryTreeDyn<Ele> tree;

    @Before
    public void initTree(){
        tree = new BinaryTreeDyn<>();
        Ele[] elements = new Ele[]{
                new Ele(0),
                new Ele(1),
                new Ele(2),
                new Ele(3),
                new Ele(4),
                new Ele(5),
                new Ele(6)
        };
        tree.insertRoot(elements[0]);

        BinaryNode<Ele> root=tree.getRootNode();

        tree.insertLeftChild(root,elements[1]);
        tree.insertRightChild(root,elements[2]);

        BinaryNode<Ele> n11=tree.getLeftNode(root);
        BinaryNode<Ele> n12=tree.getRightNode(root);

        tree.insertLeftChild(n11,elements[3]);
        tree.insertRightChild(n11,elements[4]);
        tree.insertLeftChild(n12,elements[5]);
        tree.insertRightChild(n12,elements[6]);
    }

    @Test
    public void isEmpty(){
        Assert.assertEquals(false, tree.isEmpty());
    }

    @Test
    public void controlElements(){

        BinaryNode<Ele> root = tree.getRootNode();
        Assert.assertEquals(root.getValue().getVal(),0);

        BinaryNode<Ele> n11 = tree.getLeftNode(root);
        BinaryNode<Ele> n12 = tree.getRightNode(root);

        Assert.assertEquals(n11.getValue().getVal(),1);
        Assert.assertEquals(n12.getValue().getVal(),2);

        BinaryNode<Ele> n21 = tree.getLeftNode(n11);
        BinaryNode<Ele> n22 = tree.getRightNode(n11);
        BinaryNode<Ele> n23 = tree.getLeftNode(n12);
        BinaryNode<Ele> n24 = tree.getRightNode(n12);

        Assert.assertEquals(n21.getValue().getVal(),3);
        Assert.assertEquals(n22.getValue().getVal(),4);
        Assert.assertEquals(n23.getValue().getVal(),5);
        Assert.assertEquals(n24.getValue().getVal(),6);


        tree.swapNode(n11, n22);
        Assert.assertEquals(n11.getValue().getVal(),4);
        Assert.assertEquals(n22.getValue().getVal(),1);
    }

    @Test
    public void testRemove() throws Exception {
        BinaryNode<Ele> root = tree.getRootNode();
        tree.removeLeftLeaf(root);
        Assert.assertEquals(null,tree.getLeft(root));
    }
}


class Ele implements IStructureElement {

    public final Integer value;

    public final Integer id = GeneratorElementsNumbers.getNextId();

    public Ele(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getId() {
        return value;
    }

    public Integer getVal(){
        return value;
    }

    @Override
    public String toString() {
        return "Element:"+id;
    }

    @Override
    public int compareTo(Object o) {
        Ele obj = (Ele)o;
        return value.compareTo(obj.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ele ele = (Ele) o;

        if (id != null ? !id.equals(ele.id) : ele.id != null) return false;
        if (value != null ? !value.equals(ele.value) : ele.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}