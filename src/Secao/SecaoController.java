package Secao;

import Eleicao.Eleicao;
import Eleitor.Eleitor;
import Eleitor.EleitorDAO;
import Mesario.Mesario;
import Mesario.MesarioDAO;
import Utils.AlertUtils;
import Utils.ValidateFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.util.List;

public class SecaoController {

    @FXML TextField txtLogradouro;
    @FXML TextField txtNumero;
    @FXML Button btnAdd;
    @FXML Button btnCancel;

    @FXML private ComboBox cbMesarios;
    @FXML private CheckComboBox<Eleitor> ccbEleitores;

    @FXML private TableView<Secao> tableView;
    @FXML private TableColumn<Secao, Long> secaoId;
    @FXML private TableColumn<Secao, String> secaoLogradouro;
    @FXML private TableColumn<Secao, String> secaoNumero;
    @FXML private TableColumn<Secao, String> secaoMesario;
    @FXML private TableColumn<Secao, Integer> secaoEleitores;


    private SecaoDAO secaoDAO;
    private MesarioDAO mesarioDAO;
    private EleitorDAO eleitorDAO;

    private List<Secao> secoes;
    private List<Mesario> mesarios;


    private Eleicao eleicao;

    @FXML
    public void initialize() {

        secaoDAO = new SecaoDAO();
        mesarioDAO = new MesarioDAO();
        eleitorDAO = new EleitorDAO();

        secoes = secaoDAO.selecionarSecoes();
        mesarios = mesarioDAO.selecionarMesarios();

        cbMesarios.getItems().addAll(mesarios);

        secaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        secaoLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        secaoNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        secaoMesario.setCellValueFactory(new PropertyValueFactory<>("mesarioNome"));
        secaoEleitores.setCellValueFactory(new PropertyValueFactory<>("eleitoresQuantidade"));

        tableView.getItems().addAll(secoes);

        tableView.refresh();


    }

    public void cadastrarSecao() {

        if (ValidateFields.validateTextField(txtLogradouro.getText())
        && ValidateFields.validateNumberField(txtNumero.getText())){
            Secao secao = new Secao();
            secao.setLogradouro( txtLogradouro.getText() );
            secao.setNumero( Integer.parseInt( txtNumero.getText() ) );

            Mesario mesario = (Mesario) cbMesarios.getSelectionModel().getSelectedItem();

            secao.setMesario(mesario);

            secaoDAO.cadastrarSecao(secao);

            secoes = secaoDAO.selecionarSecoes();
            tableView.getItems().setAll(secoes);
            tableView.refresh();
        } else {
            AlertUtils.alert("Valores incorretos!", "Os valores inseridos nos campos estão incorretos. Tente novamente.", Alert.AlertType.ERROR);
        }
    }

    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void removerSecao() {


        Secao secao = tableView.getSelectionModel().getSelectedItem();

        if(secao != null) {

            secoes.remove(secao);
            secaoDAO.removerSecao(secao.getId());

            tableView.getItems().setAll(secoes);
            tableView.refresh();

        }


    }

    public void load(Eleicao eleicao) {
        this.eleicao = eleicao;
    }
}
