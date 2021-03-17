package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import pl.kancelaria.utils.FxmlUtils;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private TasksController tasksController;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private void initialize(){
        setCenter("/fxml/Tasks.fxml");
    }

    public void setCenter(String fxmlPath){
        this.mainBorderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    @FXML
    public void addTask( ) {
        setCenter("/fxml/Tasks.fxml");
    }
    @FXML
    public void goToClients() {
        setCenter("/fxml/Clients.fxml");
    }
    @FXML
    public void goToCases() {
        setCenter("/fxml/Cases.fxml");
    }

    //getters and setters

    public BorderPane getMainWindowBorderPane() {
        return mainBorderPane;
    }

    public void setMainWindowBorderPane(BorderPane mainWindowBorderPane) {
        this.mainBorderPane = mainWindowBorderPane;
    }

}
