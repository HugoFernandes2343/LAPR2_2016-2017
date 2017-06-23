/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author PC
 */
public class UserTest {

    private final User u = new User("testUserName","test@Email","testPassword","testName","portuguese","GMT+1","key");
    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getNome");
        User instance = u;
        String expResult = "testName";
        String result = instance.getName();
        assertEquals(expResult, result);
        System.out.println(expResult+","+result);
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = u;
        String expResult = "testUserName";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        System.out.println(expResult+","+result);
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = u;
        String expResult = "test@Email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        System.out.println(expResult+","+result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = u;
        String expResult = "testPassword";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        System.out.println("Sucess?:"+expResult.equals(result));
    }

    /**
     * Test of setCurrentLanguage method, of class User.
     */
    @Test
    public void testSetLanguage() {
        System.out.println("setLanguage");
        String lang = "english";
        User instance = u;
        instance.setCurrentLanguage(lang);
        assertEquals(u.getCurrentLanguage(), lang);
        System.out.println(lang+","+u.getCurrentLanguage());
    }

    /**
     * Test of setTimeZone method, of class User.
     */
    @Test
    public void testSetTimeZone() {
        System.out.println("setTimeZone");
        String timeZone = "GMT+2";
        User instance = u;
        instance.setTimezone(timeZone);
        assertEquals(u.getTimezone(), timeZone);
        System.out.println(timeZone+","+u.getTimezone());
    }
}
