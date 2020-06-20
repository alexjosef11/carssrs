package controllers;

import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import user.registration.controllers.AdminPinRegisterController;
import org.testfx.framework.junit.ApplicationTest;
import user.registration.exceptions.PinIncorrectException;
import user.registration.services.UserService;

import static org.junit.Assert.assertEquals;
import static user.registration.controllers.AdminPinRegisterController.PinMessage;

public class AdminPinRegisterControllerTest extends ApplicationTest {

    private AdminPinRegisterController controller;
    @Before
    public void setUp() throws Exception {

        controller = new AdminPinRegisterController();
        controller.PinMessage = new javafx.scene.text.Text();
    }
    
    @Test
    public void testCheckPin() throws Exception {
        AdminPinRegisterController.checkPin("PinAdmin1234!");
        assertEquals(PinMessage.getText(),"");
    }

    @Test(expected = PinIncorrectException.class)
    public void testCheckPinexception() throws Exception {
        AdminPinRegisterController.checkPin("test");
    }

}