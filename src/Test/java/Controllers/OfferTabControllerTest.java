package Controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import user.registration.controllers.AddAnnouncementsController;
import user.registration.controllers.AnnouncementsFeedController;
import user.registration.controllers.OfferTabController;
import user.registration.model.Announcement;
import user.registration.services.AnnouncementsService;
import user.registration.services.FileSystemService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OfferTabControllerTest extends ApplicationTest{

    private OfferTabController controller;

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
        controller = new OfferTabController();
        controller.availability = new Text();


    }

    @Test
    public void testMouseclickActionCode() throws IOException {
        controller.handlemouseclick();
        assertEquals(" ", controller.availability.getText());
    }
    @Test
    public void testAcceptOfferActionCode() throws IOException {
        controller.AcceptButton();
        assertEquals("Please select an offer to accept!", controller.availability.getText());
    }
    @Test
    public void testDenyOfferActionCode() throws IOException {
        controller.DenyButton();
        assertEquals("Please select an offer to deny!", controller.availability.getText());
    }
    @Test
    public void testreadJsonDataActionCode() throws IOException {
        controller.readJsonData();
        assertEquals(0, AnnouncementsService.Announcements.size());
    }
}
