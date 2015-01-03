package cz.upce.fei.common.gui.toolBars;

import cz.upce.fei.common.gui.animation.AnimationControls;
import cz.upce.fei.common.gui.animation.IAnimationsControls;
import cz.upce.fei.common.gui.patternsResetHelp.IPatternsResetHelpControls;
import cz.upce.fei.common.gui.patternsResetHelp.PatternsResetHelpControls;
import cz.upce.fei.common.gui.step.IStepControls;
import cz.upce.fei.common.gui.step.StepControls;
import cz.upce.fei.common.gui.structure.IStructureControls;
import cz.upce.fei.muller.trie.gui.StructureDefaultControl;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

/**
 * @author Vojtěch Müller
 */
public class ToolBarControlsContainer {

    private final IStructureControls structureControls;

    private final IStepControls stepControls = new StepControls();

    private final IPatternsResetHelpControls paternControls = new PatternsResetHelpControls();

    private final IAnimationsControls animationsControls = new AnimationControls();

    public ToolBarControlsContainer(IStructureControls structureControls) {
        this.structureControls = structureControls;
    }


    public ToolBar getToolBar() {
        return new ToolBar(
                structureControls.getToolBarHBox(),
                new Separator(Orientation.VERTICAL),stepControls.getToolBarHBox(),
                new Separator(Orientation.VERTICAL),animationsControls.getToolBarHBox(),
                new Separator(Orientation.VERTICAL),paternControls.getToolBarHBox()
        );
    }

    public IStructureControls getStructureControls() {
        return structureControls;
    }

    public IStepControls getStepControls() {
        return stepControls;
    }

    public IPatternsResetHelpControls getPaternControls() {
        return paternControls;
    }

    public IAnimationsControls getAnimationsControls() {
        return animationsControls;
    }

}
