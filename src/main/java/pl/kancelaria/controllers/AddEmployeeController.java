package pl.kancelaria.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kancelaria.modelFX.EmployeeModel;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;

public class AddEmployeeController {
    @FXML
    private Button addEmployeeButton;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private ComboBox cbPosition;

    EmployeeModel employeeModel;


    public void initialize(){
        this.employeeModel = new EmployeeModel();
        try {
            this.employeeModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        initComboBox();
        validation();
        bindings();
    }

    private void initComboBox() {
        ObservableList<String> positions = FXCollections.observableArrayList();
        positions.addAll("Asystent", "Prawnik", "Aplikant", "Mecenas");
        this.cbPosition.setItems(positions);

    }

    private void bindings() {
        this.tfName.textProperty().bindBidirectional(this.employeeModel.employeeFxProperty().get().nameProperty());
        this.tfSurname.textProperty().bindBidirectional(this.employeeModel.employeeFxProperty().get().surnameProperty());
        this.cbPosition.valueProperty().bindBidirectional(this.employeeModel.employeeFxProperty().get().positionProperty());

    }

    private void validation() {
        this.addEmployeeButton.disableProperty().bind(tfName.textProperty().isNull()
                .or(this.tfSurname.textProperty().isNull())
        .or(this.cbPosition.valueProperty().isNull()));
    }

    @FXML
    public void addEmployee() {
        try {
            this.employeeModel.save();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        this.tfSurname.clear();
        this.tfName.clear();
        this.cbPosition.getSelectionModel().clearSelection();
        Stage stage = (Stage) this.addEmployeeButton.getScene().getWindow();
        stage.close();
        DialogUtils.dialogEmployeeInfo();
    }
}
