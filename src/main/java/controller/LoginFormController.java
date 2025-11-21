package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            Stage currentStage=(Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblMouseClickOnAction(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/forgot_password_form.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblRegisterMouseClickOnAction(MouseEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/user_registration_form.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
