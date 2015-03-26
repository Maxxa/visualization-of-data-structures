package cz.upce.fei.muller.TwoDTree.gui;

import cz.upce.fei.common.gui.utils.SceneInfo;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreeSceneInfo implements SceneInfo {

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
        return "2D Strom";
    }

    @Override
    public Integer getMinSceneWith() {
        return 200;
    }
}
