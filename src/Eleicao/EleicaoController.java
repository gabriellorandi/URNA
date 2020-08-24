package Eleicao;

import Candidato.Candidato;
import Candidato.CandidatoDAO;
import Secao.SecaoDAO;
import Secao.Secao;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EleicaoController {
    @FXML TextField txtId;
    @FXML TextField txtHora;
    @FXML Button btnAdd;
    @FXML Button btnAddCandidatoEleicao;
    @FXML Button btnCancel;
    @FXML Button btnSecao;
    @FXML DatePicker dateEleicao;

    @FXML CheckComboBox<Candidato> ccbCandidatos;
    @FXML CheckComboBox<Secao> ccbSecoes;


    @FXML private TableView<Eleicao> tableView;
    @FXML private TableColumn<Eleicao, Long> eleicaoId;
    @FXML private TableColumn<Eleicao, LocalDate> eleicaoDia;
    @FXML private TableColumn<Eleicao, Boolean> eleicaoCandidato;
    @FXML private TableColumn<Eleicao, Boolean> eleicaoSecoes;

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    private EleicaoDAO eleicaoDAO;
    private CandidatoDAO candidatoDAO;
    private SecaoDAO secaoDAO;

    List<Eleicao> eleicaos;

    List<Candidato> candidatos;
    List<Secao> secaos;

    List<Candidato> candidatosSelecionados;
    List<Secao> secoesSelecionados;

    @FXML
    public void initialize() {

        eleicaos = new ArrayList<>();

        eleicaoDAO = new EleicaoDAO();
        candidatoDAO = new CandidatoDAO();

        eleicaos = eleicaoDAO.selecionarEleicoes();
        candidatos = candidatoDAO.selecionarCandidatos();
        secaos = secaoDAO.selecionarSecoes();

        eleicaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleicaoDia.setCellValueFactory(new PropertyValueFactory<>("nome"));
        eleicaoCandidato.setCellValueFactory(new PropertyValueFactory<>("haCandidatos"));
        eleicaoSecoes.setCellValueFactory(new PropertyValueFactory<>("haSecoes"));

        tableView.getItems().addAll(eleicaos);

        ccbCandidatos.getItems().addAll(candidatos);
        ccbSecoes.getItems().addAll(secaos);

        ccbCandidatos.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Candidato>() {
            public void onChanged(ListChangeListener.Change<? extends Candidato> c) {
                candidatosSelecionados = ccbCandidatos.getCheckModel().getCheckedItems();
            }
        });

        ccbSecoes.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Secao>() {
            public void onChanged(ListChangeListener.Change<? extends Secao> s) {
                secoesSelecionados = ccbSecoes.getCheckModel().getCheckedItems();
            }
        });
    }

    public void close(ActionEvent event) throws Exception{


        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarEleicao( ) {


        Eleicao eleicao = new Eleicao();

        eleicao.setDia( dateEleicao.getValue() );
        eleicao.setCandidatos(candidatosSelecionados);
        eleicao.setSecoes(secoesSelecionados);

        eleicaoDAO.cadastrarEleicao(eleicao);

        eleicaos = eleicaoDAO.selecionarEleicoes();
        tableView.getItems().setAll(eleicaos);
        tableView.refresh();

    }

    public void abrirSecao()  throws Exception  {

        Parent parent = FXMLLoader.load(getClass().getResource("../Secao/Secao.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(parent,WIDTH,HEIGHT);
        stage.setTitle("Adicionar Seção");
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

}