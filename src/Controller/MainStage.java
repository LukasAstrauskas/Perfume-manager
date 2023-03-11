package Controller;

import Controller.JavaFxWindows.FinalMixtureWindow;
import Controller.JavaFxWindows.SceneClass;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainStage extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        SceneClass.INSTANCE.setScene(stage);
    }
}