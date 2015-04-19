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
import cz.upce.fei.muller.TwoDTree.gui.CanvasChangeImpl;
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
    private final GridViewController gridViewController;
    private final AnimationsHandlersCore animationCore;

    public TwoDTreeController(ToolBarControlsContainer controlsContainer, ITreeLayoutManager manager, CanvasChangeImpl canvasChange) {
        super(controlsContainer);
        this.manager = manager;
        gridViewController = new GridViewController(tree,canvasChange);
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
        ParserInputData dataParser = new ParserInputData(controls);
        controls.addInsertHandler(getInsertHandler(controls,dataParser));
        controls.addSearchHandler(getSearchHandler(controls,dataParser));
        controls.addRemoveHandler(getRemoveHandler(controls,dataParser));
        controls.addViewSwitchHandler(gridViewController);
    }

    private EventHandler<ActionEvent> getInsertHandler(final TwoDTreeStructureControl controls,final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearBeforeNewAction();
                CoordinateHelper helper = dataParser.action();
                if(helper==null){
                    return;
                }
                Coordinate coordinate = new Coordinate(helper.getX(), helper.getY());
                controls.disableButtons();
                tree.insert(coordinate);

            }
        };
    }

    private EventHandler<ActionEvent> getRemoveHandler(final TwoDTreeStructureControl controls, final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                clearBeforeNewAction();
                CoordinateHelper helper = dataParser.action();
                if(helper==null){
                    return;
                }
                controls.disableButtons();
                tree.remove(helper.getX(),helper.getY());
            }
        };
    }

    private EventHandler<ActionEvent> getSearchHandler(final TwoDTreeStructureControl controls, final ParserInputData dataParser) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (showDialogIsEmpty()) {
                    return;
                }
                clearBeforeNewAction();
                CoordinateHelper helper = dataParser.action();
                if(helper==null){
                    return;
                }
                controls.disableButtons();
                tree.find(helper.getX(), helper.getY());
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
        animationCore.clear();
        gridViewController.clearBeforeNewAction(controlsContainer.getStructureControls());
    }

    private void clearBeforeNewAction() {
        RemovePreparation removePreparation = animationCore.getRemovePreparation();
        if (removePreparation != null) {
            removePreparation.executeRemove();
        }
        gridViewController.clearBeforeNewAction(controlsContainer.getStructureControls());
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
