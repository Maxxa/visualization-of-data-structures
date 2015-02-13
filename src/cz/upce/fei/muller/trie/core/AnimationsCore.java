package cz.upce.fei.muller.trie.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.upce.fei.muller.trie.events.EndAction;
import cz.upce.fei.muller.trie.events.GoToNode;
import cz.upce.fei.muller.trie.events.InsertEvent;
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

    @Subscribe
      public void handleGoToNodeEvent(GoToNode event) {
        System.out.println("________HANDLE %% GO TO NODE" +event);

    }

    @Subscribe
    public void handleEndActionEvent(EndAction event) {
        System.out.println("________HANDLE %% END _" +event);

    }

    @Subscribe
    public void handleInsertNodeEvent(InsertEvent event) {
        System.out.println("________HANDLE %% INSERT _" +event);

    }

    public void clear(){
        //TODO
    }
}
