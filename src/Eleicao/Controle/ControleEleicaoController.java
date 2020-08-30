package Eleicao.Controle;

import Eleicao.Eleicao;
import Eleicao.EleicaoDAO;
import Mesario.Mesario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ControleEleicaoController {

    @FXML private Button btnIniciar;

    @FXML private TableView<Eleicao> tableView;
    @FXML private TableColumn<Eleicao, Long> eleicaoId;
    @FXML private TableColumn<Eleicao, LocalDate> eleicaoDia;

    private List<Eleicao> eleicoes;
    private EleicaoDAO eleicaoDAO;

    private Mesario mesario;


    @FXML
    public void initialize() {

        eleicaoDAO = new EleicaoDAO();

        eleicaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleicaoDia.setCellValueFactory(new PropertyValueFactory<>("dia"));


    }

    public void load(Mesario mesario) {

        setMesario(mesario);


        tableView.getItems().addAll(eleicoes);
        tableView.refresh();

    }

    public void iniciarVotacao() throws Exception{

        Eleicao eleicao = tableView.getSelectionModel().getSelectedItem();

        if(eleicao != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("../Controle/ControleEleitor.fxml")));
            Parent parent = loader.load();

            ControleEleitorController controleEleitorController = loader.getController();
            controleEleitorController.load(mesario,eleicao);


            Stage stage = (Stage)btnIniciar.getScene().getWindow();

            stage.setTitle("Eleição");
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        }


    }


    public Mesario getMesario() {
        return mesario;
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;

    }


}
