package cz.upce.fei.muller.splayTree.events;

/**
 * @author Vojtěch Müller
 */
public class RemoveRootEvent {

    private final Integer idElement;

    public RemoveRootEvent(Integer idElement) {
        this.idElement = idElement;
    }

    public Integer getIdElement() {
        return idElement;
    }

}
