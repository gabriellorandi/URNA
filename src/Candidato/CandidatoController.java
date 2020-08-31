package Candidato;

import Cargo.Cargo;
import Cargo.CargoController;
import Cargo.CargoDAO;
import Chapa.Chapa;
import Chapa.ChapaDAO;
import Eleicao.Eleicao;
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
import java.util.List;

public class CandidatoController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnCadastrar;
    @FXML Button btnCancel;
    @FXML Button btnCargo;
    @FXML Button btnChapa;
    @FXML Button btnImportar;

    @FXML private TableView<Candidato> tableView;
    @FXML private TableColumn<Candidato, Long> candidatoId;
    @FXML private TableColumn<Candidato, String> candidatoNome;
    @FXML private TableColumn<Candidato, String> candidatoChapa;
    @FXML private TableColumn<Candidato, String> candidatoCargo;


    @FXML private ComboBox cbChapa;
    @FXML private ComboBox cbCargo;

    List<Candidato> candidatos;
    CandidatoDAO candidatoDAO;

    List<Cargo> cargos;
    CargoDAO cargoDAO;

    List<Chapa> chapas;
    ChapaDAO chapaDAO;

    Eleicao eleicao;

    @FXML
    public void initialize() {

        candidatoDAO = new CandidatoDAO();
        cargoDAO = new CargoDAO();
        chapaDAO = new ChapaDAO();

        cargos = cargoDAO.selecionarCargos();
        chapas = chapaDAO.selecionarChapas();
        candidatos = candidatoDAO.selecionarCandidatos();

        candidatoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        candidatoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        candidatoChapa.setCellValueFactory(new PropertyValueFactory<>("chapaNome"));
        candidatoCargo.setCellValueFactory(new PropertyValueFactory<>("cargoNome"));

        cbCargo.getItems().setAll(cargos);
        cbChapa.getItems().setAll(chapas);

        tableView.getItems().addAll(candidatos);
        tableView.refresh();

    }

    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarCandidato() {

        if (ValidateFields.validateNumberField(txtId.getText())
        && ValidateFields.validateTextField(txtNome.getText())
        && ValidateFields.validateTextField(txtCpf.getText())) {
            Candidato candidato = new Candidato();
            candidato.setId(Long.parseLong(txtId.getText()));
            candidato.setNome(txtNome.getText());
            candidato.setCpf(Long.parseLong(txtCpf.getText()));

            Cargo cargo = (Cargo) cbCargo.getSelectionModel().getSelectedItem();
            Chapa chapa = (Chapa) cbChapa.getSelectionModel().getSelectedItem();

            candidato.setChapa(chapa);
            candidato.setCargo(cargo);

            candidatoDAO.cadastrarCandidato(candidato,eleicao);

            candidatos = candidatoDAO.selecionarCandidatos();
            tableView.getItems().setAll(candidatos);
            tableView.refresh();
        } else {
            AlertUtils.alert("Valores incorretos!", "Os valores inseridos nos campos estão incorretos. Tente novamente.", Alert.AlertType.ERROR);
        }

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

    public void abrirChapa() throws Exception {

        Parent parent = FXMLLoader.load(getClass().getResource("../Chapa/Chapa.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Adicionar Chapa");
        stage.setScene(scene);
        stage.show();


    }

    public void abrirCargo() throws Exception  {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("../Cargo/Cargo.fxml")));
        Parent parent = loader.load();

        CargoController cargoController = loader.getController();
        cargoController.load(eleicao);


        Stage stage = new Stage();

        stage.setTitle("Adicionar Cargo");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();


    }

    public void importar() throws IOException {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());

        if(file != null) {

            List<Candidato> candidatos = ImportUtils.loadCandidato(file);

            for(Candidato candidato : candidatos) {
                candidatoDAO.cadastrarCandidato(candidato,eleicao);
            }

            candidatos = candidatoDAO.selecionarCandidatos();
            tableView.getItems().setAll(candidatos);
            tableView.refresh();

        }

    }

    public void load(Eleicao eleicao) {
        this.eleicao = eleicao;
    }
}
