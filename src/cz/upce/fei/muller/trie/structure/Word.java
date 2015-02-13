package cz.upce.fei.muller.trie.structure;

/**
 * @author Vojtěch Müller
 */
public class Word extends Description {

    private final String text;

    public Word(String text) {
        this.text = text;
    }

    @Override
    public String getDescription() {
        return text;
    }
}
