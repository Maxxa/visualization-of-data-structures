package cz.upce.fei.muller.treap.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.commons.utils.dialogs.PresetsDialog;
import cz.upce.fei.common.animations.RemovePreparation;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.core.InsertExecute;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.treap.TreapPresetItem;
import cz.upce.fei.muller.treap.TreapPresets;
import cz.upce.fei.muller.treap.gui.HelpDialog;
import cz.upce.fei.muller.treap.gui.TreapStructureControls;
import cz.upce.fei.muller.treap.structure.Treap;
import cz.upce.fei.muller.treap.structure.TreapNodeImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class TreapController extends Controller {

    private Treap<Integer, TreapNodeImpl> treap;
    private ITreeLayoutManager manager;
    private final EventBus eventBus = new EventBus();
    private final AnimationsEventsHandlersCore animationCore;

    public TreapController(ToolBarControlsContainer containerControls, ITreeLayoutManager manager) {
        super(containerControls);
        this.manager = manager;
        this.initStructureControls(containerControls);
        treap = new Treap<>(eventBus);
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

    private void initStructureControls(final ToolBarControlsContainer containerControls) {
        final TreapStructureControls controls = (TreapStructureControls) containerControls.getStructureControls();
        ParserInputData dataParser = new ParserInputData(controls);
        controls.addInsertHandler(getInsertHandler(controls, dataParser));
        controls.addFindHandler(getSearchHandler(controls, dataParser));
        controls.addRemoveHandler(getRemoveHandler(controls, dataParser));

    }

    private EventHandler<ActionEvent> getInsertHandler(final TreapStructureControls controls, final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearBeforeNewAction();
                Integer result = dataParser.action();
                if (result == null) {
                    return;
                }
                TreapNodeImpl coordinate = new TreapNodeImpl(result);
                controls.disableButtons();
                treap.insert(coordinate);
            }
        };
    }

    private EventHandler<ActionEvent> getRemoveHandler(final TreapStructureControls controls, final ParserInputData dataParser) {
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
                treap.remove(result);
            }
        };
    }

    private EventHandler<ActionEvent> getSearchHandler(final TreapStructureControls controls, final ParserInputData dataParser) {
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
                treap.find(result);
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
                PresetsDialog<TreapNodeImpl, TreapPresetItem> dlg = new PresetsDialog<>("Vzory", new TreapPresets());
                if (dlg.showDialog() == Dialog.Result.OK) {
                    controlsContainer.getStepControls().setCheckBoxSelected(false);
                    controlsContainer.getAnimationsControls().setSliderValue(1);
                    loadPreset(dlg.getSelectedPresetItems(), new InsertExecute<TreapNodeImpl>() {
                        @Override
                        public void insert(TreapNodeImpl value) {
                            treap.insert(value);
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
        if (treap.isEmpty()) {
            Dialog.showInformation("Chyba", "Treap je prázdný.");
            return true;
        }
        return false;
    }

    private void clear() {
        treap.clear();
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
