package pl.kancelaria.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.kancelaria.modelFX.ClientModel;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;

public class AddClientController {

    @FXML
    private Button addClientButton;
    @FXML
    private TextField clientNameTextField;
    @FXML
    private TextArea clientDescriptionTextArea;
    @FXML
    private TextField clientFormOfSettTextField;

    private ClientModel clientModel;
    private ClientsController clientsController;

    @FXML
    public void initialize(){
        this.clientModel = new ClientModel();
        try {
            this.clientModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        validation();
        bindings();
    }

    public void bindings(){
        this.clientFormOfSettTextField.textProperty().bindBidirectional(this.clientModel.clientFXObjectProperty().get().formOfSettlementProperty());
        this.clientNameTextField.textProperty().bindBidirectional(this.clientModel.clientFXObjectProperty().get().nameProperty());
        this.clientDescriptionTextArea.textProperty().bindBidirectional(this.clientModel.clientFXObjectProperty().get().descProperty());
    }

    private void validation() {
      this.addClientButton.disableProperty().bind(this.clientNameTextField.textProperty().isNull());
    }

    public void saveClient() {
        try {
            this.clientModel.saveInDb();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }
        this.clientNameTextField.clear();
        this.clientFormOfSettTextField.clear();
        this.clientDescriptionTextArea.clear();

        Stage stage = (Stage) this.addClientButton.getScene().getWindow();
        stage.close();
        DialogUtils.dialogClientInfo();
    }
}
