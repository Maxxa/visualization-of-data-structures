package cz.upce.fei.muller.trie.gui;

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

//                + "<p><b>Klávesové zkratky:</b></p>"
//                + "<p>"
//                + "<ul>"
//                + "<li><b>ENTER</b> - Vložit prvek.</li>"
//                + "<li><b>DELETE</b> - Odstranit prvek.</li>"
//                + "<li><b>F1</b> - Najít prvek.</li>"
//                + "<li><b>F5</b> - Zapnout/vypnout krokování.</li>"
//                + "<li><b>F6</b> - Další krok.</li>"
//                + "<li><b>F7</b> - Snížit rychlost animace.</li>"
//                + "<li><b>F8</b> - Zvýšit rychlost animace.</li>"
//                + "<li><b>MEZERNÍK</b> - Pozastavení/spuštění animace.</li>"
//                + "</ul>"
//                + "</p>"

                + "<p>"
                + "<br><br>"
                + "Vytvořil: Vojtěch Müller, Univerzita Pardubice, 2015."
                + "</p>";

        HTMLDialog.show("Nápověda", message, Dialog.Icon.INFORMATION, Dialog.Buttons.OK, 600, 450);
    }


}
