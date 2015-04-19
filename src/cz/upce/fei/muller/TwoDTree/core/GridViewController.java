package cz.upce.fei.muller.TwoDTree.core;

import cz.commons.utils.ViewSwitchButton;
import cz.upce.fei.muller.TwoDTree.core.grid.DefaultGridBuilder;
import cz.upce.fei.muller.TwoDTree.core.grid.GridMouseHandler;
import cz.upce.fei.muller.TwoDTree.core.grid.GridPositionCalc;
import cz.upce.fei.muller.TwoDTree.core.grid.LinesContainer;
import cz.upce.fei.muller.TwoDTree.graphics.CircleWithData;
import cz.upce.fei.muller.TwoDTree.gui.CanvasChangeImpl;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import cz.upce.fei.muller.TwoDTree.structure.ExtendData;
import cz.upce.fei.muller.TwoDTree.structure.TwoDTree;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class GridViewController implements EventHandler<ActionEvent> {

    private final TwoDTree<Coordinate> tree;
    private final CanvasChangeImpl canvasChange;
    private GridPositionCalc gridPositionCalc;
    private final LinesContainer linesContainer = new LinesContainer();

    private final List<Node> elements = new ArrayList<>();
    private final List<CircleWithData> circles = new ArrayList<>();
    private final Label label = new Label();


    public GridViewController(TwoDTree<Coordinate> tree, CanvasChangeImpl canvasChange) {
        this.tree = tree;
        this.canvasChange = canvasChange;
        canvasChange.getGridView().addEventHandler(MouseEvent.MOUSE_MOVED, new GridMouseHandler(this));
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (gridPositionCalc == null) {
            gridPositionCalc = new DefaultGridBuilder(canvasChange.getGridView(), label).build();
        }
        if (ViewSwitchButton.class.isInstance(actionEvent.getSource())) {
            ViewSwitchButton btn = (ViewSwitchButton) actionEvent.getSource();
            if (btn.isTreeView()) {
                buildGrid();
            } else {
                canvasChange.getGridView().getChildren().removeAll(elements);
                elements.clear();
                linesContainer.clear();
                circles.clear();
                label.setText("");
            }
            canvasChange.swapCanvas();
        }
    }

    private void buildGrid() {
        for (ExtendData<Coordinate> extendData : tree) {
            if (extendData.isLeaf()) {
                addPoint(extendData);
            } else {
                addLine(extendData);
            }
        }
    }

    private void addLine(ExtendData<Coordinate> extendData) {
        boolean isX = extendData.getDimension().equals(ExtendData.Dimension.DIMENSION_X);
        Integer cutCoord = !isX ? extendData.getData().getX() : extendData.getData().getY();
        Integer coord = isX ? extendData.getData().getX() : extendData.getData().getY();

        Point2D currentPoint = gridPositionCalc.getPosition(isX ? cutCoord : coord, !isX ? cutCoord : coord);

        LinesContainer.Helper helper = linesContainer.getFromToCoordination(cutCoord, coord, !isX);
        Line line = buildContainerLine(coord, helper, isX);
        Line lineToCanvas = recalculateLinePosition(line);
        linesContainer.addLine(line, isX);
        canvasChange.getGridView().getChildren().addAll(lineToCanvas);
        elements.add(lineToCanvas);
        addPoint(extendData);
    }

    private void addPoint(ExtendData<Coordinate> extendData) {
        CircleWithData circle = new CircleWithData(extendData.getData());
        circle.setRadius(3);
        Point2D calcPoint = gridPositionCalc.getPosition(extendData.getData().getX(), extendData.getData().getY());
        circle.setTranslateX(calcPoint.getX());
        circle.setTranslateY(calcPoint.getY());
        canvasChange.getGridView().getChildren().addAll(circle);
        elements.add(circle);
        circles.add(circle);
    }

    private Line buildContainerLine(Integer coord, LinesContainer.Helper helper, Boolean isX) {
        Point2D from = new Point2D(
                isX ? coord : helper.from,
                !isX ? coord : helper.from
        );
        Point2D to = new Point2D(
                isX ? coord : helper.to,
                !isX ? coord : helper.to
        );
        return new Line(from.getX(), from.getY(), to.getX(), to.getY());
    }

    private Line recalculateLinePosition(Line line) {
        Point2D from = gridPositionCalc.getPosition(line.getStartX(), line.getStartY());
        Point2D to = gridPositionCalc.getPosition(line.getEndX(), line.getEndY());
        return new Line(from.getX(), from.getY(), to.getX(), to.getY());
    }

    public GridPositionCalc getGridPositionCalc() {
        return gridPositionCalc;
    }

    public List<CircleWithData> getCircles() {
        return circles;
    }

    public Label getLabel() {
        return label;
    }
}
