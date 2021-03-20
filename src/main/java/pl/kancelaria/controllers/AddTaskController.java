package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import pl.kancelaria.modelFX.*;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;

import java.time.LocalDate;

public class AddTaskController {
    @FXML private TextArea taDesc;
    @FXML private ComboBox<CaseFX> comboBoxCase;
    @FXML private Slider sliderPriority;
    @FXML private ComboBox<EmployeeFX> comboBoxEmployee;
    @FXML private Button saveTaskButton;

    private CaseModel caseModel;
    private ClientModel clientModel;
    private EmployeeModel employeeModel;
    private TaskModel taskModel;

    @FXML public void initialize(){
        this.taskModel = new TaskModel();
        this.caseModel = new CaseModel();
        this.clientModel = new ClientModel();
        this.employeeModel = new EmployeeModel();
        try {
            this.clientModel.init();
            this.caseModel.init();
            this.taskModel.init();
            this.employeeModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        validation();
        bindings();
    }

    private void bindings() {
        this.comboBoxEmployee.setItems(this.employeeModel.getEmployeeList());
        this.comboBoxCase.setItems(this.caseModel.getCaseFXObservableList());

        this.taDesc.textProperty().bindBidirectional(this.taskModel.taskFXProperty().get().descProperty());
        this.comboBoxCase.valueProperty().bindBidirectional(this.taskModel.taskFXProperty().get().casProperty());
        this.comboBoxEmployee.valueProperty().bindBidirectional(this.taskModel.taskFXProperty().get().employeeProperty());
        this.sliderPriority.valueProperty().bindBidirectional(this.taskModel.taskFXProperty().get().priorityProperty());
    }

    private void validation() {
        this.saveTaskButton.disableProperty().bind(this.taDesc.textProperty().isNull()
        .or(this.comboBoxCase.valueProperty().isNull())
                .or(this.comboBoxEmployee.valueProperty().isNull()));
    }


    @FXML public void saveTaskOnAction(ActionEvent actionEvent) {
        LocalDate deadline = LocalDate.now().plusDays(14);
        this.taskModel.getTaskFX().setDeadline(deadline);
        try {
            this.taskModel.save();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
    }
}
