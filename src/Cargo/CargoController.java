package Cargo;

import Utils.AlertUtils;
import Utils.ValidateFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

        cargos = cargoDAO.selecionarCargos();
        tableView.getItems().addAll(cargos);
        tableView.refresh();
    }

    public void close(ActionEvent event) throws Exception{

        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cadastrarCargo() {

        if (ValidateFields.validateTextField((txtNome.getText()))) {
            Cargo cargo = new Cargo();
            cargo.setNome( txtNome.getText());

            cargoDAO.cadastrarCargo(cargo);

            cargos = cargoDAO.selecionarCargos();

            tableView.getItems().setAll(cargos);
            tableView.refresh();
        } else {
            AlertUtils.alert("Valores incorretos!", "Os valores inseridos nos campos estão incorretos. Tente novamente.", Alert.AlertType.ERROR);
        }
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
