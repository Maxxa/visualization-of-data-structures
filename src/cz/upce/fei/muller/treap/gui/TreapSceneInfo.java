package cz.upce.fei.muller.treap.gui;

import cz.commons.graphics.IGraphics;
import cz.upce.fei.common.gui.utils.SceneInfo;

/**
 * @author Vojtěch Müller
 */
public class TreapSceneInfo implements SceneInfo {
    @Override
    public double getWidth() {
        return 600* IGraphics.PLATFORM_SCALE;
    }

    @Override
    public double getHeight() {
        return 930 * IGraphics.PLATFORM_SCALE;
    }

    @Override
    public String getTitle() {
        return "Treap";
    }

    @Override
    public Integer getMinSceneWith() {
        return 200;
    }
}
