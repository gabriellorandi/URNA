package Urna;

import Login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UrnaController {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    @FXML
    Button btnCandidatos;

    @FXML
    Button btnCargos;

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
    Button btnDeslogar;

    @FXML
    public void loadCandidatos(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Candidato/Candidate.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Candidato");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadMesarios(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Mesario/Mesario.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Mesário");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadEleitores(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Eleitor/Eleitor.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Eleitor");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadChapas(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Chapa/Chapa.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Chapa");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadGrupos(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Grupo/Grupo.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Grupo");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadEleicao(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Eleicao/Eleicao.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Eleição");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadCargos(ActionEvent event) throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Cargo/Cargo.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Cargo");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deslogar(ActionEvent event) throws Exception {

        Stage stage = (Stage)btnDeslogar.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        stage = new Stage();
        Scene scene = new Scene(parent, LoginController.WIDTH, LoginController.HEIGHT);

        stage.setTitle("URNA");
        stage.setScene(scene);
        stage.show();



    }

}
