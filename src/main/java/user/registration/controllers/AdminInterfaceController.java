package user.registration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminInterfaceController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField usernameField;

    public void goToTranzactionTab(javafx.event.ActionEvent back) throws IOException {
        Parent announcements = FXMLLoader.load(getClass().getClassLoader().getResource("login_signup.fxml"));
        Scene announcementFeed = new Scene(announcements, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(announcementFeed);
        announcements.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        announcements.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        announcements.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        announcements.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.show();
    }

    public void goToScheldueTab(javafx.event.ActionEvent back) throws IOException {
        Parent loginsignup = FXMLLoader.load(getClass().getClassLoader().getResource("admin_scheldue.fxml"));
        Scene loginsignupinterface = new Scene(loginsignup, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(loginsignupinterface);
        loginsignup.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        loginsignup.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        loginsignup.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        loginsignup.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.show();
    }

    public void goToFeedbacksTab(javafx.event.ActionEvent back) throws IOException {
        Parent ofertab = FXMLLoader.load(getClass().getClassLoader().getResource("admin_feedback.fxml"));
        Scene ofertabinterface = new Scene(ofertab, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(ofertabinterface);
        ofertab.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        ofertab.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        ofertab.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        ofertab.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.show();
    }

    public void goBackToLoginSignup(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("login_signup.fxml"));
        Scene adminpinscene = new Scene(rolechoose, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(adminpinscene);
        rolechoose.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        rolechoose.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        rolechoose.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        rolechoose.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.show();
    }

    public void setusername(String username){
        this.usernameField.setText(username);
    }

    public void minimizeWindow(javafx.event.ActionEvent min) {
        Stage window = (Stage) ((Node)min.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    public void closeWindow(javafx.event.ActionEvent close) {
        Stage window = (Stage) ((Node)close.getSource()).getScene().getWindow();
        window.close();
    }
}


