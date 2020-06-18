package user.registration.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import user.registration.model.Announcement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static user.registration.services.AnnouncementsService.USERS_PATH;

public class AnnouncementsFeedController {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Text availability ;
    @FXML
    private ListView<Announcement> Card= new ListView<Announcement>();
    @FXML
    private ImageView imageView = new ImageView();
    private static List<Announcement> announcements ;
    @FXML
    public void initialize() throws IOException {
    readJsonData();
    ObservableList<Announcement> items = FXCollections.observableArrayList ();
        for(Announcement item : announcements){
                    items.add(item);
                    Card.setItems(items);
                }
        }
    public void handlemouseclick()throws IOException{
        Card.getSelectionModel().getSelectedItem();
        Path path = Paths.get(Card.getSelectionModel().getSelectedItem().getFile());
        imageView.setImage(new Image(Files.newInputStream(path)));
        String status = "";
        for(Announcement item : announcements ) {
            if(item.equals(Card.getSelectionModel().getSelectedItem()))
            if (item.getRentBox() == true) {
                status = "This car is avalible for Rent!";
            } else if (item.getSwapBox() == true) {
                status = "This car is avalible for Swap!";
            }else {
                status = "This car is avalible for Sell!";
            }
        }
        availability.setText(status);


    }

    public void readJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

       announcements  = objectMapper.readValue(USERS_PATH.toFile(),
                new TypeReference<List<Announcement>>() {
                });
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
    @FXML
    public void gotoAddAnnouncement(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("add_announcements.fxml"));
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
    @FXML
    public void gotoOfferTab(javafx.event.ActionEvent back) throws IOException {
        Parent rolechoose = FXMLLoader.load(getClass().getClassLoader().getResource("add_announcements.fxml"));
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