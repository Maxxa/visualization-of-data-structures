package cz.upce.fei.muller.trie.events;

/**
 * @author Vojtěch Müller
 */
public class WordNotFound {
    private final String description;

    public WordNotFound(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
