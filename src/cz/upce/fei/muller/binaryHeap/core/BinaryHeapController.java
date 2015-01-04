package cz.upce.fei.muller.binaryHeap.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.binaryHeap.gui.StructureControls;
import cz.upce.fei.muller.binaryHeap.structure.BinaryHeap;
import cz.upce.fei.muller.binaryHeap.structure.HeapType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

/**
 * @author Vojtěch Müller
 */
public class BinaryHeapController extends Controller {

    private BinaryHeap heap;
    private ITreeLayoutManager manager;
    private final EventBus eventBus = new EventBus();

    public BinaryHeapController(Pane pane, ToolBarControlsContainer containerControls, ITreeLayoutManager manager) {
        super(containerControls);
        this.manager = manager;
        this.initStructureControls(containerControls);
        heap = new BinaryHeap(eventBus, HeapType.MIN);
    }

    private void initStructureControls(final ToolBarControlsContainer containerControls) {
        final StructureControls controls = (StructureControls) containerControls.getStructureControls();
        controls.addInsertHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String s = controls.getTextValue();
                    Integer parsedValue = Integer.parseInt(s.trim());
                } catch (NumberFormatException e) {
                    Dialog.showError("Chyba", "Zadáno neplatné číslo.");
                    return;
                }
                //TODO
            }
        });

        controls.addRemoveRootHandler(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(showDialogIsEmpty()){
                    return;
                }
                //TODO
            }
        });

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
//                    disableControls(false);
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
