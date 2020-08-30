package Eleitor;

import Eleicao.Eleicao;
import Grupo.Grupo;
import Grupo.GrupoDAO;
import Utils.AlertUtils;
import Utils.ImportUtils;
import Utils.ValidateFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EleitorController {

    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnCadastrar;
    @FXML Button btnCancel;
    @FXML Button btnGrupo;

    @FXML private TableView<Eleitor> tableView;
    @FXML private TableColumn<Eleitor, Long> eleitorId;
    @FXML private TableColumn<Eleitor, String> eleitorNome;
    @FXML private TableColumn<Eleitor, Long> eleitorCPF;
    @FXML private TableColumn<Eleitor, String> eleitorGrupo;

    @FXML private ComboBox cbGrupo;


    List<Eleitor> eleitores;
    EleitorDAO eleitorDAO;

    List<Grupo> grupos;
    GrupoDAO grupoDAO;

    private Eleicao eleicao;

    @FXML
    public void initialize() {

        eleitorDAO = new EleitorDAO();
        eleitores = eleitorDAO.selecionarEleitores();

        grupoDAO = new GrupoDAO();
        grupos = grupoDAO.selecionarGrupos();

        cbGrupo.getItems().addAll(grupos);

        eleitorId.setCellValueFactory(new PropertyValueFactory<Eleitor,Long>("id"));
        eleitorNome.setCellValueFactory(new PropertyValueFactory<Eleitor,String>("nome"));
        eleitorCPF.setCellValueFactory(new PropertyValueFactory<Eleitor,Long>("cpf"));
        eleitorGrupo.setCellValueFactory(new PropertyValueFactory<Eleitor,String>("grupoNome"));

        tableView.getItems().addAll(eleitores);
        tableView.refresh();

    }


    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }


    public void cadastrarEleitor() {

        if(ValidateFields.validateNumberField(txtId.getText())
        && ValidateFields.validateNumberField(txtCpf.getText())
        && ValidateFields.validateTextField(txtNome.getText())) {
            Eleitor eleitor = new Eleitor();
            eleitor.setId( Long.parseLong(txtId.getText()) );
            eleitor.setCpf( Long.parseLong(txtCpf.getText()) );
            eleitor.setNome( txtNome.getText() );

            if(!contemEleitor(eleitor)) {

                eleitor = eleitorDAO.cadastrarEleitor(eleitor,eleicao);
                eleitores.add(eleitor);
                tableView.getItems().setAll(eleitores);
                tableView.refresh();
            }
        } else {
            AlertUtils.alert("Valores incorretos!", "Os valores inseridos nos campos estão incorretos. Tente novamente.", Alert.AlertType.ERROR);
        }
    }

    public void removerEleitor() {

        Eleitor eleitor = tableView.getSelectionModel().getSelectedItem();

        if(eleitor != null) {
            eleitores.remove(eleitor);

            eleitorDAO.removerEleitor(eleitor.getId());

            tableView.getItems().setAll(eleitores);
            tableView.refresh();
        }

    }

    public void abrirGrupo() throws Exception {


        Parent parent = FXMLLoader.load(getClass().getResource("../Grupo/Grupo.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Adicionar Grupo");
        stage.setScene(scene);
        stage.show();

    }


    public Boolean contemEleitor(Eleitor eleitor) {

        for(Eleitor e : eleitores) {
            if(e.equals(eleitor))
                return true;
        }
        return false;
    }

    public void importar() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());

        if(file != null) {

            List<Eleitor> eleitores = ImportUtils.loadEleitores(file);

            for(Eleitor eleitor : eleitores) {
                eleitorDAO.cadastrarEleitor(eleitor,eleicao);
            }

            eleitores = eleitorDAO.selecionarEleitores();
            tableView.getItems().setAll(eleitores);
            tableView.refresh();

        }

    }

    public void load(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

}
