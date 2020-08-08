package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Login {

    @FXML
    Button btnLogin;

    @FXML
    public void login(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/InitialScreen.fxml"));

        Stage stage = (Stage)btnLogin.getScene().getWindow();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
