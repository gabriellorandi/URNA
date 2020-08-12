package Eleitor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EleitorController {

    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnCadastrar;
    @FXML Button btnCancel;

    @FXML private TableView<Eleitor> tableView;
    @FXML private TableColumn<Eleitor, Long> eleitorId;
    @FXML private TableColumn<Eleitor, String> eleitorNome;
    @FXML private TableColumn<Eleitor, Long> eleitorCPF;

    List<Eleitor> eleitores;


    @FXML
    public void initialize() {
        eleitores = new ArrayList<>();
        tableView.getItems().addAll(eleitores);

        eleitorId.setCellValueFactory(new PropertyValueFactory<>("eleitorId"));
        eleitorNome.setCellValueFactory(new PropertyValueFactory<>("eleitorNome"));
        eleitorCPF.setCellValueFactory(new PropertyValueFactory<>("eleitorCpf"));


    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public List<Eleitor> buscarEleitor(String busca) {

        List<Eleitor> buscaEleitores = new ArrayList<>();
        for (Eleitor eleitor : eleitores) {
            if(eleitor.getNome().contains(busca) )
                buscaEleitores.add(eleitor);
        }

        return buscaEleitores;

    }

    public void cadastrarEleitor() {

        Eleitor eleitor = new Eleitor();
        eleitor.setId( Long.parseLong(txtId.getText()));
        eleitor.setCpf( Long.parseLong(txtCpf.getText()) );
        eleitor.setNome( txtNome.getText() );

        eleitores.add(eleitor);

        tableView.refresh();

    }


    public Eleitor selecionarEleitor(Long id) {

        List<Eleitor> buscaEleitores = new ArrayList<>();
        for (Eleitor eleitor : eleitores) {
            if(eleitor.getId() == id ){
                return eleitor;
            }
        }
        return null;
    }

    public void removerEleitor(Long id) {

        for (Eleitor eleitor : eleitores) {
            if(eleitor.getId() == id ){
                eleitores.remove(eleitor);
            }
        }
    }
}
