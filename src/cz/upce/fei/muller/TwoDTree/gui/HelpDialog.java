package cz.upce.fei.muller.TwoDTree.gui;

import cz.commons.graphics.IGraphics;
import cz.commons.utils.dialogs.Dialog;
import cz.commons.utils.dialogs.HTMLDialog;

/**
 * @author Vojtěch Müller
 */
public class HelpDialog
{
    public static void show() {
        String message = ""
                + "<html>"
                + "<head><style>body{font-size:" + (13* IGraphics.PLATFORM_SCALE) + "px; font-family:verdana;}</style></head>"
                + "<body>"

                + "<p><b>Ovládání:</b></p>"
                + "<p>"
                + "<ul>"
                + "<li>Při držení klávesy CTRL lze rotací kolečka myši animační scénu přibližovat/oddalovat.</li>"
                + "<li>Stisknutím a&nbsp;tažením levého tlačítka myši v&nbsp;animační scéně lze scénu posunovat.</li>"
                + "<li>Pokud není jakákoliv hodnota zobrazena celá, zobrazí se po najetí kurzoru myši.</li>"
                + "</ul>"
                + "</p>"

                + "<p><b>2D Strom:</b></p>"
                + "<p><ul>"
                + "<li>multidimenzionální datová struktura</li>"
                + "<li>implementace k-D stromu, kde k znační na kolik dimenzí se dělí prostor</li>"
                + "<li>slouží k uchovávání prostorových dat (např. souřadnic)</li>"
                + "<li>každá úroveň stromu je dělena dle jiné dimenze tzv. diskriminátoru</li>"
                + "</ul>"
                +"Tato implementace pracuje s rozsahem x a y = 0-200"
                + "</p>"

                + "<p>"
                + "<br><br>"
                + "Vytvořil: Vojtěch Müller, Univerzita Pardubice, 2015."
                + "</p>";

        HTMLDialog.show("Nápověda", message, Dialog.Icon.INFORMATION, Dialog.Buttons.OK, 600, 450);
    }


}
