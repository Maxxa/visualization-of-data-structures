package cz.upce.fei.muller.trie.gui;

import cz.commons.graphics.IGraphics;
import cz.upce.fei.muller.trie.manager.LayoutManagerSetting;

/**
 * @author Vojtěch Müller
 */
public class TrieLayoutSetting {

    static final Integer PADDING_TOP = (int) (25* IGraphics.PLATFORM_SCALE);

    static final Integer HORIZONTAL_SPACE =(int) (10* IGraphics.PLATFORM_SCALE);

    static final Integer VERTICAL_SPACE = (int) (10* IGraphics.PLATFORM_SCALE);

    public static LayoutManagerSetting getSetting() {
        return new LayoutManagerSetting(PADDING_TOP,HORIZONTAL_SPACE,VERTICAL_SPACE);
    }
}
