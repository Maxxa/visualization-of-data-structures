package cz.upce.fei.muller.trie.manager;

/**
 * @author Vojtěch Müller
 */
public class PositionNewKeyHelper extends AbstractPositionHelper {

    public PositionNewKeyHelper(BlockManager leftBlock, BlockManager rightBlock, LayoutManagerSetting setting, double minX, double maxX) {
        super(leftBlock, rightBlock, setting, minX, maxX);
    }

    @Override
    void build() {
        //move right bro to right...
        if (existRight()) {
            double diff = right.minX-currentBox.maxX;
            if(diff<setting.getHorizontalSpace()){
                right.move(setting.getHorizontalSpace()-diff);
            }else if(right.minX<=currentBox.maxX){
                right.move(-diff+setting.getHorizontalSpace());
            }
        }
    }
}
