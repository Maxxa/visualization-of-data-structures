package cz.upce.fei.muller.trie.manager;

/**
 * @author Vojtěch Müller
 */
public class PositionNewNodeHelper extends AbstractPositionHelper {

    public PositionNewNodeHelper(BlockManager leftBlock, BlockManager rightBlock,
                                 LayoutManagerSetting setting, double positionX, double positionY, double width) {
        super(leftBlock, rightBlock, setting, positionX, positionX + width);
        this.positionY = positionY;
    }

    @Override
    void build() {
        if (existLeft() && existRight()) {
            buildIntoLeftAndRight();
            return;
        }
        if (existLeft()) {
            buildToRight();
            return;
        }
        if (existRight()) {
            buildToLeft();
        }
    }

    //vkladam jako leveho
    private void buildToLeft() {
        double size = right.minX-currentBox.maxX;
        if (size <= 0) { // prekriva se
            right.move(setting.getHorizontalSpace() / 2);
            moveCurrent(size-setting.getHorizontalSpace()/2);
        } else if (size <= setting.getHorizontalSpace()) {
            right.move(size);
        }
    }

    // vkladam jako praveho
    private void buildToRight() {
        double size = currentBox.minX - left.maxX;
        if (size <= 0) { // prekriva se
            left.move(-setting.getHorizontalSpace() / 2);
            moveCurrent(-size+setting.getHorizontalSpace()/2);
        } else if (size <= setting.getHorizontalSpace()) {
            left.move(-size);
        }
    }

    private void buildIntoLeftAndRight() { //TODO
        System.out.println("vkladam jako leveho");
        double width = currentBox.maxX - currentBox.minX;
        double sizeLeft = right.minX-currentBox.maxX;//rozdil od leveho
        double sizeRight = currentBox.minX - left.maxX;

        double spaceLeftRight = right.minX - left.maxX;

        if(sizeLeft <0){
            //into right
            return;
        }else if(sizeRight < 0){
            //into left...
            return;
        }



        if (spaceLeftRight < setting.getHorizontalSpace()) {

        } else {
            if ((spaceLeftRight - width) < setting.getHorizontalSpace()) {

            } else {

            }


        }
    }
}
