package Eleicao.Controle;

import Cargo.Cargo;
import Cargo.CargoDAO;
import Eleicao.Eleicao;
import Eleitor.Eleitor;
import Eleitor.EleitorDAO;
import Mesario.Mesario;
import Secao.Secao;
import Voto.VotoController;
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

import java.util.List;

public class ControleEleitorController {

    @FXML private TableView<Eleitor> tableView;
    @FXML private TableColumn<Eleitor, Long> eleitorId;
    @FXML private TableColumn<Eleitor, String> eleitorNome;
    @FXML private TableColumn<Eleitor, String> eleitorCpf;

    private List<Eleitor> eleitores;
    private EleitorDAO eleitorDAO;
    private CargoDAO cargoDAO;

    @FXML private TextField txtBuscar;
    @FXML private Button btnBusca;
    @FXML private Button btnEncerrar;

    private Mesario mesario;
    private Eleicao eleicao;
    private Secao secao;
    private List<Cargo> cargos;

    @FXML
    public void initialize() {

        eleitorDAO = new EleitorDAO();
        cargoDAO = new CargoDAO();

        eleitorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleitorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        eleitorCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

    }

    public void load(Mesario mesario, Eleicao eleicao,Secao secao) {
        setMesario(mesario);
        setEleicao(eleicao);
        setSecao(secao);

        eleitores = eleitorDAO.selecionarEleitoresEleicao(secao);
        cargos = cargoDAO.selecionarCargosEleicao(eleicao);

        tableView.getItems().addAll(eleitores);
        tableView.refresh();
    }

    public void buscarEleitor() {

        eleitores = eleitorDAO.selecionarEleitoresEleicao(secao,txtBuscar.getText());

        tableView.getItems().setAll(eleitores);
        tableView.refresh();

    }

    public void encerrar() {
        Stage stage = (Stage)btnEncerrar.getScene().getWindow();
        stage.close();
    }

    public void autorizarVotar() throws Exception {

        Eleitor eleitor = tableView.getSelectionModel().getSelectedItem();

        if(eleitor != null) {

            for(Cargo cargo : cargos) {

                if(eleitor.getGrupo() == null ||cargo.getGrupo().getNome() == null  ||  eleitor.getGrupo().getId().equals(cargo.getGrupo().getId())) {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation((getClass().getResource("../../Voto/Voto.fxml")));
                    Parent parent = loader.load();

                    VotoController votoController = loader.getController();
                    votoController.load(eleicao,cargo,secao,eleitor);

                    Stage stage = new Stage();

                    Scene scene = new Scene(parent);
                    stage.setTitle("Votação "+cargo.getNome());
                    stage.setScene(scene);
                    stage.showAndWait();

                }
            }
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

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }
}
