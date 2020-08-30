package Eleicao;

import Candidato.CandidatoController;
import Eleitor.EleitorController;
import Login.LoginController;
import Mesario.MesarioController;
import Secao.SecaoController;
import Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EleicaoController {
    @FXML TextField txtId;
    @FXML TextField txtHora;
    @FXML Button btnAdd;
    @FXML Button btnCancel;
    @FXML Button btnSecao;
    @FXML DatePicker dateEleicao;
    @FXML Button btnEleitores;
    @FXML Button btnMesario;
    @FXML Button btnCandidato;
    @FXML Button btnDeslogar;

    @FXML private TableView<Eleicao> tableView;
    @FXML private TableColumn<Eleicao, Long> eleicaoId;
    @FXML private TableColumn<Eleicao, LocalDate> eleicaoDia;

    private EleicaoDAO eleicaoDAO;

    List<Eleicao> eleicaos;


    @FXML
    public void initialize() {

        eleicaos = new ArrayList<>();

        eleicaoDAO = new EleicaoDAO();

        eleicaos = eleicaoDAO.selecionarEleicoes();

        eleicaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleicaoDia.setCellValueFactory(new PropertyValueFactory<>("dia"));

        tableView.getItems().addAll(eleicaos);
    }

    public void close(ActionEvent event) throws Exception{
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarEleicao( ) {

        Eleicao eleicao = new Eleicao();

        eleicao.setDia( dateEleicao.getValue() );

        eleicaoDAO.cadastrarEleicao(eleicao);

        eleicaos = eleicaoDAO.selecionarEleicoes();
        tableView.getItems().setAll(eleicaos);
        tableView.refresh();

    }

    public void abrirSecao()  throws Exception  {

        Eleicao eleicao = tableView.getSelectionModel().getSelectedItem();

        if(eleicao==null) {
            AlertUtils.alert("Atenção","Nenhuma eleição selecionada", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("../Secao/Secao.fxml")));
        Parent parent = loader.load();

        SecaoController secaoController = loader.getController();
        secaoController.load(eleicao);


        Stage stage = new Stage();

        stage.setTitle("Adicionar Secao");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }


    public void abrirEleitores()  throws Exception  {

        Eleicao eleicao = tableView.getSelectionModel().getSelectedItem();


        if(eleicao==null) {
            AlertUtils.alert("Atenção","Nenhuma eleição selecionada", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("../Eleitor/Eleitor.fxml")));
        Parent parent = loader.load();

        EleitorController eleitorController = loader.getController();
        eleitorController.load(eleicao);


        Stage stage = new Stage();

        stage.setTitle("Adicionar Eleitor");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public void abrirMesario() throws Exception {

        Eleicao eleicao = tableView.getSelectionModel().getSelectedItem();


        if(eleicao==null) {
            AlertUtils.alert("Atenção","Nenhuma eleição selecionada", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("../Mesario/Mesario.fxml")));
        Parent parent = loader.load();

        MesarioController mesarioController = loader.getController();
        mesarioController.load(eleicao);


        Stage stage = new Stage();

        stage.setTitle("Adicionar Mesario");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public void abrirCandidato() throws Exception {

        Eleicao eleicao = tableView.getSelectionModel().getSelectedItem();


        if(eleicao==null) {
            AlertUtils.alert("Atenção","Nenhuma eleição selecionada", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("../Candidato/Candidate.fxml")));
        Parent parent = loader.load();

        CandidatoController candidatoController = loader.getController();
        candidatoController.load(eleicao);


        Stage stage = new Stage();

        stage.setTitle("Adicionar Candidato");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public void removerEleicao() throws Exception{

        Eleicao eleicao = tableView.getSelectionModel().getSelectedItem();

        if(eleicao != null){

            eleicaos.remove(eleicao);
            eleicaoDAO.removerEleicao(eleicao.getId());

            tableView.getItems().setAll(eleicaos);
            tableView.refresh();

        }


    }

    @FXML
    public void deslogar(ActionEvent event) throws Exception {

        Stage stage = (Stage)btnDeslogar.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        stage = new Stage();
        Scene scene = new Scene(parent, LoginController.WIDTH, LoginController.HEIGHT);

        stage.setTitle("URNA");
        stage.setScene(scene);
        stage.show();



    }

}