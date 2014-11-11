package cz.upce.fei.common.gui.step;

/**
 * @author Vojtěch Müller
 */
public abstract class StepListenerAdapter implements IStepListener{

    @Override
    public void isStepChange(Boolean newValue) {}

    @Override
    public void next() {}

    @Override
    public void previous() {}
}
