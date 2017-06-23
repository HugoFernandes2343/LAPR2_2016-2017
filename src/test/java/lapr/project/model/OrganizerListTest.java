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
public class OrganizerListTest {


    public OrganizerListTest() {
    }

    /**
     * Test of addOrganizer method, of class OrganizerList.
     */
    @Test
    public void testAddOrganizer() {
        System.out.println("addOrganizer");
        User user = new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "key");;
        OrganizerList instance = new OrganizerList();
        instance.addOrganizer(user);
    }

    /**
     * Test of getList method, of class OrganizerList.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        OrganizerList instance = new OrganizerList();
        List<Organizer> expResult = new ArrayList<>();
        List<Organizer> result = instance.getList();
        assertEquals(expResult, result);
    }

}
