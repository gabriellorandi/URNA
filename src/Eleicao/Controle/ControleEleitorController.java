package Eleicao.Controle;

import Eleicao.Eleicao;
import Eleitor.Eleitor;
import Eleitor.EleitorDAO;
import Mesario.Mesario;
import Voto.VotoController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class ControleEleitorController {

    @FXML private TableView<Eleitor> tableView;
    @FXML private TableColumn<Eleitor, Long> eleitorId;
    @FXML private TableColumn<Eleitor, String> eleitorNome;
    @FXML private TableColumn<Eleitor, String> eleitorCpf;

    private List<Eleitor> eleitores;
    private EleitorDAO eleitorDAO;

    private Mesario mesario;
    private Eleicao eleicao;


    @FXML
    public void initialize() {

        eleitorDAO = new EleitorDAO();

        eleitorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleitorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        eleitorCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));


    }

    public void load(Mesario mesario, Eleicao eleicao) {
        setMesario(mesario);
        setEleicao(eleicao);

        eleitores = eleitorDAO.selecionarEleitoresEleicao(mesario);

        tableView.getItems().addAll(eleitores);
        tableView.refresh();

    }

    public void autorizarVotar() throws Exception {

        Eleitor eleitor = tableView.getSelectionModel().getSelectedItem();

        if(eleitor != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("../../Voto/Voto.fxml")));
            Parent parent = loader.load();

            VotoController votoController = loader.getController();
            votoController.load(eleicao);

            Stage stage = new Stage();

            Scene scene = new Scene(parent);
            stage.setTitle("Votação");
            stage.setScene(scene);
            stage.show();


        }


    }

    public List<Eleitor> getEleitores() {
        return eleitores;
    }

    public void setEleitores(List<Eleitor> eleitores) {
        this.eleitores = eleitores;
    }

    public Mesario getMesario() {
        return mesario;
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;

    }


    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }
}
