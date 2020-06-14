package user.registration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import user.registration.exceptions.*;
import user.registration.services.UserService;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Text loginMessage;
    @FXML
    private TextField roleField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordconfirmField;

    @FXML
    public void handleLoginAction() {
        try {
            UserService.loginUser(usernameField.getText(), passwordField.getText(),passwordconfirmField.getText());
            loginMessage.setText("Login successfully!");
            usernameField.clear();
            passwordField.clear();
            passwordconfirmField.clear();
        } catch (UsernameDoesNotExistsException e) {
            loginMessage.setText(e.getMessage());
            usernameField.clear();
            passwordField.clear();
            passwordconfirmField.clear();
        }
        catch (PasswordConfirmationException e) {
            loginMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
        catch (WrongPasswordException e) {
            loginMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
    }
    public void goBackToRoleChooseScene(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("role_choose_register.fxml"));
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

    public void setRole(String role){
        this.roleField.setText(role);
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

