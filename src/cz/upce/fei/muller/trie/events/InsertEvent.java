package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class InsertEvent extends AbstractEvent {

    private final Character current;

    public InsertEvent(Character current, AbstractStructureElement element) {
        super(element);
        this.current = current;
    }

    @Override
    public String toString() {
        return " ... ["+current+"] "+this.element.toString();
    }
}
