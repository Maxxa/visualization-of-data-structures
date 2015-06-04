package cz.upce.fei.muller.splayTree.events;

/**
 * @author Vojtěch Müller
 */
public class StartFindEvent {
    private final Object key;

    public StartFindEvent(Object key) {
        this.key = key;
    }

    public Object getKey() {
        return key;
    }
}
