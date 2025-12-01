package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.CrudUtil;
import util.EmailService;
import util.HibernateUtil;
import util.PasswordEncryption;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.Random;

public class ForgotPasswordFormController {

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtNewPassword;

    private String email;
    private String generatedOTP;

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) {
        if (!validatePasswords())return;

        email=askEmail();
        if (email==null) return;

        if (!isEmailRegistered(email)){
            new Alert(Alert.AlertType.ERROR, "This email is not registered!").show();
            return;
        }

        generatedOTP=genarateOTP();

        if (!EmailService.sendOTP(email, generatedOTP)){
            new Alert(Alert.AlertType.ERROR, "Error sending OTP. Try again!").show();
            return;
        }
        if (!verifyOTP()) {
            new Alert(Alert.AlertType.ERROR, "Invalid OTP!").show();
            return;
        }

        updatePassword(email, txtNewPassword.getText());
        new Alert(Alert.AlertType.INFORMATION, "Password changed successfully!").show();
    }

    private void updatePassword(String email, String newPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            User user = session.createQuery(
                            "FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();

            if (user != null) {
                String encrypted = PasswordEncryption.encrypt(newPassword);
                user.setPassword(encrypted);
                session.update(user);
            }

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Error updating password", e);

        } finally {
            session.close();
        }
    }

    private boolean verifyOTP() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("OTP Verification");
        dialog.setHeaderText("Enter OTP sent to " + email);
        dialog.setContentText("OTP:");

        Optional<String> result = dialog.showAndWait();
        return result.isPresent() && result.get().equals(generatedOTP);
    }

    private String genarateOTP() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    private boolean isEmailRegistered(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery("FROM User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return user != null;
        } catch (Exception e) {
            throw new RuntimeException("Error checking email", e);
        }
    }

    private String askEmail() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Email Verification");
        dialog.setHeaderText("Enter your registered email:");
        dialog.setContentText("Email:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private boolean validatePasswords() {
        if (txtNewPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter both password fields!").show();
            return false;
        }
        if (!txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return false;
        }
        return true;
    }

}

