package cz.upce.fei.muller.TwoDTree.gui;

import cz.commons.graphics.IGraphics;
import cz.commons.layoutManager.TreeLayoutSettings;
import cz.upce.fei.muller.TwoDTree.graphics.ITwoDNodesElements;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreeLayoutSetting implements ITwoDNodesElements{

    static final Integer PADDING_TOP = (int) (50* IGraphics.PLATFORM_SCALE);

    static final Integer HORIZONTAL_SPACE =(int) (40* IGraphics.PLATFORM_SCALE);

    static final Integer VERTICAL_SPACE = (int) (10* IGraphics.PLATFORM_SCALE);

    public static TreeLayoutSettings getSetting() {
        return new TreeLayoutSettings(PADDING_TOP,HORIZONTAL_SPACE,VERTICAL_SPACE, WIDTH,HEIGHT);
    }
}
