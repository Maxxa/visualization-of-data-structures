package cz.upce.fei.muller.TwoDTree.core.grid;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class LinesContainer {

    private List<Line> linesX = new ArrayList<>();
    private List<Line> linesY = new ArrayList<>();

    private final Comparator<Line> COMPARATOR_X = new Comparator<Line>() {
        @Override
        public int compare(Line o1, Line o2) {
            return Double.compare(o1.getStartX(), o2.getStartX());
        }
    };

    private final Comparator<Line> COMPARATOR_Y = new Comparator<Line>() {
        @Override
        public int compare(Line o1, Line o2) {
            return Double.compare(o1.getStartY(), o2.getStartY());
        }
    };

    public void addLine(Line line, boolean isX) {
        List<Line> coordinates = isX ? linesX : linesY;
        coordinates.add(line);
        Collections.sort(coordinates,isX?COMPARATOR_X:COMPARATOR_Y);
    }

    public Helper getFromToCoordination(Integer coordinate,Integer secondCoordinate, boolean isX) {
        Helper helper = new Helper();
        List<Line> coordinates = isX ? linesX : linesY;

        for (Line line : coordinates) {
            double coord = isX?line.getStartX():line.getStartY();
            if (coord <= coordinate && existIntersection(line, secondCoordinate, isX)) {
                helper.from = coord;
            } else if (coord >= coordinate && existIntersection(line, secondCoordinate, isX)) {
                helper.to = coord;
                break;
            }
        }
        return helper;
    }

    private boolean existIntersection(Line line, Integer secondCoordinate, boolean isX) {
        double min = isX?line.getStartY():line.getStartX();
        double max = isX?line.getEndY():line.getEndX();
        return secondCoordinate>=min && secondCoordinate<=max;
    }

    public void clear() {
        linesX.clear();
        linesY.clear();
    }

    public class Helper {
        public double from = 0;
        public double to = 200;
    }

}
