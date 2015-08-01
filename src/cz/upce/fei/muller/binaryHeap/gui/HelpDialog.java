package cz.upce.fei.muller.binaryHeap.gui;

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

                + "<p><b>Binární halda:</b></p>"
                + "<p><ul>"
                + "<li>využívá implementaci binárního stormu na poli</li>"
                + "<li>Max-heap - v kořenu stromu se nachází prvek s nejvyšší hodnotou</li>"
                + "<li>Min-heap - v kořenu stormu se nachází prvek s nejnižší hodnotou</li>"
                + "<li>implementace ADT Prioritní fronta</li>"
                + "<li>využití pro HeapSort</li>"
                + "</ul></p>"

                + "<p>"
                + "<br><br>"
                + "Vytvořil: Vojtěch Müller, Univerzita Pardubice, 2015."
                + "</p>";

        HTMLDialog.show("Nápověda", message, Dialog.Icon.INFORMATION, Dialog.Buttons.OK, 600, 450);
    }


}
