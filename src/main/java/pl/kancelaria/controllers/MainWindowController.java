package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import pl.kancelaria.utils.FxmlUtils;

public class MainWindowController {

    @FXML
    private BorderPane mainWindowBorderPane;

    public void addTask() {
        this.mainWindowBorderPane.setCenter(FxmlUtils.fxmlLoader("/fxml/addTask.fxml"));
    }

    public void goToClients(ActionEvent actionEvent) {
        this.mainWindowBorderPane.setCenter(FxmlUtils.fxmlLoader("/fxml/Clients.fxml"));
    }

    public void goToCases(ActionEvent actionEvent) {
        this.mainWindowBorderPane.setCenter(FxmlUtils.fxmlLoader("/fxml/Cases.fxml"));
    }

    //getters and setters

    public BorderPane getMainWindowBorderPane() {
        return mainWindowBorderPane;
    }

    public void setMainWindowBorderPane(BorderPane mainWindowBorderPane) {
        this.mainWindowBorderPane = mainWindowBorderPane;
    }
}
