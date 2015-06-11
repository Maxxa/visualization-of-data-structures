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
        ZIG_LEFT("cik left"), ZIG_RIGHT("cig right"),
        ZIG_ZIG_LEFT("cik cik left"), ZIG_ZIG_RIGHT("cik cik right"),
        ZIG_ZAG_LEFT("cik cak left"), ZIG_ZAG_RIGHT("cik cak right");

        private String description;

        SplayOperation(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public SplayOperation getType() {
        return type;
    }
}
