package user.registration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientInterfaceController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField usernameField;
    public void initialize(){
        setusername(usernameField.getText());
    }

    public void goToAnnouncementsFeed(javafx.event.ActionEvent back) throws IOException {
        Parent announcements = FXMLLoader.load(getClass().getClassLoader().getResource("announcement_feed.fxml"));
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

    public void goToCreateNewAnnouncement(javafx.event.ActionEvent back) throws IOException {
        Parent loginsignup = FXMLLoader.load(getClass().getClassLoader().getResource("add_announcements.fxml"));
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

    public void goToOfferTab(javafx.event.ActionEvent back) throws IOException {
        Parent ofertab = FXMLLoader.load(getClass().getClassLoader().getResource("offer_tab.fxml"));
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

    public void goToMeetingTab(javafx.event.ActionEvent back) throws IOException {
        Parent meetingtab = FXMLLoader.load(getClass().getClassLoader().getResource("user_meeting.fxml"));
        Scene meetingtabinterface = new Scene(meetingtab, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(meetingtabinterface);
        meetingtab.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        meetingtab.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        meetingtab.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        meetingtab.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.show();
    }

    public void goToAccountProfile(javafx.event.ActionEvent back) throws IOException {
        Parent accountprofile = FXMLLoader.load(getClass().getClassLoader().getResource("user_profile.fxml"));
        Scene accountprofileinterface = new Scene(accountprofile, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(accountprofileinterface);
        accountprofile.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        accountprofile.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        accountprofile.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        accountprofile.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.show();
    }
    public void setusername(String username){
        this.usernameField.setText("Welcome, " + LoginController.getLoggedUsername() +"!");
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


