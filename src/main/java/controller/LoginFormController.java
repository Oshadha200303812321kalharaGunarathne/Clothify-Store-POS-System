package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.dto.User;
import util.CrudUtil;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws Exception {
        if (txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter your Username and Password!").show();
            return;
        }

        User user=getLoginUser();
        if (user != null){
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
        } else {
            new Alert(Alert.AlertType.ERROR, "Incorrect Username or Password! Try again.").show();
        }
    }

    private User getLoginUser() throws Exception {
        try {
            ResultSet rst = CrudUtil.execute(
                    "SELECT * FROM user WHERE username = ? AND password = ?;",
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            if (rst.next()) {
                return new User(
                        rst.getInt("id"),
                        rst.getString("username"),
                        rst.getString("password"),
                        rst.getString("email")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @FXML
    void lblMouseClickOnAction(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/forgot_password.fxml"));
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
