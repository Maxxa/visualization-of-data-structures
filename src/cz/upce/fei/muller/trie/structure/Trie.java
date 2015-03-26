package cz.upce.fei.muller.trie.structure;

import com.google.common.eventbus.EventBus;
import cz.upce.fei.muller.trie.events.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
    public List<T> get(T key) {
        eventBus.post(new BuildWord(key,false));
        TrieNode temp = root;
        for (int i = 0; i < key.getDescription().length(); i++)
        {
            Character current = key.getDescription().charAt(i);
            eventBus.post(new GoToNode(current,temp));
            if(temp.next.containsKey(current)){
                temp = (TrieNode) temp.next.get(current);
            }else{
                eventBus.post(new EndAction(temp));
                return new ArrayList<>();
            }
        }

        Queue<TrieNode> queue = new ArrayDeque<>();
        queue.add(temp);
        List<T> returnValues = new ArrayList<>();
        while (queue.size() != 0) {
            temp = queue.poll();
            if (temp.object != null) {
                returnValues.add((T) temp.object);
            }
            queue.addAll(temp.next.values());
        }
        eventBus.post(new EndAction(temp));
        return returnValues;
    }


    @Override
    public T remove(T value) {
        eventBus.post(new BuildWord(value,false));
        TrieNode previous = root;
        for (int i = 0; i < value.getDescription().length(); i++) {
            Character character = value.getDescription().charAt(i);
            eventBus.post(new GoToNode(character,previous));
            if (previous.next.containsKey(character)) {
                previous = (TrieNode) previous.next.get(character);
            } else {
                eventBus.post(new WordNotFound(value.getDescription()));
                eventBus.post(new EndAction(previous));
                return (T) T.EMPTY;
            }
        }

        T returnedValue = (T) previous.object;

        if(!returnedValue.getDescription().equals(value.getDescription())){
            eventBus.post(new WordNotFound(value.getDescription()));
            eventBus.post(new EndAction(previous));
            return (T) T.EMPTY;
        }

        previous.object = null;
        int i = value.getDescription().length();
        while (previous != null) {
            i--;
            if (!previous.next.isEmpty()) {
                break;
            }
            if (previous.object != null) {
                break;
            }
            previous = previous.parent;
            Character character = value.getDescription().charAt(i);
            eventBus.post(new RemoveNodeKey(previous,character));
            previous.next.remove(character);
        }
        eventBus.post(new EndAction(previous));
        return returnedValue;
    }

    @Override
    public Integer getCount() {
        return count;
    }

}
