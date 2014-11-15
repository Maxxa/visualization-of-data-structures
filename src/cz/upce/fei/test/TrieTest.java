package cz.upce.fei.test;

import cz.upce.fei.muller.trie.structure.Description;
import cz.upce.fei.muller.trie.structure.Trie;
import org.junit.Assert;
import org.junit.*;

/**
 * @author Vojtěch Müller
 */
public class TrieTest {

    class StringDescription extends Description{

        private String value;

        StringDescription(String value) {
            this.value = value;
        }

        @Override
        public String getDescription() {
            return value;
        }

        @Override
        public void setDescription(String description) {
            value = description;
        }
    }


    Trie<StringDescription> trie = new Trie<>();
    Trie<StringDescription> trie2 = new Trie<>();

    StringDescription[] array = {
            new StringDescription("Nevedomi"),
            new StringDescription("Rohlik"),
            new StringDescription("Chleba"),
            new StringDescription("Praha"),
            new StringDescription("Roxet"),
            new StringDescription("Box"),
            new StringDescription("Nevim"),
    };


    @Before
    public void insert() {


        for (int i = 0; i < array.length; i++) {
            trie.add(array[i]);
            trie2.add(array[i]);
        }
    }

    @Test
    public void testInsert(){
        Assert.assertEquals("ok",0,0);
    }


    @Test
    public void testRemove(){

        StringDescription sd1 = trie.remove(array[3].value);
        StringDescription sd2 = trie2.remove1(array[3].value);

        Assert.assertEquals("Fail",array[3],sd1);

        Assert.assertEquals("Fail",array[3],sd2);

    }

}
