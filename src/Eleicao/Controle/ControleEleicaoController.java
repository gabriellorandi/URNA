package Eleicao.Controle;

import Candidato.Candidato;
import Candidato.CandidatoDAO;
import Eleicao.Eleicao;
import Eleicao.EleicaoDAO;
import Mesario.Mesario;
import Secao.Secao;
import Secao.SecaoDAO;
import Utils.AlertUtils;
import Utils.PdfUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.List;

public class ControleEleicaoController {

    @FXML private Button btnIniciar;
    @FXML private Button btnImprimir;

    private Mesario mesario;

    private EleicaoDAO eleicaoDAO;
    private SecaoDAO secaoDAO;
    private CandidatoDAO candidatoDAO;

    @FXML
    public void initialize() {

        eleicaoDAO = new EleicaoDAO();
        secaoDAO = new SecaoDAO();
        candidatoDAO = new CandidatoDAO();

    }

    public void load(Mesario mesario) {

        setMesario(mesario);

    }

    public void iniciarVotacao() throws Exception{

        Eleicao eleicao = eleicaoDAO.selecionarEleicaoMesario(mesario);
        Secao secao = secaoDAO.selecionarSecaoMesario(mesario);

        if(eleicao != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("../Controle/ControleEleitor.fxml")));
            Parent parent = loader.load();

            ControleEleitorController controleEleitorController = loader.getController();
            controleEleitorController.load(mesario,eleicao,secao);


            Stage stage = new Stage();

            stage.setTitle("Eleição");
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        }


    }

    public void imprimir() {

        Eleicao eleicao = eleicaoDAO.selecionarEleicaoMesario(mesario);
        Secao secao = secaoDAO.selecionarSecaoMesario(mesario);

        List<Candidato> candidatos = candidatoDAO.selecionarCandidatos(eleicao);

        PdfUtils.createPdf("C:\\Users\\Gabriel\\Desktop\\resultados.pdf","Resultados",mesario,candidatos);

        AlertUtils.alert("Resultados","O arquivo foi gerado com sucesso", Alert.AlertType.CONFIRMATION);
    }


    public Mesario getMesario() {
        return mesario;
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;

    }


}
