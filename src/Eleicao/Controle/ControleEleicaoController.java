package Eleicao.Controle;

import Eleicao.Eleicao;
import Eleicao.EleicaoDAO;
import Mesario.Mesario;
import Secao.Secao;
import Secao.SecaoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleEleicaoController {

    @FXML private Button btnIniciar;

    private Mesario mesario;

    private EleicaoDAO eleicaoDAO;
    private SecaoDAO secaoDAO;

    @FXML
    public void initialize() {

        eleicaoDAO = new EleicaoDAO();
        secaoDAO = new SecaoDAO();

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


            Stage stage = (Stage)btnIniciar.getScene().getWindow();

            stage.setTitle("Eleição");
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        }


    }


    public Mesario getMesario() {
        return mesario;
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;

    }


}
