package user.registration.controllers;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import user.registration.exceptions.FieldNotCompletedException;
import user.registration.exceptions.PasswordConfirmationException;
import user.registration.exceptions.UsernameAlreadyExistsException;
import user.registration.exceptions.WeekPasswordException;
import user.registration.services.UserService;

import java.io.IOException;

public class ChooseRoleController {

    @FXML
    public void goToPinAdminScene(javafx.event.ActionEvent applicationowner) throws IOException {
        Parent viewadminpinregister = FXMLLoader.load(getClass().getClassLoader().getResource("admin_pin_register.fxml"));
        Scene adminpinscene = new Scene(viewadminpinregister, 600, 500);
        Stage window = (Stage) ((Node)applicationowner.getSource()).getScene().getWindow();
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
        Scene clientregisterscene = new Scene(viewuserregistration, 600, 500);
        Stage window = (Stage) ((Node)client.getSource()).getScene().getWindow();
        window.setScene(clientregisterscene);
        window.show();
    }
}
