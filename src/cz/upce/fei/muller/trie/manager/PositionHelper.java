package cz.upce.fei.muller.trie.manager;

import javafx.geometry.Point2D;

/**
 * @author Vojtěch Müller
 */
class PositionHelper {

    private Box left = null;
    private Box right = null;
    private final Point2D position;
    private final double width;
    private final LayoutManagerSetting setting;

    double diffLeft;
    double diffRight;
    Point2D currentPosition;


    public PositionHelper(BlockManager leftBlock, BlockManager rightBlock, Point2D position, double width, LayoutManagerSetting setting) {
        this.position = position;
        this.width = width;
        this.setting = setting;
        if (leftBlock != null) {
            left = new Box(leftBlock);
        }
        if (rightBlock != null) {
            right = new Box(rightBlock);
        }

    }

    public void build() {
        double positionY= this.position.getY();
        double positionX = this.position.getX();
        double positionXMax = positionX+width;

        if (left!=null){




        }



//        if(left!=null){
//            if(left.isInRange(positionX)){
//                diffLeft = -setting.getHorizontalSpace()/2;
//                positionX= left.maxX-diffLeft;
//            }else{
//                double diff = positionX-left.maxX;
//                if(diff<setting.getHorizontalSpace()){
//                    diffLeft = diff-setting.getHorizontalSpace();
//                }
//            }
//        }
//        if(right!=null){
//            if(right.isInRange(positionX)){
//                if(left!=null){
//
//                }else{
//                    diffRight = setting.getHorizontalSpace()/2;
////                    positionX=+
//                }
//            }else{
//                double diff = left.minX - (positionX + width);
//                if (diff < setting.getHorizontalSpace()) {
//                    diffRight = setting.getHorizontalSpace() - diff;
//                }
//            }
//        }

        currentPosition = new Point2D(positionX,positionY);
    }

    class Box {

        double minX;
        double maxX;
        BlockManager manager;

        Box(BlockManager manager) {
            this.manager = manager;
            minX = manager.blockPosition.getX();
            minX = minX + manager.size() * setting.getMinNodeWidth();
        }

        boolean isInRange(double x) {
            return x >= minX && x <= maxX;
        }
    }

}
