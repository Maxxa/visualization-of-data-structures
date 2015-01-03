package cz.upce.fei.muller.binaryHeap.gui;

import cz.commons.layoutManager.TreeLayoutSettings;
import cz.upce.fei.muller.binaryHeap.graphics.IBinaryNodesElements;

/**
 * @author Vojtěch Müller
 */
final class TreeLayoutSettingBuilder {

    static final Integer PADING_TOP = 10;

    static final Integer HORIZONTAL_SPACE = 10;

    static final Integer VERTICAL_SPACE = 10;

    static TreeLayoutSettings getSetting(){
        return new TreeLayoutSettings(PADING_TOP,HORIZONTAL_SPACE,VERTICAL_SPACE, IBinaryNodesElements.WIDTH,IBinaryNodesElements.HEIGHT);
    }

}
