package Controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import user.registration.controllers.AddAnnouncementsController;
import user.registration.model.Announcement;
import user.registration.services.AnnouncementsService;
import user.registration.services.FileSystemService;

import static org.junit.Assert.assertEquals;

public class AddAnnouncemetsControllerTest extends ApplicationTest{

        private AddAnnouncementsController controller;

        @BeforeClass
        public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        AnnouncementsService.loadAnnouncementsFromFile();
    }

        @Before
        public void setUp() throws Exception {
            FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
            AnnouncementsService.loadAnnouncementsFromFile();

            controller = new AddAnnouncementsController();
            controller.makeField = new TextField();
            controller.modelField = new TextField();
            controller.priceField = new TextField();
            controller.yearField = new TextField();
            controller.kilometersField = new TextField();
            controller.powerField = new TextField();
            controller.SellBox = new javafx.scene.control.CheckBox();
            controller.SwapBox = new javafx.scene.control.CheckBox();
            controller.RentBox = new javafx.scene.control.CheckBox();
            controller.VehicleTypeBox = new ChoiceBox();
            controller.FuelTypeBox = new ChoiceBox();
            controller.path = new String();
            controller.AddAnnouncementMessage = new Text();

            controller.makeField.setText("test");
            controller.modelField.setText("test1");
            controller.priceField.setText("test2");
            controller.yearField.setText("test3");
            controller.kilometersField.setText("test4");
            controller.powerField.setText("test5");
            controller.path = "test6";
            controller.VehicleTypeBox.setAccessibleText("we");
            controller.FuelTypeBox.setAccessibleText("as");
            controller.SellBox.setSelected(true);
            controller.SwapBox.setSelected(false);
            controller.RentBox.setSelected(false);

        }

        @Test
        public void testHandleAddAnnouncementActionCode() {
            controller.handleAddAnnouncementAction();
            assertEquals(1, AnnouncementsService.Announcements.size());
            assertEquals("Announcement created sucessfully!", controller.AddAnnouncementMessage.getText());
        }
        @Test
         public void testhandleChoiceBoxActionCode(){
            controller.handleChoiceBoxAction();
            assertEquals(true, controller.SellBox.isSelected());
            assertEquals(false, controller.RentBox.isSelected());
            assertEquals(false, controller.SwapBox.isSelected());
        }


    }
