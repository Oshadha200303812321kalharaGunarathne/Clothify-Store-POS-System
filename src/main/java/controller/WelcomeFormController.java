package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class WelcomeFormController {
    @FXML
    private Button btnNext;
    @FXML
    void btnNextOnAction(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            Stage currentStage=(Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
