package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitialScreen {

    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;

    @FXML
    Button btnCandidatos;

    @FXML
    Button btnMesarios;

    @FXML
    Button btnEleitores;

    @FXML
    Button btnChapas;

    @FXML
    Button btnGrupos;

    @FXML
    Button btnEleicao;

    @FXML
    public void loadCandidatos(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/AddCandidate.fxml"));

        Stage stage = (Stage)btnCandidatos.getScene().getWindow();

        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Candidato");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadMesarios(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/AddMesario.fxml"));

        Stage stage = (Stage)btnCandidatos.getScene().getWindow();

        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Mesário");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadEleitores(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/AddElector.fxml"));

        Stage stage = (Stage)btnCandidatos.getScene().getWindow();

        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Eleitor");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadChapas(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/AddChapa.fxml"));

        Stage stage = (Stage)btnCandidatos.getScene().getWindow();

        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Chapa");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadGrupos(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/AddGroup.fxml"));

        Stage stage = (Stage)btnCandidatos.getScene().getWindow();

        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Grupo");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadEleicao(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../View/AddElection.fxml"));

        Stage stage = (Stage)btnCandidatos.getScene().getWindow();

        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Eleição");
        stage.setScene(scene);
        stage.show();
    }

}
