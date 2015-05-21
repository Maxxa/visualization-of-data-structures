package cz.upce.fei.muller.trie.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.animation.StepEventHandler;
import cz.commons.graphics.LineElement;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.muller.trie.animations.builders.*;
import cz.upce.fei.muller.trie.events.*;
import cz.upce.fei.muller.trie.graphics.TrieKey;
import cz.upce.fei.muller.trie.graphics.TrieKeysBlock;
import cz.upce.fei.muller.trie.manager.*;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.*;

/**
 * @author Vojtěch Müller
 */
public class AnimationsCore {

    private final AnimationControl animationControl;
    private final LayoutManager layoutManager;

    private List<List<TrieKey>> wordsToRemove = new ArrayList<>();
    private List<TrieKey> currentWord = new ArrayList<>();
    private List<TrieKey> coloredBlockKeys = new ArrayList<>();
    private Set<Shape> coloredShape = new HashSet<>();
    private IEndInitAnimation endAnimationHandler;
    private int counter = 0;
    private Transition lastTransition;
    private List<Transition> moveTransitions = new ArrayList<>();
    private List<Transition> moveKeysTransitions = new ArrayList<>();
    private RemoveHelper removedHelper;

    public AnimationsCore(AnimationControl animationControl, LayoutManager layoutManager) {
        this.animationControl = animationControl;
        this.layoutManager = layoutManager;
        layoutManager.getSetting().addHandler(this);
    }

    @Subscribe
    public void handleBuildWord(BuildWord word) {
        String s = word.getWord().getDescription();
        Pane canvas = layoutManager.getCanvas();
        Double beginX = (canvas.getWidth() / 2) - (s.length() * 15 / 2);
        ParallelTransition pt = new ParallelTransition();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            TrieKey k = new TrieKey(character.toString(), (int) (beginX + i * 15));
            k.setVisible(false);
            k.setOpacity(0);
            canvas.getChildren().add(k);
            currentWord.add(k);
            pt.getChildren().add(FadesTransitionBuilder.getTransition(k, Duration.ONE, 0, 1));
        }
        if (!pt.getChildren().isEmpty()) {
            animationControl.getTransitions().add(pt);
        }
    }

    @Subscribe
    public void handleGoToNodeEvent(GoToNode event) {
        controlLastTransition();
        TrieKeysBlock graphicsBlock = layoutManager.get(event.getNode().getId()).getGraphicsBlock();
        TrieKey key = graphicsBlock.getKey(event.getCurrent());
        if (key != null) {
            coloredBlockKeys.add(key);
            coloredShape.add(graphicsBlock.getKey(event.getCurrent()).getRect());
        }
        animationControl.getTransitions().add(
                new BuilderGoToNode(event.getCurrent(), currentWord.get(counter), graphicsBlock).getTransition());
        counter++;
    }

    @Subscribe
    public void handleInsertEvent(InsertEvent event) {
        controlLastTransition();
        TrieKeysBlock graphicsBlock;
        if (event.getInsertedNode().getParent() == null) {
            //create animation to visibling..
            layoutManager.setIdRoot(event.getInsertedNode().getId());
            graphicsBlock = layoutManager.get(event.getInsertedNode().getId()).getGraphicsBlock();
            lastTransition = new BuilderGoToNode(event.getCurrentCharacter(),
                    currentWord.get(counter), graphicsBlock
            ).getTransition();
            coloredShape.add(graphicsBlock.getKey(event.getCurrentCharacter()).getRect());
        } else {
            if (layoutManager.existNode(event.getInsertedNode().getId())) {
                graphicsBlock = buildNewKeyToNode(event);
            } else {
                graphicsBlock = buildNewNode(event);
            }
        }
        if (graphicsBlock != null) {
            coloredShape.add(graphicsBlock.getKey(event.getCurrentCharacter()).getRect());
            coloredBlockKeys.add(graphicsBlock.getKey(event.getCurrentCharacter()));
        }
        counter++;
    }

    @Subscribe
    public void handleRemoveTerminalSymbol(ClearTerminalSymbol event) {
        TrieKeysBlock block = layoutManager.get(event.getRemovedTerminalSymbol().getId()).getGraphicsBlock();
        final TrieKey key = block.getKey(event.getCharacter());
        ParallelTransition pt = new ParallelTransition();
        pt.setOnFinished(new StepEventHandler() {
            @Override
            protected void handleForward(ActionEvent actionEvent) {
                key.getTextLabel().setStyle("-fx-font-weight: normal;");
                key.getTextLabel().setTextFill(Color.BLACK);
            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                key.getTextLabel().setStyle("-fx-font-weight: bold;");
                key.getTextLabel().setTextFill(Color.BLUE);
            }
        });
        editLasTransition(pt);
    }

    private void editLasTransition(Transition transition) {
        if (lastTransition != null) {
            ParallelTransition pt = new ParallelTransition();
            pt.getChildren().addAll(transition, lastTransition);
        } else {
            lastTransition = transition;
        }
    }

    @Subscribe
    public void handleWordNotFound(WordNotFound event) {
        /** My be ??*/
    }

    @Subscribe
    public void handleInsertWordToLastNode(FinallyAddWord event) {
        final TrieKey key = layoutManager.get(event.getWordNode().getId()).getGraphicsBlock().getKey(event.getCharacter());
        if (lastTransition == null) {
            lastTransition = new ParallelTransition();
        }
        lastTransition.setOnFinished(new StepEventHandler() {
            @Override
            protected void handleForward(ActionEvent actionEvent) {
                key.getTextLabel().setStyle("-fx-font-weight: bold;");
                key.getTextLabel().setTextFill(Color.BLUE);

            }

            @Override
            protected void handleBack(ActionEvent actionEvent) {
                key.getTextLabel().setStyle("-fx-font-weight: normal;");
                key.getTextLabel().setTextFill(Color.BLACK);
            }
        });
    }

    @Subscribe
    public void handleRemoveNodeKey(RemoveNodeKey event) {
        TrieKeysBlock block = layoutManager.get(event.getRemoved().getId()).getGraphicsBlock();
        controlLastTransition();
        if (event.getRemoved().getParent() == null) {
            return;
        }
        IBlocksPositions positions = layoutManager.remove(event.getRemoved(), event.getCharacter(), event.getParentKey());
        if (block.getSizeChild() == 1) {
            TrieKeysBlock parentBlock = layoutManager.get(event.getRemoved().getParent().getId()).getGraphicsBlock();
            TrieKey key = parentBlock.getKey(event.getParentKey());
            editLasTransition(new BuilderRemoveNode(positions, block, event.getCharacter(), key.getLine())
                    .getTransition());
            removedHelper = new RemoveHelper(layoutManager.getCanvas(), block);
        } else {
            editLasTransition(new BuilderRemoveNodeKey(positions, block, event.getCharacter(), moveKeysTransitions).getTransition());
            removedHelper = new RemoveHelper(layoutManager.getCanvas(), block, event.getCharacter());
            moveKeysTransitions.clear();
        }
    }

    private TrieKeysBlock buildNewKeyToNode(InsertEvent event) {
        ElementInfo elementInfo = layoutManager.get(event.getInsertedNode().getId());
        TrieKeysBlock block = elementInfo.getGraphicsBlock();
        TrieKey trieKey = new TrieKey(event.getCurrentCharacter().toString(), 0);
        trieKey.setVisible(false);
        trieKey.setOpacity(0);
        block.addKey(event.getCurrentCharacter(), trieKey, true);
        IBlocksPositions blocksPositions = layoutManager.add(event.getCurrentCharacter(), trieKey, event.getInsertedNode());
        BuilderInsertKeyToNode builder = new BuilderInsertKeyToNode(moveKeysTransitions, block, trieKey, blocksPositions, currentWord.get(counter));
        lastTransition = builder.getTransition();
        moveKeysTransitions.clear();

        return block;
    }

    private TrieKeysBlock buildNewNode(InsertEvent event) {
        TrieKeysBlock block = new TrieKeysBlock(event.getInsertedNode().getId(), new Point2D(0, 0));
        TrieKey trieKey = new TrieKey(event.getCurrentCharacter().toString(), 0);
        block.addKey(event.getCurrentCharacter(), trieKey);

        TrieKeysBlock parentBlock = layoutManager.get(event.getInsertedNode().getParent().getId()).getGraphicsBlock();
        TrieKey parentKey = parentBlock.getKey(event.getParentKey());

        LineElement lineElement = new LineElement(parentKey, block);
        lineElement.setOpacity(0);
        lineElement.setVisible(false);
        parentKey.setLine(lineElement);
        layoutManager.getCanvas().getChildren().addAll(lineElement);

        IBlocksPositions pointPosition = layoutManager.add(event.getCurrentCharacter(), block, event.getInsertedNode(), event.getParentKey());

        BuilderInsertNode builder = new BuilderInsertNode(
                block, trieKey, currentWord.get(counter), pointPosition, event.getCurrentCharacter(), lineElement
        );
        lastTransition = builder.getTransition();
        return block;
    }

    @Subscribe
    public void handleEndActionEvent(EndAction event) {
        controlLastTransition();
        if (coloredBlockKeys.size() > 0) {
            BuilderLastAnimationEvent builder = new BuilderLastAnimationEvent(currentWord, coloredBlockKeys);
            animationControl.getTransitions().add(builder.getTransition());
        }
        endAnimationHandler.endAnimation(animationControl.isMarkedAsStepping());
        if (!animationControl.isMarkedAsStepping()) {
            animationControl.playForward();
        }
        coloredBlockKeys.clear();
        wordsToRemove.add(currentWord);
        currentWord.clear();
        counter = 0;
    }

    @Subscribe
    public void handleMoveNodeActionEvent(MoveBlockEvent event) {
        BuilderAnimMoveNode builder = new BuilderAnimMoveNode(event.getOldPoint(), event.getNewPoint(), layoutManager.get(event.getId()).getGraphicsBlock());
        moveTransitions.add(builder.getAnimation());
    }

    @Subscribe
    public void handleMoveKey(MoveKeyEvent event) {
        TrieKeysBlock graphicsBlock = layoutManager.get(event.getBlockId()).getGraphicsBlock();
        moveKeysTransitions.add(new BuilderAnimMoveNode(
                event.getOldPoint(), event.getNewPoint(), graphicsBlock.getKey(event.getCharacterAtBlock())
        ).getTranslateTransition());
    }

    public void clear() {
        layoutManager.clear();
        lastTransition = null;
        animationControl.clear();
        clearBeforeNewAction();
    }

    private void controlLastTransition() {
        if (!moveTransitions.isEmpty()) {
            ParallelTransition pt = new ParallelTransition();
            pt.getChildren().addAll(moveTransitions);
            animationControl.getTransitions().add(pt);
            moveTransitions.clear();
        }
        if (lastTransition != null) {
            animationControl.getTransitions().add(lastTransition);
            lastTransition = null;
        }
    }

    public void clearBeforeNewAction() {
        for (List<TrieKey> word : wordsToRemove) {
            layoutManager.getCanvas().getChildren().removeAll(word);
            word.clear();
        }
        moveTransitions.clear();
        wordsToRemove.clear();
        currentWord.clear();
        counter = 0;
        moveKeysTransitions.clear();
        coloredBlockKeys.clear();
        controlRemoving();
        for (Iterator<Shape> it = coloredShape.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            s.setStroke(Color.TRANSPARENT);
        }
    }

    private void controlRemoving() {
        if (removedHelper != null) {
            removedHelper.remove();
            removedHelper = null;
        }
    }

    public void setEndAnimationHandler(IEndInitAnimation endAnimationHandler) {
        this.endAnimationHandler = endAnimationHandler;
    }

}
