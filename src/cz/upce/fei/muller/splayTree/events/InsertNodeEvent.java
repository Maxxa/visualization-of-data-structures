package cz.upce.fei.muller.splayTree.events;

import cz.commons.layoutManager.helpers.ITreeStructure;
import cz.upce.fei.common.events.ReferenceHelper;

import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class InsertNodeEvent {

    private final ITreeStructure root;
    private final List<ReferenceHelper> referenceHelperList;

    public InsertNodeEvent(ITreeStructure root, List<ReferenceHelper> referenceHelperList) {
        this.root = root;
        this.referenceHelperList = referenceHelperList;
    }

    public ITreeStructure getRoot() {
        return root;
    }

    public List<ReferenceHelper> getReferenceHelperList() {
        return referenceHelperList;
    }
}
