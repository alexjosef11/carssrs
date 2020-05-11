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
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(),passwordconfirmField.getText(),
                    firstnameField.getText(), secondnameField.getText(),phonenumberField.getText(),
                    addressField.getText(),roleField.getText());
            registrationMessage.setText("Account created successfully!");
            usernameField.clear();
            passwordField.clear();
            passwordconfirmField.clear();
            firstnameField.clear();
            secondnameField.clear();
            phonenumberField.clear();
            addressField.clear();
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
        catch (PasswordConfirmationException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
        catch (FieldNotCompletedException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
        catch (WeekPasswordException e) {
            registrationMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
    }
    public void goBackToRoleChooseScene(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("role_choose_register.fxml"));
        Scene adminpinscene = new Scene(rolechoose, 600, 500);
        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
        window.setScene(adminpinscene);
        window.show();
    }
    public void setRole(String role){
        this.roleField.setText(role);
    }
}

