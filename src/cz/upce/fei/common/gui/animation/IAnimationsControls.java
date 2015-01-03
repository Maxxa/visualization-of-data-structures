package cz.upce.fei.common.gui.animation;

import cz.upce.fei.common.gui.toolBars.IToolBarControl;
import javafx.scene.layout.HBox;

/**
 * @author Vojtěch Müller
 */
public interface IAnimationsControls extends IToolBarControl {

    void enable();

    void disable();

    void addChangesListener(IAnimationListener listener);

    void removeChangesListener(IAnimationListener listener);

}
