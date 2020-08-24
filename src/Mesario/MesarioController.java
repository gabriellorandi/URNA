package Mesario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class MesarioController {
    @FXML TextField txtId;
    @FXML TextField txtNome;
    @FXML TextField txtCpf;
    @FXML TextField txtLogin;
    @FXML TextField txtSenha;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    @FXML private TableView<Mesario> tableView;
    @FXML private TableColumn<Mesario, Long> mesarioId;
    @FXML private TableColumn<Mesario, String> mesarioNome;
    @FXML private TableColumn<Mesario, String> mesarioCpf;
    @FXML private TableColumn<Mesario, String> mesarioLogin;
    @FXML private TableColumn<Mesario, String> mesarioSenha;

    List<Mesario> mesarios;
    MesarioDAO mesarioDAO;

    @FXML
    public void initialize() {

        mesarioDAO = new MesarioDAO();
        mesarios = mesarioDAO.selecionarMesarios();

        mesarioId.setCellValueFactory(new PropertyValueFactory<>("id"));
        mesarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        mesarioCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        mesarioLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        mesarioSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tableView.getItems().addAll(mesarios);

        tableView.refresh();

    }

    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarMesario() {

        Mesario mesario = new Mesario();
        mesario.setNome( txtNome.getText() );
        mesario.setCpf( txtCpf.getText() );
        mesario.setLogin( txtLogin.getText() );
        mesario.setSenha( txtSenha.getText() );

        mesarioDAO.cadastrarMesario(mesario);

        mesarios = mesarioDAO.selecionarMesarios();

        tableView.getItems().setAll(mesarios);
        tableView.refresh();

    }

    public void removerMesario() {

        Mesario mesario = tableView.getSelectionModel().getSelectedItem();

        if(mesario != null){

            mesarios.remove(mesario);
            mesarioDAO.removerMesario(mesario.getId());

            tableView.getItems().setAll(mesarios);
            tableView.refresh();

        }
    }


}