package Urna;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UrnaMain extends Application {

    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;

    @Override public void start(Stage stage) throws Exception {
        Pane sceneGraph = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Scene scene = new Scene(sceneGraph, WIDTH, HEIGHT);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
