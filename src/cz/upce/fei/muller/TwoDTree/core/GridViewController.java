package cz.upce.fei.muller.TwoDTree.core;

import cz.commons.utils.ViewSwitchButton;
import cz.upce.fei.muller.TwoDTree.core.grid.DefaultGridBuilder;
import cz.upce.fei.muller.TwoDTree.core.grid.GridPositionCalc;
import cz.upce.fei.muller.TwoDTree.gui.CanvasChangeImpl;
import cz.upce.fei.muller.TwoDTree.structure.Coordinate;
import cz.upce.fei.muller.TwoDTree.structure.TwoDTree;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vojtěch Müller
 */
public class GridViewController implements EventHandler<ActionEvent> {
    private final TwoDTree<Coordinate> tree;
    private final CanvasChangeImpl canvasChange;
    private GridPositionCalc gridPositionCalc;

    private final List<Node> elements = new ArrayList<>();

    public GridViewController(TwoDTree<Coordinate> tree, CanvasChangeImpl canvasChange) {
        this.tree = tree;
        this.canvasChange = canvasChange;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(gridPositionCalc==null){
            gridPositionCalc = new DefaultGridBuilder(canvasChange.getGridView()).build();
        }
        if(ViewSwitchButton.class.isInstance(actionEvent.getSource())){
            ViewSwitchButton btn = (ViewSwitchButton) actionEvent.getSource();
            if(btn.isTreeView()){
                buildGrid();
            }else{
                canvasChange.getGridView().getChildren().removeAll(elements);
                elements.clear();
            }
            canvasChange.swapCanvas();
        }
    }

    private void buildGrid() {

        for (Coordinate cord:tree){
            System.out.println(cord);
        }

        //TODO build grid ...
    }
}
