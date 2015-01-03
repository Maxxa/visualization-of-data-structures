package cz.upce.fei.common.core;

/**
 * Rozhrani pro ovladani animaci.
 * @author Martin Šára
 * @deprecated
 */
public interface UIControl {

    /**
     * De/aktivace ovladacich tlacitek.
     * @param disable 
     */
    void disableControls(boolean disable);
    
    /**
     * De/aktivace krokovaciho tlacitka.
     * @param disable 
     */
    void disableStepping(boolean disable);
    
    /**
     * Predani informace do GUI.
     * @param info 
     */
    void info(String info);
    
}
