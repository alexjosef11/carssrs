package services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import user.registration.exceptions.*;
import user.registration.model.User;
import user.registration.services.FileSystemService;
import user.registration.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


import static org.junit.Assert.*;

public class UserServiceTest {

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
        UserService.loadUsersFromFile();
        assertTrue(Files.exists(UserService.USERS_PATH));
    }

    @Test
    public void testLoadUsersFromFile() throws Exception {
        UserService.loadUsersFromFile();
        assertNotNull(UserService.users);
        assertEquals(0, UserService.users.size());
    }

    @Test
    public void testAddOneUser() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        assertNotNull(UserService.users);
        assertEquals(1, UserService.users.size());
    }

    @Test
    public void testAddTwoUsers() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        UserService.addUser("test2", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        assertNotNull(UserService.users);
        assertEquals(2, UserService.users.size());
    }

    @Test(expected = UsernameDoesNotExistsException.class)
    public void testCheckUserDoesAlreadyExist() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        assertNotNull(UserService.users);
        UserService.checkUserDoesAlreadyExist("test2");
    }

    @Test(expected = UsernameAlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        assertNotNull(UserService.users);
        UserService.checkUserDoesNotAlreadyExist("test1");
    }

    @Test(expected = WrongPasswordException.class)
    public void testCheckPassword() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test1", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        assertNotNull(UserService.users);
        UserService.checkPassword("test1","Test1235!");
    }

    @Test(expected = PasswordConfirmationException.class)
    public void testCheckPasswordsMach() throws Exception {
        UserService.checkPasswordsMach("test1","test2");
    }

    @Test(expected = FieldNotCompletedException.class)
    public void testCheckAllFieldCompleted() throws Exception {
        UserService.checkAllFieldCompleted("","","","","","");
    }

    @Test(expected = WeekPasswordException.class)
    public void testCheckPasswordformatException() throws Exception {
        UserService.checkPasswordformatException("test");
    }

    @Test
    public void testAddOneUserIsPersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test3", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void testAddTwoUserArePersisted() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test4", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        UserService.addUser("test5", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        List<User> users = new ObjectMapper().readValue(UserService.USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }

    @Test
    public void testPasswordConfirmation() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test4", "Test1234!", "Test1234!","testfirs!","testsec","239692163","21323123","Client");
        assertNotEquals(UserService.encodePassword("test4", "Test1234!"),UserService.encodePassword("test4", "Test1235!"));
    }
    @Test
    public void testStringContainsNumber() throws Exception {
        assertEquals(true,UserService.stringContainsNumber("Test1"));
    }

    @Test
    public void testStringContainsUpperCase() throws Exception {
       assertEquals(true,UserService.stringContainsUpperCase("Test1"));
    }

    @Test
    public void testSringContainsSpecialCaracter() throws Exception {
        assertEquals(true, UserService.stringContainsSpecialCaracter("Test1!"));
    }
}