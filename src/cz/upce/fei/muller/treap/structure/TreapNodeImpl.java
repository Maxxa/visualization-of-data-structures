package cz.upce.fei.muller.treap.structure;

import cz.upce.fei.common.core.AbstractStructureElement;

import java.util.Random;

/**
 * @author Vojtěch Müller
 */
public class TreapNodeImpl extends AbstractStructureElement implements IPriorityKeyContainer<Integer>{

    private final static Random priorityGenerator = new Random();

    private final Integer key;
    private final Integer priority = priorityGenerator.nextInt();

    public TreapNodeImpl(Integer key) {
        this.key = key;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public Integer getKey() {
        return key;
    }


    @Override
    public String toString() {
        return String.format("[ %s , %s ]",key,priority);
    }
}
