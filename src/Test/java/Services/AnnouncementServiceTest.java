package Services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

import user.registration.model.Announcement;
import user.registration.services.AnnouncementsService;
import user.registration.services.FileSystemService;


public class AnnouncementServiceTest {

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
        AnnouncementsService.loadAnnouncementsFromFile();
        assertTrue(Files.exists(AnnouncementsService.ANNOUNCEMENTS_PATH));

    }
    @Test
    public void testLoadUsersFromFile() throws Exception {
        AnnouncementsService.loadAnnouncementsFromFile();
        assertNotNull(AnnouncementsService.Announcements);
        assertEquals(0, AnnouncementsService.Announcements.size());
    }

    @Test
    public void testAddOneUser() throws Exception {
        AnnouncementsService.loadAnnouncementsFromFile();
       AnnouncementsService.addAnnouncement("test", "testPass", "432","2000","123","3123",
               "Saloon","Diesel",true,false,false,"path");
        assertNotNull(AnnouncementsService.Announcements);
        assertEquals(1, AnnouncementsService.Announcements.size());
    }

    @Test
    public void testAddTwoUsers() throws Exception {
        AnnouncementsService.loadAnnouncementsFromFile();
        AnnouncementsService.addAnnouncement("test", "testPass", "432","2000","123","3123",
                "Saloon","Diesel",true,false,false,"path");
        AnnouncementsService.addAnnouncement("test2", "testPass", "432","2000","123","3123",
                "Saloon","Diesel",true,false,false,"path");
        assertNotNull(AnnouncementsService.Announcements);
        assertEquals(2, AnnouncementsService.Announcements.size());
    }


    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        AnnouncementsService.loadAnnouncementsFromFile();
        AnnouncementsService.addAnnouncement("test", "testPass", "432","2000","123","3123",
                "Saloon","Diesel",true,false,false,"path");
        List<Announcement> Announcemtent = new ObjectMapper().readValue(AnnouncementsService.ANNOUNCEMENTS_PATH.toFile(), new TypeReference<List<Announcement>>() {
        });
        assertNotNull(AnnouncementsService.Announcements);
        assertEquals(1, AnnouncementsService.Announcements.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        AnnouncementsService.loadAnnouncementsFromFile();
        AnnouncementsService.addAnnouncement("test", "testPass", "432","2000","123","3123",
                "Saloon","Diesel",true,false,false,"path");
        AnnouncementsService.addAnnouncement("test2", "testPass", "432","2000","123","3123",
                "Saloon","Diesel",true,false,false,"path");
        List<Announcement> Announcemtent = new ObjectMapper().readValue(AnnouncementsService.ANNOUNCEMENTS_PATH.toFile(), new TypeReference<List<Announcement>>() {
        });
        assertNotNull(AnnouncementsService.Announcements);
        assertEquals(2, AnnouncementsService.Announcements.size());

    }

}