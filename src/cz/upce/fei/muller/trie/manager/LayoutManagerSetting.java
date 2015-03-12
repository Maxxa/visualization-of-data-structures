package cz.upce.fei.muller.trie.manager;

import com.google.common.eventbus.EventBus;

/**
 * @author Vojtěch Müller
 */
public class LayoutManagerSetting {

    private final Integer paddingTop;
    private final Integer horizontalSpace;
    private final Integer verticalSpace;
    private final double nodeHeight;
    private final double minNodeWidth;

    protected EventBus eventBus = new EventBus();

    public LayoutManagerSetting(Integer paddingTop, Integer horizontalSpace, Integer verticalSpace, double nodeHeight, double minNodeWidth) {
        this.paddingTop = paddingTop;
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;
        this.nodeHeight = nodeHeight;
        this.minNodeWidth = minNodeWidth;
    }

    public Integer getPaddingTop() {
        return paddingTop;
    }

    public Integer getHorizontalSpace() {
        return horizontalSpace;
    }

    public Integer getVerticalSpace() {
        return verticalSpace;
    }

    public double getNodeHeight() {
        return nodeHeight;
    }

    public double getMinNodeWidth() {
        return minNodeWidth;
    }

    public void addHandler(Object o){
        this.eventBus.register(o);
    }
}
