package cz.upce.fei.muller.trie;


import cz.commons.utils.presets.PresetItem;
import cz.upce.fei.muller.trie.structure.Word;

/**
* @author Vojtěch Müller
*/
public class TriePresetItem implements PresetItem<Word> {

    private final String name;
    private final Word[] items;

    public TriePresetItem(String name, String[] items) {
        this.name = name;
        this.items = new Word[items.length];
        for (int i = 0; i <items.length; i++) {
            this.items[i] = new Word(items[i]);
        }
    }

    @Override
    public Word[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name;
    }

}
