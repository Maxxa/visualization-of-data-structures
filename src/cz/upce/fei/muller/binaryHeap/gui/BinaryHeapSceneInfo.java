package cz.upce.fei.muller.binaryHeap.gui;

import cz.upce.fei.common.gui.utils.SceneInfo;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapSceneInfo implements SceneInfo {
    @Override
    public double getWidth() {
        return 600;
    }

    @Override
    public double getHeight() {
        return 930;
    }

    @Override
    public String getTitle() {
        return "Binární halda";
    }

    @Override
    public Integer getMinSceneWith() {
        return 200;
    }
}
