package user.registration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseRoleController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    public void goToPinAdminScene(javafx.event.ActionEvent applicationowner) throws IOException {
        Parent viewadminpinregister = FXMLLoader.load(getClass().getClassLoader().getResource("admin_pin_register.fxml"));
        Scene adminpinscene = new Scene(viewadminpinregister, 650, 465);
        Stage window = (Stage) ((Node)applicationowner.getSource()).getScene().getWindow();
        viewadminpinregister.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        viewadminpinregister.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        viewadminpinregister.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        viewadminpinregister.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.setScene(adminpinscene);
        window.show();
    }

    @FXML
    public void goToUserRegisterScene(javafx.event.ActionEvent client) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getClassLoader().getResource("user_register.fxml"));
        Parent viewuserregistration = Loader.load();
        RegistrationController registrationController  = Loader.getController();
        registrationController.setRole("Client");
        Scene clientregisterscene = new Scene(viewuserregistration, 650, 465);
        Stage window = (Stage) ((Node)client.getSource()).getScene().getWindow();
        viewuserregistration.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        viewuserregistration.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset);
            window.setY(event.getScreenY() - yOffset);
            window.setOpacity(0.8f);
        });
        viewuserregistration.setOnDragDone(event -> {
            window.setOpacity(1.0f);
        });
        viewuserregistration.setOnMouseReleased(event -> {
            window.setOpacity(1.0f);
        });
        window.setScene(clientregisterscene);
        window.show();
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
