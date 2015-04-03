package cz.upce.fei.muller.TwoDTree.core;

import com.google.common.eventbus.EventBus;
import cz.commons.layoutManager.ITreeLayoutManager;
import cz.commons.utils.dialogs.Dialog;
import cz.commons.utils.dialogs.PresetsDialog;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.core.InsertExecute;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.TwoDTree.TwoDTreePresetItem;
import cz.upce.fei.muller.TwoDTree.TwoDTreePresets;
import cz.upce.fei.muller.TwoDTree.animations.RemovePreparation;
import cz.upce.fei.muller.TwoDTree.gui.HelpDialog;
import cz.upce.fei.muller.TwoDTree.gui.TwoDTreeStructureControl;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import cz.upce.fei.muller.TwoDTree.structure.TwoDTree;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Arrays;

/**
 * @author Vojtěch Müller
 */
public class TwoDTreeController extends Controller {

    private final EventBus eventBus = new EventBus();
    private ITreeLayoutManager manager;
    private TwoDTree<Coordinate> tree = new TwoDTree<>(eventBus);

    private final AnimationsHandlersCore animationCore;

    public TwoDTreeController(ToolBarControlsContainer controlsContainer, ITreeLayoutManager manager) {
        super(controlsContainer);
        this.manager = manager;
        this.initStructureControls(controlsContainer);
        animationCore = new AnimationsHandlersCore(animationControl, manager);
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

    private void initStructureControls(ToolBarControlsContainer controlsContainer) {
        final TwoDTreeStructureControl controls = (TwoDTreeStructureControl) controlsContainer.getStructureControls();
        controls.addInsertHandler(getInsertHandler(controls));
        controls.addSearchHandler(getSearchHandler(controls));
        controls.addRemoveHandler(getRemoveHandler(controls));
    }

    private EventHandler<ActionEvent> getInsertHandler(final TwoDTreeStructureControl controls) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer parsedValueX;
                Integer parsedValueY;
                try {
                    String x = controls.getX();
                    String y = controls.getY();
                    parsedValueX = Integer.parseInt(x.trim());
                    parsedValueY = Integer.parseInt(y.trim());
                } catch (NumberFormatException e) {
                    Dialog.showError("Chyba", "Zadáno neplatné číslo.");
                    return;
                }
                clearBeforeNewAction();
                Coordinate coordinate = new Coordinate(parsedValueX, parsedValueY);
                controls.disableButtons();
                tree.insert(coordinate);

            }
        };
    }

    private EventHandler<ActionEvent> getRemoveHandler(final TwoDTreeStructureControl controls) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                controls.disableButtons();
                clearBeforeNewAction();
                //TODO How to remove??
            }
        };
    }

    private EventHandler<ActionEvent> getSearchHandler(final TwoDTreeStructureControl controls) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                controls.disableButtons();
                clearBeforeNewAction();
                //TODO How search??
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
                final PresetsDialog<Coordinate, TwoDTreePresetItem> dlg = new PresetsDialog<>("Vzory", new TwoDTreePresets());
                if (dlg.showDialog() == Dialog.Result.OK) {
                    clear();
                    controlsContainer.getStepControls().setCheckBoxSelected(false);
                    controlsContainer.getAnimationsControls().setSliderValue(1);
                    loadPreset(dlg.getSelectedPresetItems(), new InsertExecute<Coordinate>() {
                        private boolean first = true;

                        @Override
                        public void insert(Coordinate value) {
                            if (first) {
                                tree.create(Arrays.asList(dlg.getSelectedPresetItems()));
                                first = false;
                            }
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

    private void clear() {
        tree.clear();
        manager.clear();
        animationCore.setRemovePreparation(null);
    }

    private void clearBeforeNewAction() {
        RemovePreparation removePreparation = animationCore.getRemovePreparation();
        if (removePreparation != null) {
            removePreparation.executeRemove();
        }
        animationCore.clear();
    }

    private boolean showDialogIsEmpty() {
        if (tree.isEmpty()) {
            Dialog.showInformation("Chyba", "2D strom je prázdný.");
            return true;
        }
        return false;
    }

}
