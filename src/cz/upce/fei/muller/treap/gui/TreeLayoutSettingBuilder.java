package cz.upce.fei.muller.treap.gui;

import cz.commons.graphics.IGraphics;
import cz.commons.layoutManager.TreeLayoutSettings;
import cz.upce.fei.muller.treap.graphics.ITreapBinaryNodesElements;

/**
 * @author Vojtěch Müller
 */
public final class TreeLayoutSettingBuilder {

    static final Integer PADDING_TOP = (int) (50* IGraphics.PLATFORM_SCALE);

    static final Integer HORIZONTAL_SPACE =(int) (40* IGraphics.PLATFORM_SCALE);

    static final Integer VERTICAL_SPACE = (int) (6* IGraphics.PLATFORM_SCALE);

    public static TreeLayoutSettings getSetting(){
        return new TreeLayoutSettings(PADDING_TOP,HORIZONTAL_SPACE,VERTICAL_SPACE, ITreapBinaryNodesElements.WIDTH, ITreapBinaryNodesElements.HEIGHT);
    }

}
