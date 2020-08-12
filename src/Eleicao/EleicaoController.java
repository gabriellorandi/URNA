package Eleicao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EleicaoController {
    @FXML TextField txtId;
    @FXML TextField txtHora;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    @FXML private TableView<Eleicao> tableView;
    @FXML private TableColumn<Eleicao, Long> eleicaoId;


    List<Eleicao> eleicaos;

    @FXML
    public void initialize() {
        eleicaos = new ArrayList<>();
        tableView.getItems().addAll(eleicaos);

    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void cadastrarEleicao( ) {
    }
}