package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class MatchFindEvent extends AbstractEvent {
    private final Object key;

    public MatchFindEvent(Object key, AbstractStructureElement contents) {
        super(contents);
        this.key = key;
    }

    public SplayNodeImpl getFindingNode(){
        return (SplayNodeImpl)element;
    }

    public Object getKey() {
        return key;
    }
}
