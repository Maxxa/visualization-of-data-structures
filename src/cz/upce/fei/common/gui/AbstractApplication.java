package cz.upce.fei.common.gui;

import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.utils.SceneInfo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static cz.upce.fei.common.graphics.Graphics.PLATFORM_SCALE;

/**
 * This abstract class build default app GUI.
 * @author Vojtěch Müller
 */
public abstract class AbstractApplication<T extends Controller> extends Application {

    private T animationController;

    protected SceneInfo sceneInfo;

    private final BorderPane layout = new BorderPane();
    private final ScrollPane scrollPane = new ScrollPane();
    private final Pane canvas = new Pane();
    private ToolBar toolbar;

    @Override
    public void start(Stage stage) throws Exception {
        beforeStart(stage);
        sceneInfo = initSceneInfo();
        animationController = getController();

        toolbar= initToolbar();
        layout.setTop(toolbar);
        initScrollPane();

        startStage(stage);
        onShow();
    }

    private void initScrollPane() {
        scrollPane.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        scrollPane.setContent(new Group(canvas));
        scrollPane.setPannable(true);
        layout.setCenter(scrollPane);
    }

    private void startStage(Stage stage) {
        Scene scene = new Scene(layout, sceneInfo.getHeight()*PLATFORM_SCALE, sceneInfo.getWidth()*PLATFORM_SCALE);
        addShortcuts(scene);
        stage.setTitle(sceneInfo.getTitle());
        stage.setMinWidth(sceneInfo.getMinSceneWith() * PLATFORM_SCALE);
        stage.setScene(scene);
        stage.show();
    }

    protected abstract void beforeStart(Stage stage);

    protected abstract T getController();

    protected abstract ToolBar initToolbar();

    protected abstract void addShortcuts(Scene scene);

    protected abstract SceneInfo initSceneInfo();

    protected abstract void onShow();

    public T getAnimationController() {
        return animationController;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public BorderPane getLayout() {
        return layout;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public Pane getCanvas() {
        return canvas;
    }

    public ToolBar getToolbar() {
        return toolbar;
    }
}
