package cz.upce.fei.muller.splayTree.animations;

import cz.upce.fei.common.animations.builders.BuilderAnimMoveNode;
import cz.upce.fei.muller.splayTree.graphics.ISplayNodesElements;
import cz.upce.fei.muller.splayTree.graphics.SplayGraphicsNodeElement;
import javafx.animation.Transition;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class FindPlacePreparation {

    SplayGraphicsNodeElement searchingNode;

    private Point2D creatingPoint;

    private List<Transition> movings = new ArrayList<>();

    public FindPlacePreparation(SplayGraphicsNodeElement searchingNode, Point2D creatingPoint) {
        this.searchingNode = searchingNode;
        this.creatingPoint = creatingPoint;
    }

    public void addMove(Point2D point){
        Point2D pointTo = new Point2D(point.getX(),point.getY()- ISplayNodesElements.HEIGHT-5);
        BuilderAnimMoveNode builder = new BuilderAnimMoveNode(creatingPoint,pointTo, searchingNode);
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

    public SplayGraphicsNodeElement getSearchingNode() {
        return searchingNode;
    }

    public void addTransition(Transition animation) {
        movings.add(animation);
    }
}
