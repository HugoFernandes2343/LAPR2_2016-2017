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
 * @author PC
 */
public class FAETest {
    
    private User u = new User("UserName","password".toCharArray(),"aName","key");
    
    /**
     * Test of getUser method, of class FAE.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        
        FAE instance = new FAE(u);
        User expResult = u;
        User result = instance.getUser();
        System.out.println(expResult.toString());
        System.out.println(result.toString());
        assertEquals(expResult, result);
    }

//    /**
//     * Test of setState method, of class FAE.
//     */
//    @Test
//    public void testSetState() {
//        System.out.println("setState");
//        FAEState state = null;
//        FAE instance = null;
//        instance.setState(state);
//    }

    /**
     * Test of valida method, of class FAE.
     */
    @Test
    public void testValida() {
        System.out.println("valida");
        FAE instance = new FAE(u);
        instance.valida();
        assertNotNull(instance.getState());
    }
    
}
