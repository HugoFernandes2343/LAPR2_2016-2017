/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class FairCenterTest {

    public FairCenterTest() {
    }

    /**
     * Test of getConfirmedUsers method, of class FairCenter.
     */
    @Test
    public void testGetConfirmedUsers() {
        System.out.println("getConfirmedUsers");
        FairCenter instance = new FairCenter();
        UserRegistry expResult = instance.getConfirmedUsers();
        UserRegistry result = instance.getConfirmedUsers();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getEventRegistry method, of class FairCenter.
//     */
//    @Test
//    public void testGetEventRegistry() {
//        System.out.println("getEventRegistry");
//        FairCenter instance = new FairCenter();
//        EventRegistry expResult = instance.getEventRegistry();
//        EventRegistry result = instance.getEventRegistry();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of registerEvent method, of class FairCenter.
//     */
//    @Test
//    public void testRegisterEvent() {
//        System.out.println("registerExhibition");
//        Event event = new Exhibition();
//        FairCenter instance = new FairCenter();
//        boolean expResult = true;
//        boolean result = instance.registerEvent(event);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of registerEvent method, of class FairCenter.
//     */
//    @Test
//    public void testRegisterEventCongress() {
//        System.out.println("registerCongress");
//        Event event = new Congress();
//        FairCenter instance = new FairCenter();
//        boolean expResult = true;
//        boolean result = instance.registerEvent(event);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getEventManagerRegistry method, of class FairCenter.
//     */
//    @Test
//    public void testGetEventManagerRegistry() {
//        System.out.println("getEventManagerRegistry");
//        FairCenter instance = new FairCenter();
//        EventManagerRegistry expResult = new EventManagerRegistry();
//        EventManagerRegistry result = instance.getEventManagerRegistry();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of registerUser method, of class FairCenter.
//     */
//    @Test
//    public void testRegisterUser() {
//        System.out.println("registerUser");
//        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword");
//        FairCenter instance = new FairCenter();
//        boolean expResult = true;
//        boolean result = instance.registerUser(user);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of encryptUsers method, of class FairCenter.
//     */
//    @Test
//    public void testEncryptUsers() {
//        System.out.println("encryptUsers");
//        FairCenter instance = new FairCenter();
//        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword");
//        instance.registerUser(user);
//        instance.encryptUsers();
//
//    }
//
//    /**
//     * Test of decryptUsers method, of class FairCenter.
//     */
//    @Test
//    public void testDecryptUsers() {
//        System.out.println("decryptUsers");
//        FairCenter instance = new FairCenter();
//        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword");
//        instance.registerUser(user);
//        instance.decryptUsers();
//    }

}
