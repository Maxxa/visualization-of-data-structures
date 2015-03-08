package cz.upce.fei.muller.trie.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.muller.trie.events.BuildWord;
import cz.upce.fei.muller.trie.events.EndAction;
import cz.upce.fei.muller.trie.events.GoToNode;
import cz.upce.fei.muller.trie.events.InsertEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class Trie<T extends Description> implements ITrie<T> {

    private final EventBus eventBus;
    TrieNode root;
    private Integer count;

    public Trie(EventBus eventBus) {
        clear();
        this.eventBus = eventBus;
    }

    @Override
    public void clear() {
        root = new TrieNode();
        count = 0;
    }

    @Override
    public void add(T inserted) {
        eventBus.post(new BuildWord(inserted,true));
        TrieNode temp = root;
        Character parentKey = null;
        for (int i = 0; i < inserted.getDescription().length();i++){
            Character current = inserted.getDescription().charAt(i);
            if(!temp.next.containsKey(current)){
                eventBus.post(new InsertEvent(current,temp,parentKey));
                temp.next.put(current, new TrieNode(temp));
            }else{
                eventBus.post(new GoToNode(current,temp));
            }
            parentKey=current;
            temp = (TrieNode) temp.next.get(current);
        }

        if(temp.object.equals(T.EMPTY)){
            temp.object=inserted;
            count++;
        }

        eventBus.post(new EndAction(temp));
    }

    @Override
    public T remove(String value) {
        List<TrieNode> seznamPredchozich = new ArrayList<>();
//        TrieNode temp = root;
//        seznamPredchozich.add(temp);
//        for (int i = 0; i < value.length(); i++)
//        {
//            Character current = value.charAt(i);
//            if (temp.next.containsKey(current))
//            {
//                temp= temp.next.get(current);
//                if ((!temp.next.isEmpty() ||temp.object != null)
//                        && i!=value.length()-1)
//                {
//                    seznamPredchozich.clear();
//                    seznamPredchozich.add(temp);
//                }
//                else
//                {
//                    seznamPredchozich.add(temp);
//                }
//            }
//            else
//            {
//                return (T) T.EMPTY;
//            }
//        }
//        if (temp.object.equals(T.EMPTY)) return (T) T.EMPTY;
        T naVraceni = null;// temp.object;
//        temp.object = (T) T.EMPTY;
//        count--;
//        if (!temp.next.isEmpty()) return naVraceni;
//
//        if (!seznamPredchozich.isEmpty())
//        {
//            int pozice = naVraceni.getDescription().length();
//
//            pozice -= seznamPredchozich.size()-1;
//            if (!seznamPredchozich.get(0).next.isEmpty())
//            {
//                seznamPredchozich.get(0).next.remove(naVraceni.getDescription().charAt(pozice));
//            }
//            else
//            {
//                seznamPredchozich.get(0).next.clear();
//            }
//        }
        return naVraceni;
    }

    public T remove1(String value) {
        T zpet;
//        TrieNode previous = root;
//        for (int i = 0; i < value.length(); i++) {
//            if (previous.next.containsKey(value.charAt(i))) {
//                previous = previous.next.get(value.charAt(i));
//            } else {
//                return null;
//            }
//        }
//        zpet = previous.object;
//        previous.object = null;
//        int i = value.length();
//        while (previous != null) {
//            i--;
//            if (!previous.next.isEmpty()) {
//                break;
//            }
//            if (previous.object != null) {
//                break;
//            }
//            previous = previous.parent;
//            previous.next.remove(value.charAt(i));
//        }
        return null;
    }


    @Override
    public List<T> get(String key) {
        return null;
    }

    @Override
    public Integer getCount() {
        return count;
    }

}
