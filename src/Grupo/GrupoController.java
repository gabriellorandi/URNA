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
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtSigla;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    List<Grupo> grupos;

    @FXML private TableView<Grupo> tableView;
    @FXML private TableColumn<Grupo, Long> grupoId;
    @FXML private TableColumn<Grupo, String> grupoNome;
    @FXML private TableColumn<Grupo, String> grupoSigla;

    @FXML
    public void initialize() {
        grupos = new ArrayList<>();
        tableView.getItems().addAll(grupos);

        grupoId.setCellValueFactory(new PropertyValueFactory<>("grupoId"));
        grupoNome.setCellValueFactory(new PropertyValueFactory<>("grupoNome"));
        grupoSigla.setCellValueFactory(new PropertyValueFactory<>("grupoSigla"));

    }


    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void cadastrarGrupo( ) {

        Grupo grupo = new Grupo();
        grupo.setId( Long.parseLong(txtId.getText()));
        grupo.setNome( txtNome.getText() );
        grupo.setSigla( txtSigla.getText() );

        grupos.add(grupo);

        tableView.refresh();

    }
}
