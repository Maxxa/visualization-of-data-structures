package cz.upce.fei.muller.binaryHeap.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.commons.utils.dialogs.PresetsDialog;
import cz.commons.utils.dialogs.ProgressDialog;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.core.InsertExecute;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.binaryHeap.BinaryHeapPresetItem;
import cz.upce.fei.muller.binaryHeap.BinaryHeapPresets;
import cz.upce.fei.muller.binaryHeap.animations.RemovePreparation;
import cz.upce.fei.muller.binaryHeap.gui.HelpDialog;
import cz.upce.fei.muller.binaryHeap.gui.StructureControls;
import cz.upce.fei.muller.binaryHeap.structure.BinaryHeap;
import cz.upce.fei.muller.binaryHeap.structure.HeapNode;
import cz.upce.fei.muller.binaryHeap.structure.HeapType;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapController extends Controller {

    private BinaryHeap heap;
    private ITreeLayoutManager manager;
    private final EventBus eventBus = new EventBus();
    private final AnimationsHandlersCore animationCore;

    public BinaryHeapController(ToolBarControlsContainer containerControls, ITreeLayoutManager manager) {
        super(containerControls);
        this.manager = manager;
        this.initStructureControls(containerControls);
        heap = new BinaryHeap(eventBus, HeapType.MIN);
        animationCore = new AnimationsHandlersCore(animationControl,manager);
        eventBus.register(animationCore);
        initAnimationHandlersFinished();
    }

    private void initAnimationHandlersFinished() {
        animationCore.setEndAnimationHandler(new IEndInitAnimation() {
            @Override
            public void endAnimation(boolean stepping) {
                if (stepping) {
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
                clearBeforeNewAction();
                HeapNode newNode = new HeapNode(parsedValue);
                animationControl.clear();
                containerControls.getStructureControls().disableButtons();
                heap.insert(newNode);

            }
        });

        controls.addRemoveRootHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(showDialogIsEmpty()){
                    return;
                }
                containerControls.getStructureControls().disableButtons();
                clearBeforeNewAction();
                heap.removeRoot();
            }
        });

    }

    @Override
    protected EventHandler<ActionEvent> getHelpHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelpDialog.show();
            }
        };
    }

    @Override
    protected EventHandler<ActionEvent> getPatternHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PresetsDialog<HeapNode, BinaryHeapPresetItem> dlg = new PresetsDialog<>("Vzory", new BinaryHeapPresets());
                if (dlg.showDialog() == Dialog.Result.OK) {
                    if (showHeapType() == Dialog.Result.OK) {
                            controlsContainer.getStepControls().setCheckBoxSelected(false);
                            controlsContainer.getAnimationsControls().setSliderValue(1);
                            loadPreset(dlg.getSelectedPresetItems(),new InsertExecute<HeapNode>() {
                                @Override
                                public void insert(HeapNode value) {
                                    heap.insert(value);
                                }
                            }, dlg.runAnimation());
                    }
                }
            }
        };
    }




    @Override
    protected EventHandler<ActionEvent> getResetHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showHeapType();
            }
        };
    }

    private Dialog.Result showHeapType() {
        HeapType[] buttons = new HeapType[]{HeapType.MIN,HeapType.MAX};
        Dialog.CustomButtonsDialog<HeapType> dlg = Dialog.createCustomButtonsDialog("Reset", "Vyberte typ nové haldy:", Dialog.Icon.QUESTION, buttons, true);
        Dialog.Result result = dlg.showDialog();
        if (result == Dialog.Result.OK) {
            heap = new BinaryHeap(eventBus,dlg.getResult());
            clear();
            controlsContainer.getStructureControls().enableButtons();
        }
        return result;
    }

    private boolean showDialogIsEmpty() {
        if (heap.isEmpty()) {
            Dialog.showInformation("Chyba", "Binární halda je prázdná.");
            return true;
        }
        return false;
    }

    private void clear(){
        heap.clear();
        manager.clear();
        animationCore.setRemovePreparation(null);

    }

    private void clearBeforeNewAction(){
        System.err.println("NEW ACTION =================================================================================");
        RemovePreparation removePreparation =animationCore.getRemovePreparation();
        if(removePreparation!=null){
            removePreparation.executeRemove();
        }
        animationCore.clear();
    }

}
