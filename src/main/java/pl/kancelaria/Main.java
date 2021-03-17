package pl.kancelaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.kancelaria.controllers.MainWindowController;
import pl.kancelaria.utils.FxmlUtils;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Pane mainPane = FxmlUtils.fxmlLoader("/fxml/MainWindow.fxml");
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Moja Kancelaria");
        primaryStage.show();
    }
}