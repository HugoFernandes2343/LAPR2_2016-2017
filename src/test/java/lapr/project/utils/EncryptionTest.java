/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Hugo
 */
public class EncryptionTest {


    /**
     * Test of encrypt and decryption method, of class Encryption. Due to the
     * the random shift that the class Encryption generates, both methods need
     * to be tested at the same time.
     */
    @Test
    public void testEncryption() {
        Encryption instance = new Encryption("delta");
        System.out.println("encrypt");
        String word = "helloWorldImAnEngineer@isep.ipp.pt";
        String result = instance.encrypt(word);
        System.out.println("Given word: helloWorldImAnEngineer@isep.ipp.pt");
        System.out.println("Expected: helloWorldImAnEngineer@isep.ipp.pt");
        System.out.println("encryption Result: " + result);
        result = instance.decrypt(result);
        System.out.println("decryption Result: " + result);
        String expResult = "helloWorldImAnEngineer@isep.ipp.pt";
        assertEquals(expResult.trim(), result.trim());

    }

}
