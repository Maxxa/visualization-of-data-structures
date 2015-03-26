package cz.upce.fei.muller.trie.core;

import com.google.common.eventbus.EventBus;
import cz.commons.utils.dialogs.Dialog;
import cz.commons.utils.dialogs.PresetsDialog;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.core.IEndInitAnimation;
import cz.upce.fei.common.core.InsertExecute;
import cz.upce.fei.common.gui.toolBars.ToolBarControlsContainer;
import cz.upce.fei.muller.trie.TriePresetItem;
import cz.upce.fei.muller.trie.TriePresets;
import cz.upce.fei.muller.trie.gui.HelpDialog;
import cz.upce.fei.muller.trie.gui.TrieStructureControl;
import cz.upce.fei.muller.trie.manager.LayoutManager;
import cz.upce.fei.muller.trie.structure.Trie;
import cz.upce.fei.muller.trie.structure.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Vojtěch Müller
 */
public class TrieController extends Controller{

    private final LayoutManager layoutManager;

    private final EventBus eventBus = new EventBus();
    private final Trie<Word> trie;
    private AnimationsCore animationCore;

    public TrieController(ToolBarControlsContainer toolBarControlsContainer, LayoutManager layoutManager) {
        super(toolBarControlsContainer);
        this.layoutManager = layoutManager;
        animationCore = new AnimationsCore(animationControl,layoutManager);
        trie = new Trie<>(eventBus);
        eventBus.register(animationCore);
        initStructureHandlers();
        initEndAnimation();
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
                PresetsDialog<Word, TriePresetItem> dlg = new PresetsDialog<>("Vzory", new TriePresets());
                if (dlg.showDialog() == Dialog.Result.OK) {
                        clear();
                        controlsContainer.getStepControls().setCheckBoxSelected(false);
                        controlsContainer.getAnimationsControls().setSliderValue(1);
                        loadPreset(dlg.getSelectedPresetItems(),new InsertExecute<Word>() {
                            @Override
                            public void insert(Word word) {
                                trie.add(word);
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
            }
        };
    }

    private void initStructureHandlers() {
        final TrieStructureControl controls = ((TrieStructureControl) controlsContainer.getStructureControls());
        controls.addInsertHandler(getInsertHandler(controls));
        controls.addRemoveHandler(getRemoveHandler(controls));
        controls.addSearchHandler(getSearchHandler(controls));
    }

    private void clear(){
        trie.clear();
        animationCore.clear();
        layoutManager.clear();
    }

    private EventHandler<ActionEvent> getInsertHandler(final TrieStructureControl controls) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Word newWord = prepareBeforeNewAction(controls);
                trie.add(newWord);
            }
        };
    }

    private Word prepareBeforeNewAction(TrieStructureControl controls) {
        clearBeforeNewAction();
        Word newWord = new Word(controls.getTextValue());
        animationControl.clear();
        controls.disableButtons();
        return newWord;
    }

    private void clearBeforeNewAction() {
        animationCore.clearBeforeNewAction();
    }

    private EventHandler<ActionEvent> getSearchHandler(final TrieStructureControl controls) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Word newWord = prepareBeforeNewAction(controls);
                trie.get(newWord);
            }
        };
    }

    private EventHandler<ActionEvent> getRemoveHandler(final TrieStructureControl controls) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Word newWord = prepareBeforeNewAction(controls);
                trie.remove(newWord);
            }
        };
    }

    private void initEndAnimation() {
        animationCore.setEndAnimationHandler(new IEndInitAnimation() {
            @Override
            public void endAnimation(boolean stepping) {
                if (stepping) {
                    controlsContainer.getStepControls().enableBtnNext();
                }
            }
        });
    }

}
