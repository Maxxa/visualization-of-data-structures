package cz.upce.fei.muller.TwoDTree.animations;

import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.TwoDTree.graphics.ITwoDNodesElements;
import cz.upce.fei.muller.TwoDTree.graphics.TwoDGraphicsNode;
import javafx.animation.Transition;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class FindPlacePreparation {

    TwoDGraphicsNode insertedNode;

    private Point2D creatingPoint;

    private List<Transition> movings = new ArrayList<>();

    public FindPlacePreparation(TwoDGraphicsNode insertedNode, Point2D creatingPoint) {
        this.insertedNode = insertedNode;
        this.creatingPoint = creatingPoint;
    }

    public void addMove(Point2D point, TwoDGraphicsNode comparingNode, boolean compareX){
        Point2D pointTo = new Point2D(point.getX(),point.getY()- ITwoDNodesElements.HEIGHT-10);
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

    public TwoDGraphicsNode getInsertedNode() {
        return insertedNode;
    }

    public void addTransition(Transition animation) {
        movings.add(animation);
    }
}
