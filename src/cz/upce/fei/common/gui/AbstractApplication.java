package cz.upce.fei.common.gui;

import cz.commons.utils.handlers.ZoomHandler;
import cz.upce.fei.common.core.Controller;
import cz.upce.fei.common.gui.utils.SceneInfo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    private final StackPane stackPane = new StackPane();
    private final ZoomHandler zoomHandler = new ZoomHandler(scrollPane, canvas);

    @Override
    public void start(Stage stage) throws Exception {
        beforeStart(stage);
        sceneInfo = initSceneInfo();

        toolbar= initToolbar();
        layout.setTop(toolbar);
        initScrollPane();

        startStage(stage);
        animationController = getController();
        onShow();
    }

    private void initScrollPane() {
        canvas.setOnScroll(zoomHandler);
        scrollPane.setOnScroll(zoomHandler);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        scrollPane.setContent(new Group(canvas));
        scrollPane.setPannable(true);
        stackPane.getChildren().addAll(scrollPane);
        layout.setCenter(stackPane);
    }

    private void startStage(Stage stage) {
        Scene scene = new Scene(layout, sceneInfo.getHeight(), sceneInfo.getWidth());
        addShortcuts(scene);
        stage.setTitle(sceneInfo.getTitle());
        stage.setMinWidth(sceneInfo.getMinSceneWith());
        stage.setScene(scene);
        stage.show();
        scrollPane.setHvalue(0.5);
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

    public StackPane getStackPane() {
        return stackPane;
    }

    public Pane getCanvas() {
        return canvas;
    }

    public ToolBar getToolbar() {
        return toolbar;
    }
}
