package cz.upce.fei.muller.splayTree.core;

import cz.commons.utils.dialogs.Dialog;
import cz.upce.fei.muller.splayTree.gui.SplayStructureControls;

/**
 * @author Vojtěch Müller
 */
public class ParserInputData {

    private final SplayStructureControls control;

    public ParserInputData(SplayStructureControls control) {
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
            Dialog.showError("Chyba", "Neplatný rozsah hodnot! ");
            return null;
        }

        return result;
    }

}
