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
        presets.add(new TriePresetItem("Sada 4", new String[]{"peru","cina","indie","usa","tanzanie","nemecko","francie","norsko","rusko","italie","recko","egypt"}));
        return presets;
    }

}
