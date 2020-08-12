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
    @FXML TextField txtNome;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    @FXML private TableView<Chapa> tableView;
    @FXML private TableColumn<Chapa, Long> chapaId;
    @FXML private TableColumn<Chapa, String> chapaNome;

    List<Chapa> chapas;

    @FXML
    public void initialize() {
        chapas = new ArrayList<>();
        tableView.getItems().addAll(chapas);

        chapaId.setCellValueFactory(new PropertyValueFactory<>("chapaId"));
        chapaNome.setCellValueFactory(new PropertyValueFactory<>("chapaNome"));
    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public List<Chapa> buscarChapa(String busca) {

        List<Chapa> buscarCandidatos = new ArrayList<>();
        for (Chapa chapa : chapas) {
            if(chapa.getNome().contains(busca) )
                buscarCandidatos.add(chapa);
        }

        return buscarCandidatos;

    }

    public void cadastrarChapa() {

        Chapa chapa = new Chapa();
        chapa.setId( Long.parseLong(txtId.getText()) );
        chapa.setNome( txtNome.getText());

        chapas.add(chapa);

    }


    public Chapa selecionarChapa(Long id) {

        List<Chapa> buscarChapas = new ArrayList<>();
        for (Chapa chapa : chapas) {
            if(chapa.getId() == id ){
                return chapa;
            }
        }
        return null;
    }

    public void removerChapa(Long id) {

        for (Chapa chapa : chapas) {
            if(chapa.getId() == id ){
                chapas.remove(chapa);
            }
        }
    }


}