package cz.upce.fei.muller.treap.events;

/**
* @author Vojtěch Müller
*/
public class ReferenceHelper {

    private final Integer node;

    private Integer oldReference;
    private Integer newReference;

    boolean isLeftNodePosition;

    public ReferenceHelper(Integer node) {
        this.node = node;
    }

    public void setLeftNodePosition(boolean isLeftNodePosition) {
        this.isLeftNodePosition = isLeftNodePosition;
    }

    public Integer getNode() {
        return node;
    }

    public Integer getOldReference() {
        return oldReference;
    }

    public void setOldReference(Integer oldReference) {
        this.oldReference = oldReference;
    }

    public Integer getNewReference() {
        return newReference;
    }

    public void setNewReference(Integer newReference) {
        this.newReference = newReference;
    }

    public boolean isLeftNodePosition() {
        return isLeftNodePosition;
    }

}
