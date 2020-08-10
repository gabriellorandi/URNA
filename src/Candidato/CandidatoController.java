package Candidato;

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

public class CandidatoController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    List<Candidato> candidatos;

    public CandidatoController() {

        candidatos = new ArrayList<>();

    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public List<Candidato> buscarEleitor(String busca) {

        List<Candidato> buscarCandidatos = new ArrayList<>();
        for (Candidato candidato : candidatos) {
            if(candidato.getNome().contains(busca) )
                buscarCandidatos.add(candidato);
        }

        return buscarCandidatos;

    }

    public void cadastrarCandidato(Candidato candidato) {

        candidatos.add(candidato);

    }


    public Candidato selecionarCandidatos(Long id) {

        List<Eleitor> buscaEleitores = new ArrayList<>();
        for (Candidato candidato : candidatos) {
            if(candidato.getId() == id ){
                return candidato;
            }
        }
        return null;
    }

    public void removerCandidato(Long id) {

        for (Candidato candidato : candidatos) {
            if(candidato.getId() == id ){
                candidatos.remove(candidato);
            }
        }
    }


}
