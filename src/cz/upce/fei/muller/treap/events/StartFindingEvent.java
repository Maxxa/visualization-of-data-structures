package cz.upce.fei.muller.treap.events;


/**
 * @author Vojtěch Müller
 */
public class StartFindingEvent{

    private final Integer key;

    public StartFindingEvent(Object key){
        this.key = (Integer) key;
    }

    public Integer getKey() {
        return key;
    }
}
