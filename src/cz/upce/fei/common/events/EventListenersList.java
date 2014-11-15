package cz.upce.fei.common.events;

import java.util.LinkedList;

/**
 * @author Vojtěch Müller
 */
public class EventListenersList<E> {

    LinkedList<E> list = new LinkedList<>();

    public void addListener(E listener){
        list.addLast(listener);
    }

    public void removeListener(E listener){
        list.remove(listener);
    }

    public Iterable<E> getListeners(){
        return list;
    }

}
