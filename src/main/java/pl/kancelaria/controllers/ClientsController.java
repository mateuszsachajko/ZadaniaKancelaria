package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.kancelaria.database.dao.ClientDao;
import pl.kancelaria.database.models.Client;
import pl.kancelaria.modelFX.ClientFX;
import pl.kancelaria.modelFX.ClientModel;
import pl.kancelaria.utils.AppExc;
import pl.kancelaria.utils.DialogUtils;
import pl.kancelaria.utils.FxmlUtils;
import pl.kancelaria.utils.NewWindowUtil;

import java.io.IOException;

public class ClientsController {
    @FXML
    private TableColumn<ClientFX, String> colName;
    @FXML
    private TableColumn<ClientFX, String> colForm;
    @FXML
    private TableColumn<ClientFX, String> colComments;
    @FXML
    private TableView<ClientFX> clientsTable;

    private ClientModel clientModel;

    public void initialize(){
        this.clientModel = new ClientModel();
        try {
            this.clientModel.init();
        } catch (AppExc appExc) {
            DialogUtils.dialogError(appExc.getMessage());
        }

        bindings();
    }

    public void bindings() {
        this.clientsTable.setItems(this.clientModel.getClientsList());
        this.colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.colComments.setCellValueFactory(cellData->cellData.getValue().descProperty());
        this.colForm.setCellValueFactory(cellData->cellData.getValue().formOfSettlementProperty());
    }

    public void addClient() {
        NewWindowUtil.newWindow("/fxml/AddClient.fxml");
        this.initialize();
    }
}
