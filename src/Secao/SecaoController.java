package Secao;

import Eleitor.Eleitor;
import Mesario.Mesario;
import Mesario.MesarioDAO;
import Eleitor.EleitorDAO;
import javafx.collections.ListChangeListener;
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

    private List<Eleitor> eleitores;
    private List<Eleitor> eleitoresSelecionados;

    @FXML
    public void initialize() {

        secaoDAO = new SecaoDAO();
        mesarioDAO = new MesarioDAO();
        eleitorDAO = new EleitorDAO();

        secoes = secaoDAO.selecionarSecoes();
        mesarios = mesarioDAO.selecionarMesarios();
        eleitores = eleitorDAO.selecionarEleitores();

        cbMesarios.getItems().addAll(mesarios);

        secaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        secaoLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        secaoNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        secaoMesario.setCellValueFactory(new PropertyValueFactory<>("mesarioNome"));
        secaoEleitores.setCellValueFactory(new PropertyValueFactory<>("eleitoresQuantidade"));

        tableView.getItems().addAll(secoes);

        tableView.refresh();

        ccbEleitores.getItems().addAll(eleitores);

        ccbEleitores.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Eleitor>() {
            public void onChanged(ListChangeListener.Change<? extends Eleitor> e) {
                eleitoresSelecionados = ccbEleitores.getCheckModel().getCheckedItems();
            }
        });

    }

    public void cadastrarSecao() {

        Secao secao = new Secao();
        secao.setLogradouro( txtLogradouro.getText() );
        secao.setNumero( Integer.parseInt( txtNumero.getText() ) );

        Mesario mesario = (Mesario) cbMesarios.getSelectionModel().getSelectedItem();

        secao.setEleitores(eleitoresSelecionados);

        secao.setMesario(mesario);

        secaoDAO.cadastrarSecao(secao);

        secoes = secaoDAO.selecionarSecoes();
        tableView.getItems().setAll(secoes);
        tableView.refresh();


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

}
