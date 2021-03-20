package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.kancelaria.modelFX.CaseFX;
import pl.kancelaria.modelFX.CaseModel;
import pl.kancelaria.modelFX.ClientFX;
import pl.kancelaria.modelFX.EmployeeFX;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;
import pl.kancelaria.utils.FxmlUtils;
import pl.kancelaria.utils.NewWindowUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class CasesController {
    @FXML private TableView<CaseFX> tableCases;
    @FXML private CheckBox finishedCasesCheckBox;
    @FXML private TableColumn<CaseFX, String> colSignature;
    @FXML private TableColumn<CaseFX,String> colDescription;
    @FXML private TableColumn<CaseFX,String> colCourt;
    @FXML private TableColumn<CaseFX,ClientFX> colClient;
    @FXML private TableColumn<CaseFX, LocalDate> colDate;
    private CaseModel caseModel;

    public void initialize(){
        this.caseModel = new CaseModel();
        try {
            this.caseModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        bindings();
    }

    private void bindings() {
        this.tableCases.setItems(this.caseModel.getCaseFXObservableList());
        this.colClient.setCellValueFactory(c->c.getValue().clientProperty());
        this.colCourt.setCellValueFactory(c->c.getValue().courtProperty());
        this.colDate.setCellValueFactory(c->c.getValue().startDateProperty());
        this.colDescription.setCellValueFactory(c->c.getValue().descProperty());
        this.colSignature.setCellValueFactory(c->c.getValue().signatureProperty());
    }

    public void addCase(ActionEvent actionEvent) {
        NewWindowUtil.newWindow("/fxml/AddCase.fxml");
        this.initialize();
    }
}
