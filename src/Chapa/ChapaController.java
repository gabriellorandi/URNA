package Chapa;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ChapaController {
    @FXML TextField txtId;
    @FXML TextField txtSigla;
    @FXML TextField txtNome;
    @FXML Button btnCadastrar;
    @FXML Button btnCancel;
    @FXML Button btnRemove;

    @FXML private TableView<Chapa> tableView;
    @FXML private TableColumn<Chapa, Long> chapaId;
    @FXML private TableColumn<Chapa, String> chapaNome;
    @FXML private TableColumn<Chapa, String> chapaSigla;


    List<Chapa> chapas;
    ChapaDAO chapaDAO;

    @FXML
    public void initialize() {

        chapaDAO = new ChapaDAO();

        chapaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        chapaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        chapaSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));

        chapas = chapaDAO.selecionarChapas();
        tableView.getItems().addAll(chapas);
        tableView.refresh();
    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void cadastrarChapa() {

        Chapa chapa = new Chapa();
        chapa.setSigla( txtSigla.getText() );
        chapa.setNome( txtNome.getText());

        chapaDAO.cadastrarChapa(chapa);

        chapas = chapaDAO.selecionarChapas();

        tableView.getItems().setAll(chapas);
        tableView.refresh();


    }

    public void removerChapa() {

        Chapa chapa = tableView.getSelectionModel().getSelectedItem();

        if(chapa != null){

            chapas.remove(chapa);
            chapaDAO.removerChapa(chapa.getId());

            tableView.getItems().setAll(chapas);
            tableView.refresh();

        }


    }


}