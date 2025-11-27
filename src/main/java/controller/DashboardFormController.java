package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    public void initialize(){
        setDate();
        setTime();
    }

    private void setDate() {
        LocalDate today = LocalDate.now();
        lblDate.setText(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    private void setTime(){
        Timeline clock = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void openWindow(String fxmlName, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/" + fxmlName + ".fxml"));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        openWindow("employee_registration_form", "Employee Management");
    }

    @FXML
    void btnItemsOnAction(ActionEvent event) {
        openWindow("items_form", "Items Management");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        openWindow("login_form", "User Login");
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) {
        openWindow("order_details_form", "Order Details");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        openWindow("order_form", "Place Order");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        openWindow("reports_form", "Reports");
    }

    @FXML
    void btnReturnsOnAction(ActionEvent event) {
        openWindow("sales_return_form", "Sales Return");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        openWindow("supplier_form", "Supplier Management");
    }

}
