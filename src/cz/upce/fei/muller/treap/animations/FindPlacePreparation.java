package cz.upce.fei.muller.treap.animations;

import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.treap.graphics.ITreapBinaryNodesElements;
import cz.upce.fei.muller.treap.graphics.TreapGraphicElement;
import javafx.animation.Transition;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class FindPlacePreparation {

    TreapGraphicElement insertedNode;

    private Point2D creatingPoint;

    private List<Transition> movings = new ArrayList<>();

    public FindPlacePreparation(TreapGraphicElement insertedNode, Point2D creatingPoint) {
        this.insertedNode = insertedNode;
        this.creatingPoint = creatingPoint;
    }

    public void addMove(Point2D point){
        Point2D pointTo = new Point2D(point.getX(),point.getY()- ITreapBinaryNodesElements.HEIGHT-5);
        BuilderAnimMoveNode builder = new BuilderAnimMoveNode(creatingPoint,pointTo,insertedNode);
        creatingPoint=pointTo;
        Transition transition = movings.isEmpty()?builder.getAnimation():builder.getTranslateTransition();
        movings.add(transition);
    }

    public List<Transition> getMovings(){
        return movings;
    }

    public Point2D getLastPosition() {
        return creatingPoint;
    }

    public TreapGraphicElement getInsertedNode() {
        return insertedNode;
    }

    public void addTransition(Transition animation) {
        movings.add(animation);
    }
}
