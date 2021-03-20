package pl.kancelaria.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class DialogUtils {

    public static void dialogError(String error){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Błąd");
        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();

    }

    public static void dialogEmployeeInfo(){
        Alert addedEmp = new Alert(Alert.AlertType.INFORMATION);
        addedEmp.setContentText("Dodano Pracownika!");
        addedEmp.showAndWait();
    }

    public static void dialogClientInfo(){
        Alert addedEmp = new Alert(Alert.AlertType.INFORMATION);
        addedEmp.setContentText("Dodano Klienta!");
        addedEmp.showAndWait();
    }

}
