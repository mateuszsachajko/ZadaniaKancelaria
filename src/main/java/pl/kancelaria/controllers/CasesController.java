package pl.kancelaria.controllers;

import javafx.event.ActionEvent;
import pl.kancelaria.utils.FxmlUtils;

public class CasesController {
    private MainWindowController mainWindowController;

    public void toMainWindow() {
        this.mainWindowController.getMainWindowBorderPane().setCenter(FxmlUtils.fxmlLoader("/fxml/MainWindow.fxml"));
    }
}
