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
import user.registration.model.User;
import user.registration.services.UserService;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

import static user.registration.services.UserService.users;

public class LoginController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Text loginMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordconfirmField;
    private String role;
    private static String loggedUsername;
    @FXML
    public void handleLoginAction(javafx.event.ActionEvent clientinterface) throws IOException {
        try {
            UserService.loginUser(usernameField.getText(), passwordField.getText(), passwordconfirmField.getText());
            loginMessage.setText("Login successfully!");
            for (User user : users) {
                if (Objects.equals(usernameField.getText(), user.getUsername())) {
                    this.role = user.getRole();
                    this.loggedUsername=user.getUsername();
                }
            }
            if (role.equals("Client")) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getClassLoader().getResource("client_interface.fxml"));
                Parent viewclientinterface = Loader.load();
                ClientInterfaceController clientInterfaceController = Loader.getController();
                clientInterfaceController.setusername("Welcome, " + usernameField.getText() + "!");
                Scene clientregisterscene = new Scene(viewclientinterface, 650, 465);
                Stage window = (Stage) ((Node) clientinterface.getSource()).getScene().getWindow();
                viewclientinterface.setOnMousePressed(event -> {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                });
                viewclientinterface.setOnMouseDragged(event -> {
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);
                    window.setOpacity(0.8f);
                });
                viewclientinterface.setOnDragDone(event -> {
                    window.setOpacity(1.0f);
                });
                viewclientinterface.setOnMouseReleased(event -> {
                    window.setOpacity(1.0f);
                });
                window.setScene(clientregisterscene);
                window.show();
            } else {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getClassLoader().getResource("admin_interface.fxml"));
                Parent viewadmininterface = Loader.load();
                AdminInterfaceController adminInterfaceController = Loader.getController();
                adminInterfaceController.setusername("Welcome Admin, " + usernameField.getText() + "!");
                Scene clientregisterscene = new Scene(viewadmininterface, 650, 465);
                Stage window = (Stage) ((Node) clientinterface.getSource()).getScene().getWindow();
                viewadmininterface.setOnMousePressed(event -> {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                });
                viewadmininterface.setOnMouseDragged(event -> {
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);
                    window.setOpacity(0.8f);
                });
                viewadmininterface.setOnDragDone(event -> {
                    window.setOpacity(1.0f);
                });
                viewadmininterface.setOnMouseReleased(event -> {
                    window.setOpacity(1.0f);
                });
                window.setScene(clientregisterscene);
                window.show();
            }
        } catch (UsernameDoesNotExistsException e) {
            loginMessage.setText(e.getMessage());
            usernameField.clear();
            passwordField.clear();
            passwordconfirmField.clear();
        } catch (PasswordConfirmationException e) {
            loginMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        } catch (WrongPasswordException e) {
            loginMessage.setText(e.getMessage());
            passwordField.clear();
            passwordconfirmField.clear();
        }
    }

    public void goBackToLoginSignup(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("login_signup.fxml"));
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
    public static String getLoggedUsername(){return loggedUsername;}

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

    public void minimizeWindow(javafx.event.ActionEvent min) {
        Stage window = (Stage) ((Node) min.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    public void closeWindow(javafx.event.ActionEvent close) {
        Stage window = (Stage) ((Node) close.getSource()).getScene().getWindow();
        window.close();
    }
}

