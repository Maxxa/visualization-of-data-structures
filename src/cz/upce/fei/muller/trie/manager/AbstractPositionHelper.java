package cz.upce.fei.muller.trie.manager;

import cz.upce.fei.muller.trie.graphics.ITrieNodesSetting;
import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
public abstract class AbstractPositionHelper implements ITrieNodesSetting {

    Box left = null;
    Box right = null;

    final Box currentBox;

    final LayoutManagerSetting setting;

    double positionX;
    double positionY;

    public AbstractPositionHelper(
            BlockManager leftBlock,
            BlockManager rightBlock,
            LayoutManagerSetting setting,
            double minX, double maxX) {
        this.currentBox = new Box(minX, maxX);
        this.setting = setting;
        positionX = minX;
        if (leftBlock != null) {
            left = new Box(leftBlock);
            positionY = leftBlock.blockPosition.getY();
        }
        if (rightBlock != null) {
            right = new Box(rightBlock);
            positionY = rightBlock.blockPosition.getY();
        }
    }

    abstract void build();

    public boolean existLeft() {
        return left != null;
    }

    public boolean existRight() {
        return right != null;
    }

    public Point2D getPoint() {
        return new Point2D(positionX, positionY);
    }

    class Box {

        double minX;
        double maxX;
        BlockManager manager;

        Box(double minX, double maxX) {
            this.minX = minX;
            this.maxX = maxX;
            System.out.println("current min: "+minX+" max: "+maxX);
        }

        Box(BlockManager manager) {
            this.manager = manager;
            minX = manager.blockPosition.getX();
            maxX = minX + manager.size() * setting.getMinNodeWidth();
            System.out.println("min: "+minX+" max: "+maxX);
        }

        public void move(double diff) {
            if(diff<0){
                manager.moveToLeft(diff,setting.getHorizontalSpace());
            }else{
                manager.moveToRight(diff,setting.getHorizontalSpace());
            }
        }
    }

    void moveCurrent(double move){
        positionX = currentBox.minX+move;
        currentBox.minX=positionX;
        currentBox.maxX=currentBox.maxX+move;
    }

}
