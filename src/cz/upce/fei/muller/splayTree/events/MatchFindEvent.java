package cz.upce.fei.muller.splayTree.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class MatchFindEvent extends AbstractEvent {
    public MatchFindEvent(AbstractStructureElement contents) {
        super(contents);
    }
}
