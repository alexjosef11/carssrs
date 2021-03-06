package user.registration.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import user.registration.exceptions.*;
import user.registration.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserService {

    public static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");
    public static List<User> users;

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String password, String passwordconfirm, String firstname, String secondname,
                               String phonenumber, String address, String role)
            throws UsernameAlreadyExistsException, PasswordConfirmationException,FieldNotCompletedException, WeekPasswordException {
        checkUserDoesNotAlreadyExist(username);
        checkPasswordsMach(password, passwordconfirm);
        checkAllFieldCompleted(username, password, firstname, passwordconfirm, secondname,phonenumber);
        checkPasswordformatException(password);
        users.add(new User(username, encodePassword(username, password),encodePassword(username, passwordconfirm), firstname, secondname, phonenumber, address, role));
        persistUsers();
    }
    public static void loginUser(String username, String password, String passwordconfirm) throws UsernameDoesNotExistsException, PasswordConfirmationException, WrongPasswordException {
        checkUserDoesAlreadyExist(username);
        checkPasswordsMach(password, passwordconfirm);
        checkPassword(password,username);
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void checkAllFieldCompleted(String username, String password, String firstname, String passwordconfirm,
                                              String secondname, String phonenumber)
            throws FieldNotCompletedException {
        if (username.trim().isEmpty() || password.trim().isEmpty()|| firstname.trim().isEmpty()||
                passwordconfirm.trim().isEmpty()|| phonenumber.trim().isEmpty()|| secondname.trim().isEmpty()) {
            throw new FieldNotCompletedException();
        }
    }

    public static void checkPasswordsMach(String password, String passwordconfirm) throws PasswordConfirmationException {
        if (!password.trim().equals(passwordconfirm.trim())) {
            throw new PasswordConfirmationException();
        }
    }

    public static void checkPasswordformatException(String password) throws WeekPasswordException {
        if (password.length()<8)
            throw new WeekPasswordException("8 characters");
        if (!stringContainsNumber(password))
                throw new WeekPasswordException("one digit");
        if (!stringContainsUpperCase(password))
            throw new WeekPasswordException("one upper case");
        if (!stringContainsSpecialCaracter(password))
            throw new WeekPasswordException("one special character");
    }

    public static boolean stringContainsNumber(String s)
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }
    public static boolean stringContainsUpperCase(String s)
    {
        return Pattern.compile( "[A-Z]" ).matcher( s ).find();
    }
    public static boolean stringContainsSpecialCaracter(String s)
    {
        return Pattern.compile( "[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]" ).matcher( s ).find();
    }
    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
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

    public static void checkUserDoesAlreadyExist(String username) throws UsernameDoesNotExistsException {
        int ok=0;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                ok=1;
        }
        if(ok==0){
            throw new UsernameDoesNotExistsException(username);
        }
    }

    public static void checkPassword(String password, String username) throws WrongPasswordException {
        int ok=0;
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                if (Objects.equals(encodePassword(username,password), user.getPassword())) {
                    ok = 1;
                }
            }
        }
        if(ok==0) {
                throw new WrongPasswordException();
            }
    }

    public static List<User> getUsers() {
        return users;
    }

}
