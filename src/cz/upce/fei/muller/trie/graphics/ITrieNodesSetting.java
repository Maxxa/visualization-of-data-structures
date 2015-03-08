package cz.upce.fei.muller.trie.graphics;

import cz.commons.graphics.IGraphics;
import javafx.scene.paint.Color;

/**
 * @author Vojtěch Müller
 */
public interface ITrieNodesSetting {

    public static final int KEY_WIDTH = (int) (20.0 * IGraphics.PLATFORM_SCALE);
    public static final int HEIGHT = (int) (20.0  * IGraphics.PLATFORM_SCALE);

    public static final int MAX_KEYS = 26;/* a-z */

    public static final Color BG_COLOR = Color.LIGHTYELLOW;

}
