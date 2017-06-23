/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class OrganizerTest {

    private static final Organizer org = new Organizer(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key"));

    public OrganizerTest() {
    }

    /**
     * Test of toString method, of class Organizer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");
        String expResult = user.toString();;
        String result = org.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class Organizer.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        User expResult = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");
        User result = org.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Organizer.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object o = new Organizer(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key"));;
        boolean expResult = true;
        boolean result = org.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Organizer.
     */
    @Test
    public void testEqualsFalseNotTheSameInstance() {
        System.out.println("equals");
        Object o = 1.0;
        boolean expResult = false;
        boolean result = org.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Organizer.
     */
    @Test
    public void testEqualsFalseUser() {
        System.out.println("equals");
        Object o = new Organizer(new User("Name", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key"));;;
        boolean expResult = false;
        boolean result = org.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Organizer.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Organizer instance = new Organizer(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key"));;;
        int expResult = org.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

}
