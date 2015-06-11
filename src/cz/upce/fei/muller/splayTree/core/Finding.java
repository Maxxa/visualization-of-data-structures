package cz.upce.fei.muller.splayTree.core;

import cz.upce.fei.common.core.AbstractStructureElement;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;

/**
 * @author Vojtěch Müller
 */
public class Finding extends AbstractEventPreparation {


    private final SplayNodeImpl findingNode;

    public Finding(Data data,SplayNodeImpl findingNode) {
        super(data);

        this.findingNode = findingNode;
    }

    public void findEnd(AbstractStructureElement findNode, boolean isFind) {

    }

    @Override
    protected void clear() {

    }
}
