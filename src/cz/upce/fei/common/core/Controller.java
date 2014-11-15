package cz.upce.fei.common.core;

import cz.commons.graphics.Graphics;
import cz.commons.utils.dialogs.ProgressDialog;
import cz.upce.fei.common.events.EventQueue;

/**
 * Rodicovska trida pro ridici tridy.
 * @author Martin Šára
 * @param <G> typ grafiky
 * @param <Q> typ fronty
 */
public abstract class Controller<G /*extends Graphics*/, Q extends EventQueue> {
    
    /**
     * Graficka cast.
     */
    protected G graphics;
    /**
     * Fronta udalosti.
     */
    protected Q queue;
    /**
     * Ovladaci rozhrani (GUI).
     */
    protected UIControl uiControls;
    
    
    
    /**
     * Automaticke provadeni dalsich kroku (=plynula animace).
     */
    protected boolean autoNextStep = false;  
    /**
     * Indikace nacitani sady vstupnich dat.
     */
    protected boolean loadingPreset = false;
    /**
     * Dialog zobrazujici nacitani dat.
     */
    protected ProgressDialog progressDialog;
   
        
    
    /**
     * Vybere prvni udalosti z fronty a prislusne je zpracuje.
     */
    protected abstract void processEvent();    
    
    
    
    /**
     * Vola zpracovani dalsi udalosti z fronty (pokud neni prazdna).
     */
    public void step() {
        if (loadingPreset) return;
        if (queue.isEmpty()) {
            //Log.get().info("que is empty");
            uiControls.disableControls(false);
            uiControls.disableStepping(true);
            return;
        }
        
        if (progressDialog != null && progressDialog.getTotalEvents() > 0) {
            progressDialog.setProgress(queue.size());
        }
                
        uiControls.disableControls(true);
        uiControls.disableStepping(true);        
        
        processEvent();                
    }
    
    /**
     * Metoda zajistujici krokovani x animaci dle aktualniho nastaveni.
     * Je volana po kazdem dokonceni dilci animace.
     */
    public final void nextStep() {
        if (autoNextStep && loadingPreset == false) {
            step();
        } else {
            if (queue.isEmpty()) {
                uiControls.disableControls(false);
                uiControls.disableStepping(true);
            } else if (autoNextStep == false) {
                uiControls.disableStepping(false);
            }
        }
    }
    
    /**
     * Nastaveni automatickeho krokovani.
     * @param state 
     */
    public final void setAutoStep(boolean state) {
        this.autoNextStep = state;
    }
    
    /**
     * Zjisteni zapnuti automatickeho krokovani.
     * @return true pokud je automaticke krokovani zapnuto
     */
    public final boolean isAutoStep() {
        return autoNextStep;
    }
    
    /**
     * Zjisteni davkoveho nacitani prednastavene sady dat.
     * @return true pokud se davkove nacitaji data
     */
    public final boolean isLoadingPreset() {
        return loadingPreset;
    }
    
    /**
     * Nastaveni rychlosti animace.
     * @param speed rychlost v rozmezi 0-5
     * @throws IllegalArgumentException pokud je rychlost mimo pripustne meze
     */
    public final void setSpeed(double speed) {
        if (speed < 0 || speed > 5) {
            throw new IllegalArgumentException("rychlost musi byt v rozmezi 0-5");
        }
//        graphics.setSpeed(speed);
    }
    
    /**
     * Test prazdnosti fronty udalosti.
     * @return true pokud je fronta prazdna
     */
    public final boolean isQueueEmpty() {
        return queue.isEmpty();
    }
    
    /**
     * Pozastavi probihajici animaci.
     */
    public void pauseAnimation() {
//        graphics.pause();
    }
    
    /**
     * Spusti pozastavenou animaci.
     */
    public void playAnimation() {
//        graphics.play();
    }

}
