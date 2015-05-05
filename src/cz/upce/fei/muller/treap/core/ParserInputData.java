package cz.upce.fei.muller.treap.core;

import cz.commons.utils.dialogs.Dialog;
import cz.upce.fei.muller.treap.gui.TreapStructureControls;

/**
 * @author Vojtěch Müller
 */
public class ParserInputData {

    private final TreapStructureControls control;

    public ParserInputData(TreapStructureControls control) {
        this.control = control;
    }

    public Integer action() {
        Integer result = null;
        try {
            result = Integer.parseInt(control.getTextValue());
        } catch (NumberFormatException e) {
            Dialog.showError("Chyba", "Zadáno neplatné číslo.");
            return null;
        }
        if (result < 0 ) {
            Dialog.showError("Chyba", "Neplatný rozsah hodnot! Povolené hodnoty 1-200.");
            return null;
        }

        return result;
    }

}
