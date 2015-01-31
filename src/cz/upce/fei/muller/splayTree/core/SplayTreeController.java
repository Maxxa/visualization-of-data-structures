package cz.upce.fei.muller.splayTree.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.binaryHeap.structure.BinaryHeap;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;
import cz.upce.fei.muller.binaryHeap.structure.HeapType;
import cz.upce.fei.muller.splayTree.gui.StructureControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class SplayTreeController extends Controller {

    private BinaryHeap heap;
    private ITreeLayoutManager manager;
    private final EventBus eventBus = new EventBus();
    private final AnimationsEventsHandlersCore animationCore;

    public SplayTreeController(ToolBarControlsContainer containerControls, ITreeLayoutManager manager) {
        super(containerControls);
        this.manager = manager;
        this.initStructureControls(containerControls);
        heap = new BinaryHeap(eventBus, HeapType.MIN);
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

                HeapNode newNode = new HeapNode(parsedValue);
                animationControl.clear();
                containerControls.getStructureControls().disableButtons();
                heap.insert(newNode);

            }
        });

        controls.addRemoveHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                heap.removeRoot();
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

                HeapType[] buttons = new HeapType[]{HeapType.MIN,HeapType.MAX};
                Dialog.CustomButtonsDialog<HeapType> dlg = Dialog.createCustomButtonsDialog("Reset", "Vyberte typ nové haldy:", Dialog.Icon.QUESTION, buttons, true);
                Dialog.Result result = dlg.showDialog();
                if (result == Dialog.Result.OK) {
                    heap.clear();
                    manager.clear();
                    heap = new BinaryHeap(eventBus,dlg.getResult());
                    controlsContainer.getStructureControls().enableButtons();
//                    disableStepping(true); //TODO
                }

            }
        };
    }

    private boolean showDialogIsEmpty() {
        if (heap.isEmpty()) {
            Dialog.showInformation("Chyba", "Binární halda je prázdná.");
            return true;
        }
        return false;
    }

}
