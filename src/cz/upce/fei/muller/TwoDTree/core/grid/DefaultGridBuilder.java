package cz.upce.fei.muller.TwoDTree.core.grid;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Vojtěch Müller
 */
public class DefaultGridBuilder {

    private final Pane gridView;
    private final Label label;

    private final int paddingTop = 50;

    public DefaultGridBuilder(Pane gridView, Label label) {
        this.gridView = gridView;
        this.label = label;
    }

    public GridPositionCalc build() {
        int width = (int) ((gridView.getPrefHeight()));
        int x = (int) ((gridView.getPrefWidth()/2)-width/2);
        initRect(width, x);
        initLabels(width,x);
        initLabelText(x,width);
        return new GridPositionCalc(x,paddingTop,width);
    }

    private void initLabelText(int x, int width) {
        label.setTranslateX(x+width/2-10);
        label.setTranslateY(paddingTop/4);
        gridView.getChildren().addAll(label);
    }

    private void initLabels(int width, int x) {
        initLabel(x-20,paddingTop-15,"200");
        initLabel(x+width,paddingTop+width,"200");
        initLabel(x-10,paddingTop+width,"0");
    }

    private void initRect(int width, int x) {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(paddingTop);
        rectangle.translateYProperty();
        rectangle.setWidth(width);
        rectangle.setHeight(width);
        gridView.getChildren().addAll(rectangle);
    }

    private void initLabel(int x,int y,String text){
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        gridView.getChildren().addAll(label);
    }
}
