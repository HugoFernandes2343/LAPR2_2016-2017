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
public class StandTest {

    private static final Stand stand = new Stand(10.0, "stand");

    public StandTest() {
    }

    /**
     * Test of toString method, of class Stand.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Area:" + 10.0 + "Description:" + "stand";
        String result = stand.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Stand.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "stand";
        String result = stand.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArea method, of class Stand.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        double expResult = 10.0;
        double result = stand.getArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class Stand. Tests if its the same object.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object o = new Stand(10.0, "stand");
        boolean expResult = true;
        boolean result = stand.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Stand.
     */
    @Test
    public void testEqualsFalseNotTheSameInstance() {
        System.out.println("equals");
        Object o = 10;
        boolean expResult = false;
        boolean result = stand.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Stand.
     */
    @Test
    public void testEqualsFalseArea() {
        System.out.println("equals");
        Object o = new Stand(15.0, "stand");
        boolean expResult = false;
        boolean result = stand.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Stand.
     */
    @Test
    public void testEqualsFalseDescription() {
        System.out.println("equals");
        Object o = new Stand(10.0, "stand2");
        boolean expResult = false;
        boolean result = stand.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Stand.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Stand instance = new Stand(10.0, "stand");
        int expResult = stand.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

}
