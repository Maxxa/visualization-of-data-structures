package cz.upce.fei.muller.splayTree.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.commons.utils.dialogs.PresetsDialog;
import cz.upce.fei.common.animations.RemovePreparation;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.core.InsertExecute;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.splayTree.SplayPresetItem;
import cz.upce.fei.muller.splayTree.SplayPresets;
import cz.upce.fei.muller.splayTree.gui.HelpDialog;
import cz.upce.fei.muller.splayTree.gui.SplayStructureControls;
import cz.upce.fei.muller.splayTree.structure.SplayNodeImpl;
import cz.upce.fei.muller.splayTree.structure.SplayTree;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class SplayTreeController extends Controller {

    private SplayTree<Integer, SplayNodeImpl> splay;
    private ITreeLayoutManager manager;
    private final EventBus eventBus = new EventBus();
    private final AnimationsEventsHandlersCore animationCore;


    public SplayTreeController(ToolBarControlsContainer containerControls, ITreeLayoutManager manager) {
        super(containerControls);
        this.manager = manager;
        this.initStructureControls(containerControls);
        splay = new SplayTree<>(eventBus);
        animationCore = new AnimationsEventsHandlersCore(animationControl, manager);
        eventBus.register(animationCore);
        initAnimationHandlersFinished();
    }

    private void initAnimationHandlersFinished() {
        animationCore.setEndAnimationHandler(new IEndInitAnimation() {
            @Override
            public void endAnimation(boolean steping) {
                if (steping) {
                    controlsContainer.getStepControls().enableBtnNext();
                }
            }
        });
    }

    private void initStructureControls(ToolBarControlsContainer containerControls) {
        final SplayStructureControls controls = (SplayStructureControls) containerControls.getStructureControls();
        ParserInputData dataParser = new ParserInputData(controls);
        controls.addInsertHandler(getInsertHandler(controls, dataParser));
        controls.addFindHandler(getSearchHandler(controls, dataParser));
        controls.addRemoveHandler(getRemoveHandler(controls, dataParser));
    }

    private EventHandler<ActionEvent> getInsertHandler(final SplayStructureControls controls, final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearBeforeNewAction();
                Integer result = dataParser.action();
                if (result == null) {
                    return;
                }
                SplayNodeImpl coordinate = new SplayNodeImpl(result);
                controls.disableButtons();
                splay.insert(coordinate);
            }
        };
    }

    private EventHandler<ActionEvent> getRemoveHandler(final SplayStructureControls controls, final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                clearBeforeNewAction();
                Integer result = dataParser.action();
                if (result == null) {
                    return;
                }
                controls.disableButtons();
                splay.delete(result);
            }
        };
    }

    private EventHandler<ActionEvent> getSearchHandler(final SplayStructureControls controls, final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                clearBeforeNewAction();
                Integer result = dataParser.action();
                if (result == null) {
                    return;
                }
                controls.disableButtons();
                splay.find(result);
            }
        };
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
                PresetsDialog<SplayNodeImpl, SplayPresetItem> dlg = new PresetsDialog<>("Vzory", new SplayPresets());
                if (dlg.showDialog() == Dialog.Result.OK) {
                    controlsContainer.getStepControls().setCheckBoxSelected(false);
                    controlsContainer.getAnimationsControls().setSliderValue(1);
                    clear();
                    loadPreset(dlg.getSelectedPresetItems(), new InsertExecute<SplayNodeImpl>() {
                        @Override
                        public void insert(SplayNodeImpl value) {
                            splay.insert(value);
                        }
                    }, dlg.runAnimation());
                }
            }
        };
    }

    @Override
    protected EventHandler<ActionEvent> getResetHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clear();
                controlsContainer.getStructureControls().enableButtons();
            }
        };
    }

    private boolean showDialogIsEmpty() {
        if (splay.isEmpty()) {
            Dialog.showInformation("Chyba", "Treap je prázdný.");
            return true;
        }
        return false;
    }

    private void clear() {
        splay.clear();
        manager.clear();
        animationControl.clear();
    }

    private void clearBeforeNewAction() {
        RemovePreparation removePreparation = animationCore.getRemovePreparation();
        if (removePreparation != null) {
            removePreparation.executeRemove();
        }
        animationCore.clear();
    }
}
