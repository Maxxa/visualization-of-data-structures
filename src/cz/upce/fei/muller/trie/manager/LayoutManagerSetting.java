package cz.upce.fei.muller.trie.manager;

/**
 * @author Vojtěch Müller
 */
public class LayoutManagerSetting {

    private final Integer paddingTop;
    private final Integer horizontalSpace;
    private final Integer verticalSpace;

    public LayoutManagerSetting(Integer paddingTop, Integer horizontalSpace, Integer verticalSpace) {
        this.paddingTop = paddingTop;
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;
    }
}
