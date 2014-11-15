package cz.upce.fei.muller.trie.structure;

/**
 * @author Vojtěch Müller
 */
public abstract class Description {

    public final static Description EMPTY = new Description() {
        @Override
        public String getDescription() {
            return "";
        }

        @Override
        public void setDescription(String description) { }
    };

    public abstract String getDescription();
    public abstract void setDescription(String description);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        String description = getDescription();
        Description that = (Description) o;
        if (description != null ? !description.equals(that.getDescription()) : that.getDescription() != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getDescription() != null ? getDescription().hashCode() * 29 : 0;
    }
}
