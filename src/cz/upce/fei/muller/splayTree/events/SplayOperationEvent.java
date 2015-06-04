package cz.upce.fei.muller.splayTree.events;

/**
 * @author Vojtěch Müller
 */
public class SplayOperationEvent {

    private final SplayOperation type;

    public SplayOperationEvent(SplayOperation type) {
        this.type = type;
    }

    public enum SplayOperation {
        ZIG_LEFT, ZIG_RIGHT,
        ZIG_ZIG_LEFT, ZIG_ZIG_RIGHT,
        ZIG_ZAG_LEFT, ZIG_ZAG_RIGTH
    }
}
