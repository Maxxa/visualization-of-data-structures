package cz.upce.fei.muller.splayTree.gui;

import cz.commons.graphics.IGraphics;
import cz.commons.layoutManager.TreeLayoutSettings;
import cz.upce.fei.muller.splayTree.graphics.IBinaryNodesElements;

/**
 * @author Vojtěch Müller
 */
final class TreeLayoutSettingBuilder {

    static final Integer PADDING_TOP = (int) (25* IGraphics.PLATFORM_SCALE);

    static final Integer HORIZONTAL_SPACE =(int) (10* IGraphics.PLATFORM_SCALE);

    static final Integer VERTICAL_SPACE = (int) (10* IGraphics.PLATFORM_SCALE);

    static TreeLayoutSettings getSetting(){
        return new TreeLayoutSettings(PADDING_TOP,HORIZONTAL_SPACE,VERTICAL_SPACE, IBinaryNodesElements.WIDTH, IBinaryNodesElements.HEIGHT);
    }

}
