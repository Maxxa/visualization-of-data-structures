package cz.upce.fei.muller.trie.events;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.common.events.AbstractEvent;

/**
 * @author Vojtěch Müller
 */
public class EndAction extends AbstractEvent{

    public EndAction(AbstractStructureElement element) {
        super(element);
    }
}
