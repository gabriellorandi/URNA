package Mesario;

import Eleitor.Eleitor;
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

public class MesarioController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    @FXML private TableView<Mesario> tableView;
    @FXML private TableColumn<Mesario, Long> mesarioId;
    @FXML private TableColumn<Mesario, String> mesarioNome;

    List<Mesario> mesarios;

    @FXML
    public void initialize() {
        mesarios = new ArrayList<>();
        tableView.getItems().addAll(mesarios);

        mesarioId.setCellValueFactory(new PropertyValueFactory<>("mesarioId"));
        mesarioNome.setCellValueFactory(new PropertyValueFactory<>("mesarioNome"));
    }

    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }


    public List<Mesario> buscarMesario(String busca) {

        List<Mesario> buscarCandidatos = new ArrayList<>();
        for (Mesario mesario : mesarios) {
            if(mesario.getNome().contains(busca) )
                buscarCandidatos.add(mesario);
        }

        return buscarCandidatos;

    }

    public void cadastrarMesario() {

        Mesario mesario = new Mesario();
        mesario.setId( Long.parseLong( txtId.getText() ) );
        mesario.setNome( txtNome.getText() );

        mesarios.add(mesario);

    }


    public Mesario selecionarCandidatos(Long id) {

        List<Eleitor> buscaEleitores = new ArrayList<>();
        for (Mesario mesario : mesarios) {
            if(mesario.getId() == id ){
                return mesario;
            }
        }
        return null;
    }

    public void removerCandidato(Long id) {

        for (Mesario mesario : mesarios) {
            if(mesario.getId() == id ){
                mesarios.remove(mesario);
            }
        }
    }

}