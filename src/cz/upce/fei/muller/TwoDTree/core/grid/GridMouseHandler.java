package cz.upce.fei.muller.TwoDTree.core.grid;

import cz.upce.fei.muller.TwoDTree.core.GridViewController;
import cz.upce.fei.muller.TwoDTree.graphics.CircleWithData;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Vojtěch Müller
 */
public class GridMouseHandler implements EventHandler<MouseEvent> {

    private final GridViewController gridViewController;

    private Circle lastCircle;

    public GridMouseHandler(GridViewController gridViewController) {
        this.gridViewController = gridViewController;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (gridViewController.getGridPositionCalc().isPointInRect(mouseEvent.getX(), mouseEvent.getY())) {
            findNearestCircle(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
        } else {
            clearSelected();
        }
    }

    private void findNearestCircle(Point2D point2D) {
        CircleWithData changeCircle = null;
        Double lastMinimalDistance = Double.MAX_VALUE;
        for (CircleWithData circle : gridViewController.getCircles()) {
            Point2D point = new Point2D(circle.getTranslateX(), circle.getTranslateY());
            Double distance = point2D.distance(point);
            if (distance.compareTo(lastMinimalDistance) <= 0) {
                lastMinimalDistance = distance;
                changeCircle = circle;
            }
        }
        swapCircle(changeCircle);
    }

    private void swapCircle(CircleWithData circle) {
        clearSelected();
        lastCircle = circle;
        lastCircle.setFill(Color.RED);
        gridViewController.getLabel().setText(String.format("[ %s ; %s ]", circle.getCoordinate().getX(), circle.getCoordinate().getY()));
    }

    private void clearSelected() {
        if (lastCircle != null) {
            lastCircle.setFill(Color.BLACK);
            gridViewController.getLabel().setText("");
        }
    }
}
