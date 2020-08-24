package Eleicao.Controle;

import Eleitor.Eleitor;
import Login.LoginController;
import Mesario.Mesario;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ControleEleicaoController {

    @FXML private TableView<Eleitor> tableView;
    @FXML private TableColumn<Eleitor, Long> eleitorId;
    @FXML private TableColumn<Eleitor, String> eleitorNome;
    @FXML private TableColumn<Eleitor, String> eleitorCpf;

    private List<Eleitor> eleitores;
    private Mesario mesario;


    @FXML
    public void initialize() {



        eleitorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        eleitorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        eleitorCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));


    }

    public void autorizarVotar() {




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
}
