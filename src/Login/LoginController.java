package Login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController extends Application {

    @FXML
    Button btnLogin;

    @FXML
    ImageView imageView;

    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;

    @Override public void start(Stage stage) throws Exception {
        Pane sceneGraph = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Scene scene = new Scene(sceneGraph, WIDTH, HEIGHT);

        stage.setTitle("URNA");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void login(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnLogin.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
