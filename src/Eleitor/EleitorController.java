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
    @FXML TextField txtRegistro;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnCadastrar;
    @FXML Button btnCancel;

    @FXML private TableView<Eleitor> tableView;
    @FXML private TableColumn<Eleitor, Long> eleitorId;
    @FXML private TableColumn<Eleitor, String> eleitorNome;
    @FXML private TableColumn<Eleitor, Long> eleitorCPF;

    List<Eleitor> eleitores;
    EleitorDAO eleitorDAO;


    @FXML
    public void initialize() {

        eleitorDAO = new EleitorDAO();
        eleitores = eleitorDAO.selecionarEleitores();

        eleitorId.setCellValueFactory(new PropertyValueFactory<Eleitor,Long>("id"));
        eleitorNome.setCellValueFactory(new PropertyValueFactory<Eleitor,String>("nome"));
        eleitorCPF.setCellValueFactory(new PropertyValueFactory<Eleitor,Long>("cpf"));

        tableView.getItems().addAll(eleitores);
        tableView.refresh();

    }

    public void atualizarEleitor() {

        Eleitor eleitor = buscarEleitor();

        if(eleitor != null) {
            eleitor.setId( Long.parseLong(txtId.getText()));
            eleitor.setCpf( Long.parseLong(txtCpf.getText()) );
            eleitor.setNome( txtNome.getText() );

            tableView.refresh();

        }

    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public Eleitor buscarEleitor() {

        Eleitor eleitor = tableView.getSelectionModel().getSelectedItem();

        if(eleitor != null) {
            for (Eleitor e : eleitores) {
                if(eleitor.equals(e) ) {
                    return eleitor;
                }
            }
        }

        return null;
    }

    public void cadastrarEleitor() {

        Eleitor eleitor = new Eleitor();
        eleitor.setId( Long.parseLong(txtId.getText()) );
        eleitor.setCpf( Long.parseLong(txtCpf.getText()) );
        eleitor.setNome( txtNome.getText() );

        if(!contemEleitor(eleitor)) {

            eleitor = eleitorDAO.cadastrarEleitor(eleitor);
            eleitores.add(eleitor);
            tableView.getItems().setAll(eleitores);
            tableView.refresh();
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


    public Boolean contemEleitor(Eleitor eleitor) {

        for(Eleitor e : eleitores) {
            if(e.equals(eleitor))
                return true;
        }
        return false;
    }
}
