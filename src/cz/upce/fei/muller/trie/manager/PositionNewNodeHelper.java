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
        double size = right.minX - currentBox.maxX;
        if (size <= 0) { // prekriva se
            right.move(setting.getHorizontalSpace() / 2);
            moveCurrent(size - setting.getHorizontalSpace() / 2);
        } else if (size < setting.getHorizontalSpace()) {
            right.move(size);
        }
    }

    // vkladam jako praveho
    private void buildToRight() {
        double size = currentBox.minX - left.maxX;
        if (size <= 0) { // prekriva se
            left.move(-setting.getHorizontalSpace() / 2);
            moveCurrent(-size + setting.getHorizontalSpace() / 2);
        } else if (size < setting.getHorizontalSpace()) {
            left.move(-size);
        }
    }

    private void buildIntoLeftAndRight() { //TODO
        double width = currentBox.maxX - currentBox.minX;
        double spaceLeftRight = right.minX - left.maxX;
        if (width <= spaceLeftRight) { // mezi oba prvky se vejde(bez nutnych mezer)
            if (width > (spaceLeftRight - 2 * setting.getHorizontalSpace())) {
                //mizi prvky je dost mista pro vloženi ale málo pro mezery..
                double space = (spaceLeftRight - 2 * setting.getHorizontalSpace()) - width;
                left.move(space / 2);
                right.move(-space / 2);
                moveCurrent(left.maxX - positionX-space/2);
            } else {
                buildIntoItsSpace(width);
            }
        } else {
            double moving = (width + setting.getHorizontalSpace() * 2 - spaceLeftRight) / 2;
            left.move(-moving);
            right.move(moving);
            setPositionX(left.maxX - moving + setting.getHorizontalSpace());
        }
    }

    private void buildIntoItsSpace(double width) {
        // prvek se vleze mezi oba bloky jen je potreba zkontrolovat rozmezi. skrz pozici pripadne upravit leveho nebo praveho...
        double toLeft = right.minX - currentBox.maxX;
        double toRight = currentBox.minX - left.maxX;

        if (toRight <= 0 || toRight < setting.getHorizontalSpace()) {
            setPositionX(left.maxX + setting.getHorizontalSpace());
            return;
        }
        if (toLeft <= 0 || toLeft < setting.getHorizontalSpace()) {
            setPositionX(right.minX - setting.getHorizontalSpace() - width);
        }
    }
}
