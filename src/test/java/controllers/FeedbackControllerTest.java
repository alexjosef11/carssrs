package controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import user.registration.controllers.FeedbackAdminController;
import user.registration.controllers.FeedbackController;
import org.testfx.framework.junit.ApplicationTest;
import user.registration.model.Feedback;
import user.registration.services.FeedbackService;
import user.registration.services.FileSystemService;
import static org.junit.Assert.assertEquals;
import static user.registration.model.FeedbackAdmin.Responses;

public class FeedbackControllerTest extends ApplicationTest {
    private FeedbackController controller;
    //private FeedbackAdminController Controller;

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

        controller = new FeedbackController();
        controller.answer = new Text();
        //Controller=new FeedbackAdminController();
        controller.response1 = new TextField();
        controller.response2 = new TextField();
        controller.response3 = new TextField();
        controller.response4 = new TextField();
        controller.response1.setText("asdas");
        controller.response2.setText("asdas");
        controller.response3.setText("asdas");
        controller.response4.setText("asdas");
    }

    @Test
    public void testHandleFeedbackAction() {
       controller.handleFeedbackAction();
        assertEquals(1,FeedbackService.feedback.size());
       assertEquals("Thank you for the Feedback!", controller.answer.getText());
    }
}
