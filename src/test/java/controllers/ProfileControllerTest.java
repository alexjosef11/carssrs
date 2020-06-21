package controllers;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import user.registration.controllers.ProfileController;
import user.registration.model.Feedback;
import user.registration.model.User;
import user.registration.services.FeedbackService;
import user.registration.services.FileSystemService;
import user.registration.services.UserService;

import java.util.ArrayList;

import static user.registration.services.UserService.users;

import static org.junit.Assert.assertEquals;

public class ProfileControllerTest extends ApplicationTest {
    private ProfileController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();

    }
    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
        FeedbackService.loadFeedbackFromFile();

        users = new ArrayList<>();

        controller = new ProfileController();
        users.add(new User("test", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client"));
        controller.firstNameProfile = new TextField();
        controller.secondNameProfile = new TextField();
        controller.adressProfile = new TextField();
        controller.phoneProfile = new TextField();
    }
    @Test
    public void testViewProfileAction() {
        controller.handleShowProfile();
        assertEquals(1, UserService.users.size());
    }

}
