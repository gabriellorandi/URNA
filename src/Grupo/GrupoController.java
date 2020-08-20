package Grupo;

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

public class GrupoController {
    @FXML TextField txtNome;
    @FXML Button btnCancel;
    @FXML Button btnRemover;
    @FXML Button btnCadastrar;

    List<Grupo> grupos;
    GrupoDAO grupoDAO;

    @FXML private TableView<Grupo> tableView;
    @FXML private TableColumn<Grupo, Long> grupoId;
    @FXML private TableColumn<Grupo, String> grupoNome;

    @FXML
    public void initialize() {

        grupoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        grupoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        grupoDAO = new GrupoDAO();
        grupos = grupoDAO.selecionarGrupos();

        tableView.getItems().addAll(grupos);
        tableView.refresh();

    }


    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarGrupo( ) {

        Grupo grupo = new Grupo();
        grupo.setNome( txtNome.getText() );

        grupoDAO.cadastrarGrupo(grupo);

        grupos = grupoDAO.selecionarGrupos();

        tableView.getItems().setAll(grupos);
        tableView.refresh();

    }

    public void removerGrupo() {

        Grupo grupo = tableView.getSelectionModel().getSelectedItem();

        if(grupo != null){

            grupos.remove(grupo);
            grupoDAO.removerGrupo(grupo.getId());

            tableView.getItems().setAll(grupos);
            tableView.refresh();

        }


    }

}
