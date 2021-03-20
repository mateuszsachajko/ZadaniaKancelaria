package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.kancelaria.database.models.Client;
import pl.kancelaria.modelFX.CaseModel;
import pl.kancelaria.modelFX.ClientFX;
import pl.kancelaria.modelFX.ClientModel;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;
import pl.kancelaria.utils.NewWindowUtil;

public class AddCaseController {
    @FXML
    private Button addCaseBtn;
    @FXML
    private TextField tfSignature;
    @FXML
    private TextField tfCourt;
    @FXML
    private TextArea taDescription;
    @FXML
    private DatePicker doStartDate;
    @FXML
    private ComboBox<ClientFX> comboBoxClients;

    private CaseModel caseModel;
    private ClientModel clientModel;
    
    @FXML
    public void initialize(){
        this.caseModel = new CaseModel();
        this.clientModel = new ClientModel();
        try {
            this.caseModel.init();
            this.clientModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        validation();
        bindings();
    }

    private void validation() {
        this.addCaseBtn.disableProperty().bind(this.taDescription.textProperty().isNull()
                .or(this.comboBoxClients.valueProperty().isNull()));
    }

    private void bindings() {
        this.taDescription.textProperty().bindBidirectional(this.caseModel.caseFXObjectPropertyProperty().get().descProperty());
        this.tfCourt.textProperty().bindBidirectional(this.caseModel.caseFXObjectPropertyProperty().get().courtProperty());
        this.tfSignature.textProperty().bindBidirectional(this.caseModel.caseFXObjectPropertyProperty().get().signatureProperty());
        this.comboBoxClients.setItems(this.clientModel.getClientsList());
        this.comboBoxClients.valueProperty().bindBidirectional(this.caseModel.caseFXObjectPropertyProperty().get().clientProperty());
    }


    public void saveToDbOnAction(ActionEvent actionEvent) {
        System.out.println(this.caseModel.getCaseFXObjectProperty());


        try {
            this.caseModel.saveInDb();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }


    }

    public void goToAddClient(ActionEvent actionEvent) {
        NewWindowUtil.newWindow("/fxml/AddClient.fxml");
        try {
            this.clientModel.init();
        } catch (AppExc appExc) {
            appExc.printStackTrace();
        }
    }
}
