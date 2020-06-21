package controllers;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import user.registration.controllers.AdminScheduleController;
import user.registration.controllers.ProfileController;
import user.registration.controllers.UserMeetingsController;
import user.registration.services.FeedbackService;
import user.registration.services.FileSystemService;
import user.registration.services.UserService;

import static org.junit.Assert.assertEquals;

public class AdminScheduleControllerTest extends ApplicationTest {
    private AdminScheduleController controller;
    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
        FeedbackService.loadFeedbackFromFile();
    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FeedbackService.loadFeedbackFromFile();

        controller = new AdminScheduleController();

        controller.scheldueText = new Text();
    }
    @Test
    public void testRefreshScheduleAction() {
        controller.refreshScheldue();
        assertEquals("Nothing to schedule", controller.scheldueText.getText());
    }
}
