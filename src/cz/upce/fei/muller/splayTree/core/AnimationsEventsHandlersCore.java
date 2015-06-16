package cz.upce.fei.muller.splayTree.core;

import com.google.common.eventbus.Subscribe;
import cz.commons.animation.AnimationControl;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.layoutManager.MoveElementEvent;
import cz.upce.fei.common.animations.RemovePreparation;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.events.RotationEvent;
import cz.upce.fei.muller.splayTree.events.*;
import cz.upce.fei.muller.splayTree.graphics.ISplayNodesElements;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import cz.upce.fei.muller.splayTree.structure.UnificationSubTreeEvent;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class AnimationsEventsHandlersCore {

    private Data data;
    IEndInitAnimation endInitAnimation;
    AbstractEventPreparation preparation = null;

    public AnimationsEventsHandlersCore(AnimationControl animationControl, ITreeLayoutManager manager) {
        this.data = new Data(animationControl, manager, initCreatingPoint(manager.getCanvas()),
                initFlashMessagePosition(manager.getCanvas()));
        manager.getDepthManager().addEventConsumer(this);
    }

    /*-------------- HANDLERS ------------------------*/
    @Subscribe
    public void handleEndEvent(LastActionEvent event) {
        endInitAnimation.endAnimation(data.animationControl.isMarkedAsStepping());
        if (!data.animationControl.isMarkedAsStepping()) {
            data.animationControl.playForward();
        }
    }

    @Subscribe
    public void handleStartInserting(StartInserting event) {
        preparation = new Inserting(data, event.getInsertingNode());
    }

    @Subscribe
    public void handleStartFinding(StartFindEvent event) {
        if (preparation == null) {
            preparation = new Finding(data, (Integer) event.getKey());
        }
    }

    @Subscribe
    public void handleStartRemoving(StartRemoving event) {
        preparation = new Removing(data, event.getKey());
    }

    @Subscribe
    public void handleStartRemoving(InsertNodeEvent event) {
        if (preparation != null) {
            ((Inserting) preparation).insert(event.getRoot(), event.getReferenceHelperList());
        }
    }

    @Subscribe
    public void handleElementFindEnd(ElementFindEndEvent event) {
        if (preparation != null) {
            ((Finding) preparation).findEnd(event.getFindNode(), event.isFind());
        }
    }

    @Subscribe
    public void handleElementKeyExist(ElementKeyExistEvent event) {
        if (preparation != null) {
            ((Inserting) preparation).elementKeyExist();
        }
    }

    @Subscribe
    public void handleRemoveRoot(RemoveRootEvent event) {
        if (preparation != null) {
            ((Removing) preparation).removeRoot(event.getIdElement());
        }
    }

    @Subscribe
    public void handleSplayOperation(SplayOperationEvent event) {
        if (preparation != null) {
            preparation.splayOperation(event.getType());
        }

    }

    @Subscribe
    public void handleRotationEvent(RotationEvent event) {
        if (preparation != null) {
            preparation.rotation(event);
        }
    }

    @Subscribe
    public void handleMoveToChildFind(MoveToChildEvent event) {
        if (preparation != null) {
            preparation.moveToChild(event.getFindingKey(), event.getComparingNode());
        }
    }

    @Subscribe
    public void handleMove(MoveElementEvent event) {
        if (preparation != null) {
            preparation.moveElementAtLayout(event.getElementId(), event.getOldPoint(), event.getNewPoint());
        }
    }

    @Subscribe
    public void handleMatchFindEvent(MatchFindEvent event) {
        if (preparation != null) {
            preparation.matchFind(event.getKey());
        }
    }

    @Subscribe
    public void handleFindingMax(FindingMaxEvent event) {
        if (preparation != null) {
            ((Removing) preparation).showFindingMax();
        }
    }

    @Subscribe
    public void handleFindingMax(UnificationSubTreeEvent event) {
        if (preparation != null) {
            ((Removing) preparation).unificationSubTree(event);
        }
    }


    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent event) {
        SplayGraphicsNodeElement newNode = new SplayGraphicsNodeElement(event.getNode(), 0, 0);
        newNode.setOpacity(0);
        data.manager.addElement(newNode, null, false);
        data.manager.getCanvas().getChildren().addAll(newNode.getChildLine(NodePosition.LEFT), newNode.getChildLine(NodePosition.RIGHT));
        data.insertTransition(new BuilderAnimMoveNode(data.creatingPoint,
                data.manager.getNodePosition(event.getNode().getId()),
                data.getNode(event.getNode().getId())));
    }

    public void setEndAnimationHandler(IEndInitAnimation endAnimationHandler) {
        this.endInitAnimation = endAnimationHandler;
    }

    public RemovePreparation getRemovePreparation() {
        if (preparation != null) {
            if (preparation instanceof Removing) {
                return ((Removing) preparation).removePreparation;
            }
        }
        return null;
    }

    public void clear() {
        if (preparation != null) {
            preparation.clearBeforeNewAction();
            preparation = null;
        }
    }

    private Point2D initFlashMessagePosition(Pane canvas) {
        return new Point2D(canvas.getWidth() / 2 + 100, 10);
    }

    private Point2D initCreatingPoint(Pane canvas) {
        return new Point2D(
                canvas.getWidth() / 2 - ISplayNodesElements.WIDTH / 2,
                0
        );
    }
}
