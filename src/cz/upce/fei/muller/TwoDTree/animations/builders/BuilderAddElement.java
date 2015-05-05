package cz.upce.fei.muller.TwoDTree.animations.builders;

import cz.commons.graphics.LineElement;
import cz.commons.graphics.NodePosition;
import cz.commons.layoutManager.WorkBinaryNodeInfo;
import cz.commons.utils.FadesTransitionBuilder;
import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.TwoDTree.animations.handlers.InsertingEndEventHandler;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/**
 * @author Vojtěch Müller
 */
public class BuilderAddElement extends BuilderAnimMoveNode {

    private final WorkBinaryNodeInfo currentInformation;
    private final boolean isLeftChild;

    public BuilderAddElement(Point2D to, Point2D creatingPoint, TwoDGraphicsNode element) {
        super(creatingPoint, to, element);

        currentInformation = null;
        isLeftChild = false;
    }

    public BuilderAddElement(Point2D to, Point2D from, WorkBinaryNodeInfo currentInformation, boolean leftChild) {
        super(from, to, currentInformation.get().getElement());
        this.currentInformation = currentInformation;
        this.isLeftChild = leftChild;
    }

    @Override
    protected FadeTransition getFadeBefore() {
        return getFade(true);
    }

    @Override
    protected FadeTransition getFadeAfter() {
        return getFade(false);
    }

    public FadeTransition getFade(boolean isBefore) {
        if (currentInformation!=null && currentInformation.hasParent()) {
            return FadesTransitionBuilder.getTransition(getLineFromParent(), Duration.millis(1), isBefore?1:0,isBefore? 0:1);
        }
        return null;
    }

    @Override
    public TranslateTransition getTranslateTransition() {
        TranslateTransition tt = super.getTranslateTransition();
        if(currentInformation!=null){
            tt.setOnFinished(new InsertingEndEventHandler(currentInformation,this.isLeftChild));
        }
        return tt;
    }

    private LineElement getLineFromParent() {
        TwoDGraphicsNode parent= currentInformation.getParent().getElement();
        return parent.getChildLine(this.isLeftChild ? NodePosition.LEFT:NodePosition.RIGHT);
    }
}
