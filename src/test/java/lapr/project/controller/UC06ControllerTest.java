/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.FairCenter;
import lapr.project.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Hugo
 */
public class UC06ControllerTest {

    public UC06ControllerTest() {
    }

    /**
     * Test of validateUser method, of class UC06Controller.
     * In this test the user already in the system.
     */
    @Test
    public void testValidateUserAlreadyExists() {

        System.out.println("validateUser");
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        FairCenter fc = new FairCenter();
        User user = new User(username, email, password, name, language, timeZone, keyword);
        fc.registerUser(user);
        UC06Controller instance = new UC06Controller(fc);
        boolean expResult = false;
        boolean result = instance.validateUser(username, email, password, name, language, timeZone, keyword);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of validateUser method, of class UC06Controller.
     * In this test the user does not exist in the system.
     */
    @Test
    public void testValidateUserDoesNotExist() {

        System.out.println("validateUser");
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        FairCenter fc = new FairCenter();
        UC06Controller instance = new UC06Controller(fc);
        boolean expResult = true;
        boolean result = instance.validateUser(username, email, password, name, language, timeZone, keyword);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerUser method, of class UC06Controller.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        FairCenter fc = new FairCenter();
        UC06Controller instance = new UC06Controller(fc);
        String password = "Aa-1";
        instance.validateUser("htc", "htc@gmail.com", password, "Hugo Carvalho", "Portuguese", "GMT+00", "delta");
        boolean expResult = true;
        boolean result = instance.registerUser();
        assertEquals(expResult, result);

    }

}
