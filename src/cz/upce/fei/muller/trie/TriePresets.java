package cz.upce.fei.muller.trie;

import cz.commons.utils.presets.Preset;
import cz.upce.fei.muller.trie.structure.Word;

import java.util.ArrayList;

/**
 * @author Vojtěch Müller
 */
public class TriePresets implements Preset<Word, TriePresetItem> {

    @Override
    public ArrayList<TriePresetItem> getAll() {
        ArrayList<TriePresetItem> presets = new ArrayList<>();
        presets.add(new TriePresetItem("Sada 1", new String[]{"amos","lev","anna","pavla","miloslav","lea","mila","pavel","petra","petr"}));
        presets.add(new TriePresetItem("Sada 2", new String[]{"karel","tereza","miroslav","nikoletta","lenka","katerina","martin","frantisek","alois"}));
        presets.add(new TriePresetItem("Sada 3", new String[]{"pardubice","praha","hradec","brno","ostrava","jihlava","policka","svitavy","podebrady","plzen"}));
        presets.add(new TriePresetItem("Sada 4", new String[]{
                "peru","cina","indie","kena","usa","nambie","tanzanie","nemecko","francie","norsko",
                "oman","kypr","nikaragua","mikronesie","mexiko","kuba","kanada",
                "rusko","nigerie","italie","recko","mosambik","egypt","madagaskar","nepal"}));
        presets.add(new TriePresetItem("Sada 5", new String[]{
                "packa","pad","padak","padelat","alias","almuzna","almanach","ano",
                "preambule","precizni","premie","premier","preventivni","prodejna","prodelat","prodavac",
                "pracant","prace","pracka","pracny","pracovat","pracujici","pragmaticky","prach","prahnout"
        }));

        return presets;
    }

}
