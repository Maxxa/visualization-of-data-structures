package cz.upce.fei.common.gui.step;

/**
 * @author Vojtěch Müller
 */
public interface IStepListener {

    void isStepChange(Boolean newValue);
    void next();
    void previous();

}
