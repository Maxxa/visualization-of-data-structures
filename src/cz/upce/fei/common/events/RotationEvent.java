package cz.upce.fei.common.events;

import cz.commons.layoutManager.helpers.ITreeStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class RotationEvent {

    private final boolean isLeftRotation;

    private ITreeStructure treeRestructure;

    private List<ReferenceHelper> referenceHelperList = new ArrayList<>();

    public RotationEvent(boolean isLeftRotation) {
        this.isLeftRotation = isLeftRotation;
    }

    public void addReferenceHelper(ReferenceHelper helper){
        referenceHelperList.add(helper);
    }

    public void setTreeRestructure(ITreeStructure root){
        this.treeRestructure = root;
    }

    public boolean isLeftRotation() {
        return isLeftRotation;
    }

    public ITreeStructure getTreeRestructure() {
        return treeRestructure;
    }

    public List<ReferenceHelper> getReferenceHelperList() {
        return referenceHelperList;
    }
}
