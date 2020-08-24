package Voto;

import Candidato.Candidato;
import Candidato.CandidatoDAO;
import Eleicao.Eleicao;
import Eleitor.Eleitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class VotoController {

    @FXML Button btn1;
    @FXML Button btn2;
    @FXML Button btn3;
    @FXML Button btn4;
    @FXML Button btn5;
    @FXML Button btn6;
    @FXML Button btn7;
    @FXML Button btn8;
    @FXML Button btn9;

    @FXML Button btnBranco;
    @FXML Button btnConfirmar;
    @FXML Label numeroCandidato;

    private VotoDAO votoDAO;
    private CandidatoDAO candidatoDAO;

    private Eleicao eleicao;
    private Eleitor eleitor;

    private Candidato candidato;
    private String votoNumero;

    @FXML
    public void initialize() {

        votoDAO = new VotoDAO();
        candidatoDAO = new CandidatoDAO();
        votoNumero = "";

    }

    public void load(Eleicao eleicao) {
        setEleicao(eleicao);
    }


    public void digito1(ActionEvent actionEvent) {
        votoNumero += "1";
        procurarCandidato();
    }

    public void digito2(ActionEvent actionEvent) {
        votoNumero += "2";
        procurarCandidato();
    }

    public void digito3(ActionEvent actionEvent) {
        votoNumero += "3";
        procurarCandidato();
    }

    public void digito4(ActionEvent actionEvent) {
        votoNumero += "4";
        procurarCandidato();
    }

    public void digito5(ActionEvent actionEvent) {
        votoNumero += "5";
        procurarCandidato();
    }

    public void digito6(ActionEvent actionEvent) {
        votoNumero += "6";
        procurarCandidato();
    }

    public void digito7(ActionEvent actionEvent) {
        votoNumero += "7";
        procurarCandidato();
    }

    public void digito8(ActionEvent actionEvent) {
        votoNumero += "8";
        procurarCandidato();
    }

    public void digito9(ActionEvent actionEvent) {
        votoNumero += "9";
        procurarCandidato();
    }

    private void procurarCandidato() {

        candidato = candidatoDAO.procurarCandidato(eleicao,Long.parseLong(votoNumero));

    }

    public void corrige(ActionEvent actionEvent) {
        if(!votoNumero.isEmpty()){
            votoNumero.substring(0, votoNumero.length() - 1);
        }
    }

    public void votarBranco(ActionEvent actionEvent) {

        Voto v = new Voto();
        v.setEleicao(eleicao);
        v.setCandidato(null);
        v.setData(LocalDate.now());

        votoDAO.cadastrarVoto(v);

    }

    public void confirmarVoto(ActionEvent actionEvent) {

        Voto v = new Voto();
        v.setEleicao(eleicao);
        v.setCandidato(candidato);
        v.setData(LocalDate.now());

        votoDAO.cadastrarVoto(v);

    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
    }

    public Eleitor getEleitor() {
        return eleitor;
    }

    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }
}
