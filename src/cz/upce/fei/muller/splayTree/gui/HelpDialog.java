package cz.upce.fei.muller.splayTree.gui;

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

                + "<p><b>Splay:</b></p>"
                + "<p>"
                + "<ul>"
                + "<li>autoři: Daniel Sleator a Robert Tarjamen, 1985,</li>"
                + "<li>implementace ADT Tabulka,</li>"
                + "<li>paměťová reprezentace stejná jako u BVS - nedisponuje žádnými dalšími informacemi,</li>"
                + "<li>Move-to-front heuristika, každý nalezený prvek je přesunut do kořene stormu,</li>"
                + "<li> rychlý pro přístup k uzlům které jsou často vyhledávány,</li>"
                + "<li>využítí pro cache</li>"
                + "</ul>"
                + "</p>"

                + "<p>"
                + "<br><br>"
                + "Vytvořil: Vojtěch Müller, Univerzita Pardubice, 2015."
                + "</p>";

        HTMLDialog.show("Nápověda", message, Dialog.Icon.INFORMATION, Dialog.Buttons.OK, 600, 450);
    }


}
