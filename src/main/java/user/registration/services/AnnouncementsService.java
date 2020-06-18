package user.registration.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import user.registration.exceptions.*;
import user.registration.model.Announcement;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class AnnouncementsService {


    public static final Path USERS_PATH = FileSystemService.getPathToFile("config", "announcements.json");

    private static List<Announcement> Announcements  = new ArrayList<Announcement>();

    public static void loadAnnouncementsFromFile() throws IOException {
    try{
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("announcements.json"), USERS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Announcements = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<Announcement>>() {});}catch (IOException ex){
    }
    }



    public static void addAnnouncement(String make, String model, String price, String year, String kilometers,
                                       String power, String VehicleType, String FuelType, boolean state, boolean rentBoxState, boolean selected, String file)
            throws FieldNotCompletedException {
        checkAllFieldCompleted(make, model, price, year, kilometers, power);
        Announcements.add(new Announcement(make, model, price, year, kilometers, power,VehicleType,FuelType,state,rentBoxState,selected,file));

        persistAnnouncement();
    }

    private static void checkAllFieldCompleted(String make, String model, String price, String year, String kilometers,
                                               String power)
            throws FieldNotCompletedException {
        if (make.trim().isEmpty() || model.trim().isEmpty()|| price.trim().isEmpty()||
                year.trim().isEmpty()|| kilometers.trim().isEmpty()|| power.trim().isEmpty()) {
            throw new FieldNotCompletedException();
        }
    }
    public static void persistAnnouncement() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), Announcements);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
