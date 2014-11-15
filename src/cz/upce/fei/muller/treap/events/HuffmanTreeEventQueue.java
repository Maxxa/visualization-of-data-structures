package cz.upce.fei.muller.treap.events;

import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.events.EventQueue;

/**
 * @author Vojtěch Müller
 */
public class HuffmanTreeEventQueue extends EventQueue {
    protected HuffmanTreeEventQueue(Controller control) {
        super(control);
    }

    @Override
    public void handle(Object event) {

    }
}
