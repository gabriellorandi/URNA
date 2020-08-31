package Voto;

import Candidato.Candidato;
import Cargo.Cargo;
import Candidato.CandidatoDAO;
import Eleicao.Eleicao;
import Eleitor.Eleitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;

public class VotoController {

    @FXML Button btn0;
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
    @FXML Label lblNumeroCandidato;
    @FXML Label lblNomeCandidato;
    @FXML Label lblPartidoCandidato;
    @FXML Label lblPartidoCandidato1;


    private VotoDAO votoDAO;
    private CandidatoDAO candidatoDAO;

    private Eleicao eleicao;
    private Eleitor eleitor;

    private Candidato candidato;
    private Cargo cargo;

    private String votoNumero;

    @FXML
    public void initialize() {

        votoDAO = new VotoDAO();
        candidatoDAO = new CandidatoDAO();
        votoNumero = "";

    }

    public void load(Eleicao eleicao, Cargo cargo) {
        this.eleicao = eleicao;
        this.cargo = cargo;
    }


    public void digito1(ActionEvent actionEvent) {
        votoNumero += "1";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito2(ActionEvent actionEvent) {
        votoNumero += "2";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito3(ActionEvent actionEvent) {
        votoNumero += "3";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito4(ActionEvent actionEvent) {
        votoNumero += "4";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito5(ActionEvent actionEvent) {
        votoNumero += "5";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito6(ActionEvent actionEvent) {
        votoNumero += "6";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito7(ActionEvent actionEvent) {
        votoNumero += "7";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito8(ActionEvent actionEvent) {
        votoNumero += "8";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    public void digito9(ActionEvent actionEvent) {
        votoNumero += "9";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }
    public void digito0(ActionEvent actionEvent) {
        votoNumero += "0";
        lblNumeroCandidato.setText(votoNumero);
        procurarCandidato();
    }

    private void procurarCandidato() {

        candidato = candidatoDAO.procurarCandidato(eleicao,cargo,Long.parseLong(votoNumero));
        if(candidato != null) {
            lblNomeCandidato.setText(candidato.getNome());
            lblPartidoCandidato1.setText(candidato.getCargoNome());
        } else {
            lblNomeCandidato.setText("");
            lblPartidoCandidato.setText("");
            lblPartidoCandidato1.setText("");
        }
    }

    public void corrige(ActionEvent actionEvent) {
        if(!votoNumero.isEmpty()){
            votoNumero.substring(0, votoNumero.length() - 1);
            lblNumeroCandidato.setText(votoNumero);
        }
    }

    public void votarBranco(ActionEvent actionEvent) {

        Voto v = new Voto();
        v.setEleicao(eleicao);
        v.setCandidato(null);
        v.setData(LocalDate.now());

        votoDAO.cadastrarVoto(v);

        Stage stage = (Stage)btnBranco.getScene().getWindow();
        stage.close();

    }

    public void confirmarVoto(ActionEvent actionEvent) {

        Voto v = new Voto();
        v.setEleicao(eleicao);
        v.setCandidato(candidato);
        v.setData(LocalDate.now());

        votoDAO.cadastrarVoto(v);

        Stage stage = (Stage)btnConfirmar.getScene().getWindow();
        stage.close();

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
