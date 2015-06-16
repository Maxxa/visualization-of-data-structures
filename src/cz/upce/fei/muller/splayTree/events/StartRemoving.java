package cz.upce.fei.muller.splayTree.events;

/**
 * @author Vojtěch Müller
 */
public class StartRemoving {

    Object key;

    public StartRemoving(Object key) {
        this.key=key;
    }

    public Integer getKey() {
        return (Integer) key;
    }
}
