package user.registration.controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;



public class UserMeetingsController {

    public Text meetingsText;
    private double xOffset = 0;
    private double yOffset = 0;

    public void minimizeWindow(javafx.event.ActionEvent min) {
        Stage window = (Stage) ((Node)min.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    public void closeWindow(javafx.event.ActionEvent close) {
        Stage window = (Stage) ((Node)close.getSource()).getScene().getWindow();
        window.close();
    }
    public void refreshMeetings(){
        meetingsText.setText(AnnouncementsFeedController.q);
    }
    public void goBackToRoleChooseScene(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("client_interface.fxml"));
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

}