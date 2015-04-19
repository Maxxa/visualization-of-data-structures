package cz.upce.fei.muller.TwoDTree.core;

import cz.commons.utils.dialogs.Dialog;
import cz.upce.fei.muller.TwoDTree.gui.TwoDTreeStructureControl;

/**
 * @author Vojtěch Müller
 */
public class ParserInputData {

    private final TwoDTreeStructureControl control;

    private int x;

    public ParserInputData(TwoDTreeStructureControl control) {
        this.control = control;
    }

    public CoordinateHelper action() {
        Integer x = null;
        Integer y = null;
        try {
            x = Integer.parseInt(control.getX());
            y = Integer.parseInt(control.getY());
        } catch (NumberFormatException e) {
            Dialog.showError("Chyba", "Zadáno neplatné číslo.");
            return null;
        }
        if (x < 0 || x > 200 || y < 0 || y > 200) {
            Dialog.showError("Chyba", "Neplatný rozsah hodnot! Povolené hodnoty 1-200.");
            return null;
        }


        final Integer finalX = x;
        final Integer finalY = y;
        return new CoordinateHelper() {
            @Override
            public int getX() {
                return finalX;
            }

            @Override
            public int getY() {
                return finalY;
            }
        };

    }


}
