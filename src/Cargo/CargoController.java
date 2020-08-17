package Cargo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class CargoController {

    @FXML TextField txtNome;
    @FXML Button btnCadastrar;
    @FXML Button btnCancel;
    @FXML Button btnRemove;

    @FXML private TableView<Cargo> tableView;
    @FXML private TableColumn<Cargo, Long> cargoId;
    @FXML private TableColumn<Cargo, String> cargoNome;

    List<Cargo> cargos;
    CargoDAO cargoDAO;

    @FXML
    public void initialize() {

        cargoDAO = new CargoDAO();

        cargoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cargoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        cargos = cargoDAO.selecionarChapas();
        tableView.getItems().addAll(cargos);
        tableView.refresh();
    }

    public void close(ActionEvent event) throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("../Urna/Urna.fxml"));

        Stage stage = (Stage)btnCancel.getScene().getWindow();

        stage.setTitle("Urna");
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void cadastrarCargo() {

        Cargo cargo = new Cargo();
        cargo.setNome( txtNome.getText());

        cargoDAO.cadastrarCargo(cargo);

        cargos = cargoDAO.selecionarChapas();

        tableView.getItems().setAll(cargos);
        tableView.refresh();


    }

    public void removerCargo() {

        Cargo cargo = tableView.getSelectionModel().getSelectedItem();

        if(cargo != null){

            cargos.remove(cargo);
            cargoDAO.removerCargo(cargo.getId());

            tableView.getItems().setAll(cargos);
            tableView.refresh();

        }

    }


}
