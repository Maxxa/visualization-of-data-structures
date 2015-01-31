package cz.upce.fei.muller.treap.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.treap.gui.StructureControls;
import cz.upce.fei.muller.treap.structure.Treap;
import cz.upce.fei.muller.treap.structure.TreapNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class TreapController extends Controller {

    private Treap treap;
    private ITreeLayoutManager manager;
    private final EventBus eventBus = new EventBus();
    private final AnimationsEventsHandlersCore animationCore;

    public TreapController(ToolBarControlsContainer containerControls, ITreeLayoutManager manager) {
        super(containerControls);
        this.manager = manager;
        this.initStructureControls(containerControls);
        treap = new Treap(eventBus);
        animationCore = new AnimationsEventsHandlersCore(animationControl,manager);
        eventBus.register(animationCore);
        initAnimationHandlersFinished();
    }

    private void initAnimationHandlersFinished() {
        animationCore.setEndAnimationHandler(new IEndAnimation() {
            @Override
            public void endAnimation(boolean steping) {
                if (steping) {
                    controlsContainer.getStepControls().enableBtnNext();
                }
            }
        });
    }

    private void initStructureControls(final ToolBarControlsContainer containerControls) {
        final StructureControls controls = (StructureControls) containerControls.getStructureControls();
        controls.addInsertHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer parsedValue;
                try {
                    String s = controls.getTextValue();
                    parsedValue = Integer.parseInt(s.trim());
                } catch (NumberFormatException e) {
                    Dialog.showError("Chyba", "Zadáno neplatné číslo.");
                    return;
                }

                TreapNode newNode = new TreapNode(parsedValue);
                animationControl.clear();
                containerControls.getStructureControls().disableButtons();
                treap.insert(newNode);

            }
        });

        controls.addRemoveHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                //treap.removeRoot();
            }
        });

    }

    private void resetAnimation() {
        //TODO remove all animation from before values
    }

    @Override
    protected EventHandler<ActionEvent> getHelpHandler() {
        return null; //TODO
    }

    @Override
    protected EventHandler<ActionEvent> getPatternHandler() {
        return null; //TODO
    }

    @Override
    protected EventHandler<ActionEvent> getResetHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

//                HeapType[] buttons = new HeapType[]{HeapType.MIN, HeapType.MAX};
//                Dialog.CustomButtonsDialog<HeapType> dlg = Dialog.createCustomButtonsDialog("Reset", "Vyberte typ nové haldy:", Dialog.Icon.QUESTION, buttons, true);
//                Dialog.Result result = dlg.showDialog();
//                if (result == Dialog.Result.OK) {
//                    treap.clear();
//                    manager.clear();
//                    treap = new BinaryHeap(eventBus,dlg.getResult());
//                    controlsContainer.getStructureControls().enableButtons();
////                    disableStepping(true); //TODO
//                }

            }
        };
    }

    private boolean showDialogIsEmpty() {
        if (treap.isEmpty()) {
            Dialog.showInformation("Chyba", "Binární halda je prázdná.");
            return true;
        }
        return false;
    }

}
