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
import user.registration.exceptions.FieldNotCompletedException;
import user.registration.exceptions.PasswordConfirmationException;
import user.registration.exceptions.UsernameAlreadyExistsException;
import user.registration.exceptions.WeekPasswordException;
import user.registration.services.UserService;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegistrationController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField roleField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordconfirmField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField secondnameField;
    @FXML
    private TextField phonenumberField;
    @FXML
    private TextField addressField;


    @FXML
    public void handleRegisterAction(javafx.event.ActionEvent login) throws IOException {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), passwordconfirmField.getText(),
                    firstnameField.getText(), secondnameField.getText(), phonenumberField.getText(),
                    addressField.getText(), roleField.getText());
            registrationMessage.setText("Account created successfully!");
            usernameField.clear();
            passwordField.clear();
            passwordconfirmField.clear();
            firstnameField.clear();
            secondnameField.clear();
            phonenumberField.clear();
            addressField.clear();
            {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getClassLoader().getResource("user_login.fxml"));
                Parent viewuserlogin = Loader.load();
                Scene loginscene = new Scene(viewuserlogin, 650, 465);
                Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
                viewuserlogin.setOnMousePressed(event -> {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                });
                viewuserlogin.setOnMouseDragged(event -> {
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);
                    window.setOpacity(0.8f);
                });
                viewuserlogin.setOnDragDone(event -> {
                    window.setOpacity(1.0f);
                });
                viewuserlogin.setOnMouseReleased(event -> {
                    window.setOpacity(1.0f);
                });
                window.setScene(loginscene);
                window.show();
            }
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        } catch (PasswordConfirmationException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        } catch (FieldNotCompletedException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        } catch (WeekPasswordException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
    }

    public void goBackToRoleChooseScene(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("role_choose_register.fxml"));
        Scene adminpinscene = new Scene(rolechoose, 650, 465);
        Stage window = (Stage) ((Node) back.getSource()).getScene().getWindow();
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

    public void setRole(String role) {
        this.roleField.setText(role);
    }

    public void minimizeWindow(javafx.event.ActionEvent min) {
        Stage window = (Stage) ((Node) min.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    public void closeWindow(javafx.event.ActionEvent close) {
        Stage window = (Stage) ((Node) close.getSource()).getScene().getWindow();
        window.close();
    }
}

