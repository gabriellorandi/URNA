package Grupo;

import Eleitor.EleitorController;
import Utils.AlertUtils;
import Utils.ValidateFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("../Eleitor/Eleitor.fxml")));
        loader.load();
        loader.getController();


        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarGrupo( ) {

        if (ValidateFields.validateTextField((txtNome.getText()))) {
            Grupo grupo = new Grupo();
            grupo.setNome( txtNome.getText() );

            grupoDAO.cadastrarGrupo(grupo);

            grupos = grupoDAO.selecionarGrupos();

            tableView.getItems().setAll(grupos);
            txtNome.clear();
            tableView.refresh();
        } else {
            AlertUtils.alert("Valores incorretos!", "Os valores inseridos nos campos estão incorretos. Tente novamente.", Alert.AlertType.ERROR);
        }
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
