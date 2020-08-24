package Candidato;

import Cargo.Cargo;
import Cargo.CargoDAO;
import Chapa.Chapa;
import Chapa.ChapaDAO;
import Grupo.Grupo;
import Grupo.GrupoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.util.List;

public class CandidatoController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnAdd;
    @FXML Button btnCancel;
    @FXML Button btnAddEleicao;

    @FXML private TableView<Candidato> tableView;
    @FXML private TableColumn<Candidato, Long> candidatoId;
    @FXML private TableColumn<Candidato, String> candidatoNome;
    @FXML private TableColumn<Candidato, String> candidatoChapa;
    @FXML private TableColumn<Candidato, String> candidatoGrupo;
    @FXML private TableColumn<Candidato, String> candidatoCargo;


    @FXML private ComboBox cbGrupo;
    @FXML private ComboBox cbChapa;
    @FXML private ComboBox cbCargo;

    List<Candidato> candidatos;
    CandidatoDAO candidatoDAO;

    List<Cargo> cargos;
    CargoDAO cargoDAO;

    List<Chapa> chapas;
    ChapaDAO chapaDAO;

    List<Grupo> grupos;
    GrupoDAO grupoDAO;

    @FXML
    public void initialize() {

        candidatoDAO = new CandidatoDAO();
        cargoDAO = new CargoDAO();
        chapaDAO = new ChapaDAO();
        grupoDAO = new GrupoDAO();

        cargos = cargoDAO.selecionarCargos();
        chapas = chapaDAO.selecionarChapas();
        grupos = grupoDAO.selecionarGrupos();
        candidatos = candidatoDAO.selecionarCandidatos();

        candidatoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        candidatoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        candidatoChapa.setCellValueFactory(new PropertyValueFactory<>("chapaNome"));
        candidatoGrupo.setCellValueFactory(new PropertyValueFactory<>("grupoNome"));
        candidatoCargo.setCellValueFactory(new PropertyValueFactory<>("cargoNome"));

        cbCargo.getItems().setAll(cargos);
        cbChapa.getItems().setAll(chapas);
        cbGrupo.getItems().setAll(grupos);

        tableView.getItems().addAll(candidatos);
        tableView.refresh();

    }

    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarCandidato() {

        Candidato candidato = new Candidato();
        candidato.setId(Long.parseLong(txtId.getText()));
        candidato.setNome(txtNome.getText());
        candidato.setCpf(Long.parseLong(txtCpf.getText()));

        Cargo cargo = (Cargo) cbCargo.getSelectionModel().getSelectedItem();
        Grupo grupo = (Grupo) cbGrupo.getSelectionModel().getSelectedItem();
        Chapa chapa = (Chapa) cbChapa.getSelectionModel().getSelectedItem();

        candidatoDAO.cadastrarCandidato(candidato,chapa,cargo,grupo);

        candidatos = candidatoDAO.selecionarCandidatos();
        tableView.getItems().setAll(candidatos);
        tableView.refresh();

    }


    public void removerCandidato() {

        Candidato candidato = tableView.getSelectionModel().getSelectedItem();

        if(candidato != null) {

            candidatos.remove(candidato);
            candidatoDAO.removerCandidato(candidato.getId());

            tableView.getItems().setAll(candidatos);
            tableView.refresh();

        }

    }


}
