package Login;

import Eleicao.Controle.ControleEleicaoController;
import Eleicao.Controle.ControleEleitorController;
import Mesario.Mesario;
import Mesario.MesarioDAO;
import Utils.AlertUtils;
import Utils.PostgreSQLJDBC;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController extends Application {


    @FXML
    TextField txtLogin;

    @FXML
    TextField txtSenha;

    @FXML
    Button btnLogin;

    @FXML
    ImageView imageView;

    private Mesario mesario;
    private MesarioDAO mesarioDAO = new MesarioDAO();

    public static final int HEIGHT = 400;
    public static final int WIDTH = 600;

    public static final int ADMIN_HEIGHT = 500;
    public static final int ADMIN_WIDTH = 810;

    @Override public void start(Stage stage) throws Exception {
        Pane sceneGraph = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Scene scene = new Scene(sceneGraph, WIDTH, HEIGHT);

        stage.setTitle("URNA");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void login(ActionEvent event) throws Exception {

        PostgreSQLJDBC bancoDados = new PostgreSQLJDBC();

        bancoDados.conectar();

        Long mesarioId = mesarioDAO.getLogin(txtLogin.getText(),txtSenha.getText(),false);

        if(mesarioId!=null) {

            mesario = mesarioDAO.selecionarMesario(mesarioId);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("../Eleicao/Controle/ControleEleicao.fxml")));
            Parent parent = loader.load();

            ControleEleicaoController controleEleicaoController = loader.getController();
            controleEleicaoController.load(mesario);

            Stage stage = (Stage)btnLogin.getScene().getWindow();

            stage.setTitle("Eleição");
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

        } else if(isAdmin()) {

            Parent parent = FXMLLoader.load(getClass().getResource("../Eleicao/Eleicao.fxml"));

            Stage stage = (Stage)btnLogin.getScene().getWindow();
            Scene scene = new Scene(parent,ADMIN_WIDTH,ADMIN_HEIGHT);
            stage.setTitle("Adicionar Eleição");
            stage.setScene(scene);
            stage.show();

        } else {

            AlertUtils.alert("Login","Senha ou Usuário inválidos!",Alert.AlertType.WARNING);

        }

    }


    private boolean isAdmin() {
        return mesarioDAO.getLogin(txtLogin.getText(),txtSenha.getText(),true)!=null;
    }

    public Mesario getMesario() {
        return mesario;
    }

    public void setMesario(Mesario mesario) {
        this.mesario = mesario;
    }
}
