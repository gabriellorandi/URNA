package Eleitor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EleitorController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnAdd;
    @FXML Button btnCancel;
    @FXML TextField buscar;

    List<Eleitor> eleitores;

    public EleitorController() {

        eleitores = new ArrayList<>();

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

    public void cadastrarEleitor(Eleitor eleitor) {

        eleitores.add(eleitor);

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
