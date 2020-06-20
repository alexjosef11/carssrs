package user.registration.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import user.registration.exceptions.FieldNotCompletedException;
import user.registration.model.Announcement;
import user.registration.model.User;
import user.registration.services.AnnouncementsService;
import user.registration.services.FileSystemService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static user.registration.services.AnnouncementsService.ANNOUNCEMENTS_PATH;

public class AddAnnouncementsController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Text AddAnnouncementMessage;
    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField kilometersField;
    @FXML
    private TextField powerField;
    @FXML
    private ChoiceBox VehicleTypeBox;
    @FXML
    private ChoiceBox FuelTypeBox;
    @FXML
    private CheckBox SellBox;
    @FXML
    private CheckBox RentBox;
    @FXML
    private CheckBox SwapBox;
    @FXML
    private File file;
    @FXML
    private String path ;

    @FXML
    public void initialize(){
        VehicleTypeBox.getItems().addAll("Cabriolet","Estate","Saloon","Hatchback","Coupe","SUV","Van");
        FuelTypeBox.getItems().addAll("Diesel","Petrol","Electric");
    }
    @FXML
    void handleAddPhoto(){
        Stage stage = new Stage();
        stage.setTitle("Add Photo");
        FileChooser filechooser = new FileChooser();
        filechooser.setInitialDirectory(new File("C:\\"));
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("jpg files","*.jpg"));
        file = filechooser.showOpenDialog(stage);
        path = file.getAbsolutePath();
        filechooser.setInitialDirectory(file.getParentFile());
        }

    @FXML
    public void handleChoiceBoxAction(){
        if(SellBox.isSelected()==true)
        {
            SwapBox.setSelected(false);
            RentBox.setSelected(false);
        }
        if(SwapBox.isSelected()==true)
        {
            SellBox.setSelected(false);
            RentBox.setSelected(false);
        }
        if(RentBox.isSelected()==true)
        {
            SwapBox.setSelected(false);
            SellBox.setSelected(false);
        }

    }
    @FXML
    public void handleAddAnnouncementAction() {
        try {
            AnnouncementsService.addAnnouncement(makeField.getText(), modelField.getText(), priceField.getText(),
                    yearField.getText(), kilometersField.getText(), powerField.getText(), (String) VehicleTypeBox.getValue(), (String) FuelTypeBox.getValue(), SellBox.isSelected(), RentBox.isSelected(), SwapBox.isSelected(), path);
            AddAnnouncementMessage.setText("Announcements created sucessfully!");
            makeField.clear();
            modelField.clear();
            priceField.clear();
            yearField.clear();
            kilometersField.clear();
            powerField.clear();
            AnnouncementsService.persistAnnouncement();
        } catch (FieldNotCompletedException e) {
            AddAnnouncementMessage.setText(e.getMessage());
            makeField.clear();
            modelField.clear();
            priceField.clear();
            yearField.clear();
            kilometersField.clear();
        }
    }
    public void backtosomething(javafx.event.ActionEvent back) throws IOException {
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
    public void minimizeWindow(javafx.event.ActionEvent min) {
        Stage window = (Stage) ((Node)min.getSource()).getScene().getWindow();
        window.setIconified(true);
    }

    public void closeWindow(javafx.event.ActionEvent close) {
        Stage window = (Stage) ((Node)close.getSource()).getScene().getWindow();
        window.close();
    }

}
