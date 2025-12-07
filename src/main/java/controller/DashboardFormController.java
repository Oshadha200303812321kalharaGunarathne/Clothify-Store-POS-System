package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private AnchorPane contentArea;

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

    private void loadUI(String fileName) {
        try {
            Node node = FXMLLoader.load(getClass().getResource("/view/" + fileName + ".fxml"));
            contentArea.getChildren().setAll(node);

            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        loadUI("employee_registration_form");
    }

    @FXML
    void btnItemsOnAction(ActionEvent event) {
        loadUI("items_form");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
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
    void btnOrderDetailsOnAction(ActionEvent event) {
        loadUI("order_details_form");
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        loadUI("order_form");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        loadUI("reports_form");
    }

    @FXML
    void btnReturnsOnAction(ActionEvent event) {
        loadUI("sales_return_form");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        loadUI("supplier_form");
    }

}
