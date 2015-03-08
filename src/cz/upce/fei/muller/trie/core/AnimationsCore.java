package cz.upce.fei.muller.trie.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.muller.trie.animations.builders.BuilderGoToNode;
import cz.upce.fei.muller.trie.animations.builders.BuilderInsertKeyToNode;
import cz.upce.fei.muller.trie.animations.builders.BuilderInsertNode;
import cz.upce.fei.muller.trie.events.BuildWord;
import cz.upce.fei.muller.trie.events.EndAction;
import cz.upce.fei.muller.trie.events.GoToNode;
import cz.upce.fei.muller.trie.events.InsertEvent;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.ElementInfo;
import cz.upce.fei.muller.trie.manager.IBlocksPositions;
import cz.upce.fei.muller.trie.manager.LayoutManager;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.*;

/**
 * @author Vojtěch Müller
 */
public class AnimationsCore {

    private final AnimationControl animationControl;
    private final LayoutManager layoutManager;

    private Map<Character, TrieKey> currentWord = new HashMap<>();
    private Set<Shape> coloredShape = new HashSet<>();
    private IEndInitAnimation endAnimationHandler;

    public AnimationsCore(AnimationControl animationControl, LayoutManager layoutManager) {
        this.animationControl = animationControl;
        this.layoutManager = layoutManager;
    }

    @Subscribe
    public void handleBuildWord(BuildWord word) {
        String s = word.getWord().getDescription();
        Pane canvas = layoutManager.getCanvas();
        Double beginX = (canvas.getWidth() / 2) - (s.length() * 15 / 2);
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            TrieKey k = new TrieKey(character.toString(), (int) (beginX + i * 15));
            canvas.getChildren().add(k);
            currentWord.put(character, k);
        }
    }

    @Subscribe
    public void handleGoToNodeEvent(GoToNode event) {
        System.out.println("________HANDLE %% GO TO NODE .... " + event);
        TrieKeysBlock graphicsBlock = layoutManager.get(event.getNode().getId()).getGraphicsBlock();
        animationControl.getTransitions().add(new BuilderGoToNode(event.getCurrent(), currentWord.get(event.getCurrent()), graphicsBlock).getTransition());
        coloredShape.add(graphicsBlock.getKey(event.getCurrent()).getRect());
    }

    @Subscribe
    public void handleInsertEvent(InsertEvent event) {
        System.out.println("________HANDLE %% INSERT .... " + event);
        TrieKeysBlock graphicsBlock;
        if (event.getInsertedNode().getParent() == null) {
            //create animation to visibling..
            layoutManager.setIdRoot(event.getInsertedNode().getId());
            graphicsBlock = layoutManager.get(event.getInsertedNode().getId()).getGraphicsBlock();
            animationControl.getTransitions().add(new BuilderGoToNode(event.getCurrentCharacter(),
                    currentWord.get(event.getCurrentCharacter()), graphicsBlock
            ).getTransition());
            coloredShape.add(graphicsBlock.getKey(event.getCurrentCharacter()).getRect());
        } else {
            if (layoutManager.existNode(event.getInsertedNode().getId())) {
                graphicsBlock = buildNewKeyToNode(event);
            } else {
                graphicsBlock = buildNewNode(event);
            }
        }
        if (graphicsBlock != null)
            coloredShape.add(graphicsBlock.getKey(event.getCurrentCharacter()).getRect());

    }

    private TrieKeysBlock buildNewKeyToNode(InsertEvent event) {
        ElementInfo elementInfo = layoutManager.get(event.getInsertedNode().getId());
        TrieKeysBlock block = elementInfo.getGraphicsBlock();
        TrieKey trieKey = new TrieKey(event.getCurrentCharacter().toString(), 0);
        block.addKey(event.getCurrentCharacter(),trieKey);//TODO add parent key

        BuilderInsertKeyToNode builder = new BuilderInsertKeyToNode();
        animationControl.getTransitions().add(builder.getTransition());
        return block;
    }

    private TrieKeysBlock buildNewNode(InsertEvent event) {
        TrieKeysBlock block = new TrieKeysBlock(event.getInsertedNode().getId(), new Point2D(0, 0));
        TrieKey trieKey = new TrieKey(event.getCurrentCharacter().toString(), 0);
        block.addKey(event.getCurrentCharacter(), trieKey);
        IBlocksPositions pointPosition = layoutManager.add(event.getCurrentCharacter(), block,event.getInsertedNode(),event.getParentKey());
        BuilderInsertNode builder = new BuilderInsertNode(block, trieKey, currentWord.get(event.getCurrentCharacter()), pointPosition);
        animationControl.getTransitions().add(builder.getTransition());
        return block;
    }

    @Subscribe
    public void handleEndActionEvent(EndAction event) {
        System.out.println("________HANDLE %% END _" + event);
        endAnimationHandler.endAnimation(animationControl.isMarkedAsStepping());
        if (!animationControl.isMarkedAsStepping()) {
            animationControl.playForward();
        }
    }

    public void clear() {
        //TODO
    }

    public void clearBeforeNewAction() {
        layoutManager.getCanvas().getChildren().removeAll(currentWord.values());
        for (Iterator<Shape> it = coloredShape.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            s.setStroke(Color.TRANSPARENT);
        }
    }

    public void setEndAnimationHandler(IEndInitAnimation endAnimationHandler) {
        this.endAnimationHandler = endAnimationHandler;
    }
}
