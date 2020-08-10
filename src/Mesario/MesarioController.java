package Mesario;

import Candidato.Candidato;
import Eleitor.Eleitor;
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

public class MesarioController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    List<Mesario> mesarios;


    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    public List<Mesario> buscarEleitor(String busca) {

        List<Mesario> buscarCandidatos = new ArrayList<>();
        for (Mesario mesario : mesarios) {
            if(mesario.getNome().contains(busca) )
                buscarCandidatos.add(mesario);
        }

        return buscarCandidatos;

    }

    public void cadastrarCandidato(Mesario mesario) {

        mesarios.add(mesario);

    }


    public Mesario selecionarCandidatos(Long id) {

        List<Eleitor> buscaEleitores = new ArrayList<>();
        for (Mesario mesario : mesarios) {
            if(mesario.getId() == id ){
                return mesario;
            }
        }
        return null;
    }

    public void removerCandidato(Long id) {

        for (Mesario mesario : mesarios) {
            if(mesario.getId() == id ){
                mesarios.remove(mesario);
            }
        }
    }

}