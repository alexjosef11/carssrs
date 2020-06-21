package services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import user.registration.model.Feedback;
import user.registration.services.FeedbackService;
import user.registration.services.FileSystemService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;
public class FeedbackServiceTest {
    @BeforeClass
    public static void setupClass() {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initApplicationHomeDirIfNeeded();
    }

    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomePath().toFile());
    }

    @Test
    public void testCopyDefaultFileIfNotExists() throws Exception {
        FeedbackService.loadFeedbackFromFile();
        assertTrue(Files.exists(FeedbackService.USERS_PATH));
    }
    @Test
    public void testLoadFeedbackFromFile() throws Exception {
        FeedbackService.loadFeedbackFromFile();
        assertNotNull(FeedbackService.feedback);
        assertEquals(0, FeedbackService.feedback.size());
    }
    @Test
   public void testAddOneFeedback() throws Exception {
        FeedbackService.loadFeedbackFromFile();
        FeedbackService.addFeedback("test", "testPass", "432","abc");
        assertNotNull(FeedbackService.feedback);
        assertEquals(1, FeedbackService.feedback.size());
    }

    @Test
    public void testAddTwoFeedback() throws Exception {
        FeedbackService.loadFeedbackFromFile();
        FeedbackService.addFeedback("test1", "testPass1", "123","asdsa");
        FeedbackService.addFeedback("test2", "testPass2", "456","sadsa");
        assertNotNull(FeedbackService.feedback);
        assertEquals(2, FeedbackService.feedback.size());
    }
    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        FeedbackService.loadFeedbackFromFile();
        FeedbackService.addFeedback("test", "testPass", "432","adsadsa");
        List<Feedback> feedback = new ObjectMapper().readValue(FeedbackService.USERS_PATH.toFile(), new TypeReference<List<Feedback>>() {
        });
        assertNotNull(feedback);
        assertEquals(1, feedback.size());
    }
    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        FeedbackService.loadFeedbackFromFile();
        FeedbackService.addFeedback("test1", "testPass", "432","adsadsa");
        FeedbackService.addFeedback("test2", "testPass", "432","adsadsa");
        List<Feedback> feedback = new ObjectMapper().readValue(FeedbackService.USERS_PATH.toFile(), new TypeReference<List<Feedback>>() {
        });
        assertNotNull(feedback);
        assertEquals(2, feedback.size());
    }
}
