package cz.upce.fei.muller.trie.core;

import cz.commons.animation.AnimationControl;
import cz.upce.fei.muller.trie.manager.LayoutManager;

/**
 * @author Vojtěch Müller
 */
public class AnimationsCore {

    private final AnimationControl animationControl;
    private final LayoutManager layoutManager;

    public AnimationsCore(AnimationControl animationControl, LayoutManager layoutManager) {

        this.animationControl = animationControl;
        this.layoutManager = layoutManager;
    }

    public void clear(){
        //TODO
    }
}
