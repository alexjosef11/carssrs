package user.registration.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import user.registration.exceptions.*;
import user.registration.model.Feedback;
import user.registration.model.FeedbackAdmin;
import user.registration.model.User;

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

public class FeedbackService {

    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "feedback.json");
    private static List<Feedback> feedback=new ArrayList<Feedback>();
       public static void loadFeedbackFromFile() throws IOException {

           if (!Files.exists(USERS_PATH)) {
               FileUtils.copyURLToFile(FeedbackService.class.getClassLoader().getResource("feedback.json"), USERS_PATH.toFile());
           }

           ObjectMapper objectMapper = new ObjectMapper();

           feedback = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<Feedback>>() {
           });
           // System.out.println(feedback.toString());
           for (Feedback f : feedback){
               if (FeedbackAdmin.Responses != null)
                   FeedbackAdmin.Responses = FeedbackAdmin.Responses + f.getResponse1() + " " + f.getResponse2() + " " + f.getResponse3() + " " + f.getResponse4() + "\n";
               else
                   FeedbackAdmin.Responses = f.getResponse1() + " " + f.getResponse2() + " " + f.getResponse3() + " " + f.getResponse4() + "\n";
           }
            System.out.println(FeedbackAdmin.Responses);
       }



    public static void addFeedback(String response1, String response2, String response3, String response4) throws FieldNotCompletedException {

        checkAllFieldCompleted(response1, response2, response3, response4);
       feedback.add(new Feedback(response1, response2, response3, response4));
       //System.out.println(feedback.toString());
       persistFeedback();
    }




    private static void checkAllFieldCompleted(String response1, String response2, String response3, String response4)
            throws FieldNotCompletedException {
        if (response1.trim().isEmpty() || response2.trim().isEmpty() || response3.trim().isEmpty() ||
                response4.trim().isEmpty()) {
            throw new FieldNotCompletedException();
        }
    }

    private static void persistFeedback() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), feedback);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
}
