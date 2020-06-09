package user.registration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import user.registration.exceptions.PinIncorrectException;

import java.io.IOException;


public class AdminPinRegisterController{
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    public PasswordField passwordconfirmField;
    @FXML
    public Text PinMessage;

    @FXML
    public void goBackToRoleChooseScene(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("role_choose_register.fxml"));
        Scene adminpinscene = new Scene(rolechoose, 650, 465);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
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
        window.setScene(adminpinscene);
        window.show();
    }

    public void goToUserRegisterScene(javafx.event.ActionEvent client) throws IOException {
        try {
            checkPin(passwordconfirmField.getText());
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getClassLoader().getResource("user_register.fxml"));
            Parent viewuserregistration = Loader.load();
            RegistrationController registrationController  = Loader.getController();
            registrationController.setRole("Application Owner");
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
        catch (PinIncorrectException e){
            PinMessage.setText("Pin is not correct !");
            passwordconfirmField.clear();
        }
    }

    private void checkPin(String password) throws PinIncorrectException {
        if (!password.trim().equals("PinAdmin1234!")) {
            PinMessage.setText("Pin is not correct!");
                throw new PinIncorrectException();
        }
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
