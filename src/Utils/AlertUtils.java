package Utils;

import javafx.scene.control.Alert;

public class AlertUtils {


    public static void alert(String title, String text, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(text);

        alert.showAndWait();
    }

}
