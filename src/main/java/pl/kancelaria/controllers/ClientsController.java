package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import pl.kancelaria.utils.FxmlUtils;

public class ClientsController {

    private MainWindowController mainWindowController;

    public void backToMainWindow() {
        this.mainWindowController.getMainWindowBorderPane().setCenter(FxmlUtils.fxmlLoader("/fxml/MainWindow.fxml"));
    }
}
