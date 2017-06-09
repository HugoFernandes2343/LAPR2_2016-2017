/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class UserTest {

    private User u = new User("testUserName","test@Email","testPassword".toCharArray(),"testName","portuguese","GMT+1","key");
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
        char[] expResult = "testPassword".toCharArray();
        char[] result = instance.getPassword();
        assertArrayEquals(expResult, result);
        System.out.println("Sucess?:"+Arrays.equals(expResult, result));
    }

    /**
     * Test of setLanguage method, of class User.
     */
    @Test
    public void testSetLanguage() {
        System.out.println("setLanguage");
        String lang = "english";
        User instance = u;
        instance.setLanguage(lang);
        assertEquals(u.getLanguage(), lang);
        System.out.println(lang+","+u.getLanguage());
    }

    /**
     * Test of setTimeZone method, of class User.
     */
    @Test
    public void testSetTimeZone() {
        System.out.println("setTimeZone");
        String timeZone = "GMT+2";
        User instance = u;
        instance.setTimeZone(timeZone);
        assertEquals(u.getTimeZone(), timeZone);
        System.out.println(timeZone+","+u.getTimeZone());
    }
//TESTAR TOSTRING ?? NECESSARIO?
//    /**
//     * Test of toInfoString method, of class User.
//     */
//    @Test
//    public void testToInfoString() {
//        System.out.println("toInfoString");
//        User instance = null;
//        String expResult = "";
//        String result = instance.toInfoString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class User.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        User instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
