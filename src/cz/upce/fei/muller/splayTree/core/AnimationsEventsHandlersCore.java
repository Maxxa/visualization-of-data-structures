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
        if (preparation != null) {
            preparation.endEvent();
        }
        endInitAnimation.endAnimation(data.animationControl.isMarkedAsStepping());
        if (!data.animationControl.isMarkedAsStepping()) {
            data.animationControl.playForward();
        }
        System.out.println("_________ LAST EVENT");
    }

    @Subscribe
    public void handleStartInserting(StartInserting event) {
        System.out.println("_________ START INSERTING");
        preparation = new Inserting(data, event.getInsertingNode());
    }

    @Subscribe
    public void handleStartFinding(StartFindEvent event) {
        System.out.println("_________ START FINDING");
        preparation = new Finding(data,(Integer)event.getKey());
    }

    @Subscribe
    public void handleStartRemoving(StartRemoving event) {
        System.out.println("_________ START REMOVING");
        preparation = new Removing(data, event.getKey());
    }

    @Subscribe
    public void handleStartRemoving(InsertNodeEvent event) {
        System.out.println("Inserting element and rebuild tree... ");
        if (preparation != null) {
            try {
                ((Inserting) preparation).insert(event.getRoot(),event.getReferenceHelperList());
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }
    }

    @Subscribe
    public void handleElementFindEnd(ElementFindEndEvent event) {
        System.out.println("Element find event, Is find? " + event.isFind());
        if (preparation != null) {
            try {
                ((Finding) preparation).findEnd(event.getFindNode(), event.isFind());
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }
    }

    @Subscribe
    public void handleElementKeyExist(ElementKeyExistEvent event) {
        System.out.println("Element key exist.");
        if (preparation != null) {
            try {
                ((Inserting) preparation).elementKeyExist();
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }
    }

    @Subscribe
    public void handleSplayOperation(SplayOperationEvent event) {
        System.out.println("Splay operation.");
        if (preparation != null) {
            try {
                preparation.splayOperation(event.getType());
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }

    }

    @Subscribe
    public void handleRotationEvent(RotationEvent event){
        System.out.println("Rotation operation.");
        if (preparation != null) {
            try {
                preparation.rotation(event);
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }
    }

    @Subscribe
    public void handleMoveToChildFind(MoveToChildEvent event) {
        System.out.println("Move to child match ...");
        if (preparation != null) {
            try {
                preparation.moveToChild(event.getFindingKey(), event.getComparingNode());
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }

    }

    @Subscribe
    public void handleMove(MoveElementEvent event) {
        System.out.println("Move node at moving ... match ...");
        if (preparation != null) {
            try {
                preparation.moveElementAtLayout(event.getElementId(), event.getOldPoint(), event.getNewPoint());
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }

    }

    @Subscribe
    public void handleMatchFindEvent(MatchFindEvent event) {
        System.out.println("Move to child match finding...");
        if (preparation != null) {
            try {
                preparation.matchFind(event.getKey());
            } catch (Exception ex) {
                for (int i = 0; i < ex.getStackTrace().length; i++) {
                    System.err.println(ex.getStackTrace()[i]);
                }
            }
        }
    }


    @Subscribe
    public void handleCreateRootEvent(CreateRootEvent event) {
        System.out.println("___HANDLE CREATE ROOT__");
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
                return preparation.removePreparation;
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
