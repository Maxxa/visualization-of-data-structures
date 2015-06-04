package cz.upce.fei.muller.splayTree.structure;

import com.google.common.eventbus.EventBus;

/**
 * @author Vojtěch Müller
 */
public class TestMain {

    public static EventBus bus = new EventBus();

    public static void main(String[] args) {
        SplayTree<Integer, SplayNodeImpl> tree = new SplayTree<>(bus);





    }
}
