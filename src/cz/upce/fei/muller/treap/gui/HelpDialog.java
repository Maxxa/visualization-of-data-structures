package cz.upce.fei.muller.treap.gui;

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

                + "<p><b>Treap</b></p>"
                + "<p>"
                + "<ul>"
                + "<li>autoři: Cecilia R. Argon a Raimud Seidel, 1989,</li>"
                + "<li>implementace ADT tabulka,</li>"
                + "</ul>"
                + "</p>"
                +"<p>   Uzly stromu jsou charakterizovány unikátní položkou Klíč a položkou Priorita. Vyhledávání využívá stejných principů jako binární vyhledávací strom.</p>"
                +"<p>Náhodně vygenerované priority jsou v rozmezí 0-1000.</p>"
                + "<p>"
                + "<br><br>"
                + "Vytvořil: Vojtěch Müller, Univerzita Pardubice, 2015."
                + "</p>";

        HTMLDialog.show("Nápověda", message, Dialog.Icon.INFORMATION, Dialog.Buttons.OK, 600, 450);
    }


}
