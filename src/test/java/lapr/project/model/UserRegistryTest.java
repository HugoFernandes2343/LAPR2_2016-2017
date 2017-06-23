/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hugo
 */
public class UserRegistryTest {

    public UserRegistryTest() {
    }

    /**
     * Test of getUsersList method, of class UserRegistry.
     */
    @Test
    public void testGetUsersList() {
        System.out.println("getUsersList");
        UserRegistry instance = new UserRegistry();
        List<User> expResult = new ArrayList<>();
        List<User> result = instance.getUsersList();
        assertEquals(expResult, result);

    }

    /**
     * Test of registerUser method, of class UserRegistry.
     */
    @Test
    public void testRegisterUserSuccessfull() {
        System.out.println("registerUser");
        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword");
        UserRegistry instance = new UserRegistry();
        boolean expResult = true;
        boolean result = instance.registerUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of decryptAll method, of class UserRegistry.
     */
    @Test
    public void testDecryptAll() {
        System.out.println("decryptAll");
        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword");
        User expResult = user;
        UserRegistry instance = new UserRegistry();
        instance.registerUser(user);
        instance.encryptAll();
        instance.decryptAll();
        User result = instance.getUsersList().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of encryptAll method, of class UserRegistry.
     */
    @Test
    public void testEncryptAll() {
        System.out.println("decryptAll");
        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword");
        user.encryptData();
        User expResult = user;
        UserRegistry instance = new UserRegistry();
        instance.registerUser(user);
        instance.encryptAll();
        User result = instance.getUsersList().get(0);
        assertEquals(expResult, result);
    }

}
