package cz.upce.fei.common.graphics;

import cz.commons.graphics.BinaryNodeElement;
import cz.commons.graphics.LineElement;

/**
 * @author Vojtěch Müller
 */
public abstract class BinaryNodeWithLine extends BinaryNodeElement {

    private LineElement[] lineToChild = new LineElement[2];

    public BinaryNodeWithLine(int id, int keyWidth, int height) {
        super(id, keyWidth, height);
    }

    public void setChildLine(LineElement element, boolean isLeft){
        lineToChild[isLeft?0:1]= element;
    }

    public LineElement getChildLine(NodePosition position){
        return lineToChild[position.equals(NodePosition.LEFT)?0:1];
    }

    public boolean isLeftChild(){
        return isChild(NodePosition.LEFT);
    }

    public boolean isRightChild(){
        return isChild(NodePosition.RIGHT);
    }

    private boolean isChild(NodePosition position){
        LineElement line = getChildLine(position);
        return line!=null && line.getEnd()!=null && BinaryNodeWithLine.class.isInstance(line.getEnd());
    }

    public <T> T getLeftChild(){
        return getChild(NodePosition.LEFT);
    }

    public <T> T getRightChild(){
        return getChild(NodePosition.RIGHT);
    }

    public <T> T getChild(NodePosition position){
        if(isChild(position)){
            return (T) getChildLine(position).getEnd();
        }
        return null;
    }

}
