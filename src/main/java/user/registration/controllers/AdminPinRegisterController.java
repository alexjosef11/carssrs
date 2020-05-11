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


public class AdminPinRegisterController {

    @FXML
    public PasswordField passwordconfirmField;
    @FXML
    public Text PinMessage;

    @FXML
    public void goBackToRoleChooseScene(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("role_choose_register.fxml"));
        Scene adminpinscene = new Scene(rolechoose, 600, 500);

        Stage window = (Stage) ((Node)back.getSource()).getScene().getWindow();
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
            Scene clientregisterscene = new Scene(viewuserregistration, 600, 500);
            Stage window = (Stage) ((Node)client.getSource()).getScene().getWindow();
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
}
