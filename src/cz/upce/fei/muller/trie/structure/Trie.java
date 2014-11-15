package cz.upce.fei.muller.trie.structure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class Trie<T extends Description> implements Iterable<T>,ITrie<T> {

    Node root;
    private Integer count;

    public Trie() {
        clear();
    }

    @Override
    public void clear() {
        root = new Node();
        count = 0;
    }

    @Override
    public void add(T inserted) {
        Node temp = root;
        for (int i = 0; i < inserted.getDescription().length();i++){
            Character current = inserted.getDescription().charAt(i);
            if(!temp.next.containsKey(current)){
                temp.next.put(current, new Node(temp));
            }
            temp = temp.next.get(current);
        }

        if(temp.object.equals(T.EMPTY)){
            temp.object=inserted;
            count++;
        }
    }

    @Override
    public T remove(String value) {
        List<Node> seznamPredchozich = new ArrayList<>();
        Node temp = root;
        seznamPredchozich.add(temp);
        for (int i = 0; i < value.length(); i++)
        {
            Character current = value.charAt(i);
            if (temp.next.containsKey(current))
            {
                temp= temp.next.get(current);
                if ((!temp.next.isEmpty() ||temp.object != null)
                        && i!=value.length()-1)
                {
                    seznamPredchozich.clear();
                    seznamPredchozich.add(temp);
                }
                else
                {
                    seznamPredchozich.add(temp);
                }
            }
            else
            {
                return (T) T.EMPTY;
            }
        }
        if (temp.object.equals(T.EMPTY)) return (T) T.EMPTY;
        T naVraceni = temp.object;
        temp.object = (T) T.EMPTY;
        count--;
        if (!temp.next.isEmpty()) return naVraceni;

        if (!seznamPredchozich.isEmpty())
        {
            int pozice = naVraceni.getDescription().length();

            pozice -= seznamPredchozich.size()-1;
            if (!seznamPredchozich.get(0).next.isEmpty())
            {
                seznamPredchozich.get(0).next.remove(naVraceni.getDescription().charAt(pozice));
            }
            else
            {
                seznamPredchozich.get(0).next.clear();
            }
        }
        return naVraceni;
    }

    public T remove1(String value) {
        T zpet;
        Node previous = root;
        for (int i = 0; i < value.length(); i++) {
            if (previous.next.containsKey(value.charAt(i))) {
                previous = previous.next.get(value.charAt(i));
            } else {
                return null;
            }
        }
        zpet = previous.object;
        previous.object = null;
        int i = value.length();
        while (previous != null) {
            i--;
            if (!previous.next.isEmpty()) {
                break;
            }
            if (previous.object != null) {
                break;
            }
            previous = previous.parent;
            previous.next.remove(value.charAt(i));
        }
        return zpet;
    }


    @Override
    public List<T> get(String key) {
        return null;
    }


    class Node {
        public Hashtable<Character,Node> next;
        public T object;
        public Node parent;

        Node() {
            next = new Hashtable<>();
            object= (T) T.EMPTY;
            parent=null;
        }

        public Node(Node parent){
            this();
            this.parent = parent;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
