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

    private final User u = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");

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
        System.out.println(expResult + "," + result);
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
        System.out.println(expResult + "," + result);
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
        System.out.println(expResult + "," + result);
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
        System.out.println("Sucess?:" + expResult.equals(result));
    }

    /**
     * Test of getCurrentLanguage method, of class User.
     */
    @Test
    public void testGetCurrentLanguage() {
        System.out.println("getCurrentLanguage");
        String expResult = "portuguese";
        String result = u.getCurrentLanguage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getTimezone method, of class User.
     */
    @Test
    public void testGetTimezone() {
        System.out.println("getTimezone");
        String expResult = "GMT+1";
        String result = u.getTimezone();
        assertEquals(expResult, result);

    }

    /**
     * Test of toInfoString method, of class User.
     */
    @Test
    public void testToInfoString() {
        System.out.println("toInfoString");
        String expResult = String.format("Name: %s%n Email: %s%n", "testName", "test@Email");
        String result = u.toInfoString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User();
        String expResult = String.format("Name: %s%n Email: %s%n Password: %s%n", "testName", "test@Email", String.valueOf("testPassword"));
        String result = u.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User. Tests the case where its the same
     * object.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object o = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");
        boolean expResult = true;
        boolean result = u.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEqualsFalseNotSameInstance() {
        System.out.println("equals");
        Object o = 10.0;
        boolean expResult = false;
        boolean result = u.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEqualsFalseUserName() {
        System.out.println("equals");
        Object o = new User("testUser", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");
        boolean expResult = false;
        boolean result = u.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEqualsFalseEmail() {
        System.out.println("equals");
        Object o = new User("testUserName", "test@Eail", "testPassword", "testName", "portuguese", "GMT+1", "key");
        boolean expResult = false;
        boolean result = u.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEqualsFalsePassword() {
        System.out.println("equals");
        Object o = new User("testUserName", "test@Email", "testsword", "testName", "portuguese", "GMT+1", "key");
        boolean expResult = false;
        boolean result = u.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEqualsFalseName() {
        System.out.println("equals");
        Object o = new User("testUserName", "test@Email", "testPassword", "teme", "portuguese", "GMT+1", "key");
        boolean expResult = false;
        boolean result = u.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");
        int expResult = user.hashCode();
        int result = u.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of encryptData method, of class User.
     */
    @Test
    public void testEncryptData() {
        System.out.println("encryptData");
        User instance = u;
        instance.encryptData();
        u.encryptData();
        assertEquals(instance, u);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of decryptData method, of class User.
     */
    @Test
    public void testDecryptData() {
        System.out.println("decryptData");
        System.out.println("encryptData");
        User instance = u;
        instance.encryptData();
        instance.decryptData();
        assertEquals(instance, u);
    }
}
