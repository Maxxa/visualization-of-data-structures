package cz.upce.fei.muller.splayTree.structure;

import cz.commons.layoutManager.helpers.ITreeStructure;

/**
 * @author Vojtěch Müller
 */
public class UnificationSubTreeEvent {

    private ITreeStructure newTreeStructure;
    private Integer newRightConnect;
    private Integer newRoot;

    public void setNewTreeStructure(ITreeStructure newTreeStructure) {
        this.newTreeStructure = newTreeStructure;
    }

    public void setNewRightConnect(Integer newRightConnect) {
        this.newRightConnect = newRightConnect;
    }

    public Integer getNewRightConnect() {
        return newRightConnect;
    }

    public ITreeStructure getNewTreeStructure() {
        return newTreeStructure;
    }

    public void setNewRoot(Integer newRoot) {
        this.newRoot = newRoot;
    }

    public Integer getNewRoot() {
        return newRoot;
    }
}
