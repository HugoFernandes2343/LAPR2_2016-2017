/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Hugo
 */
public class CalculationsTest {

    public CalculationsTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getEventStandsFrequencyTable method, of class Calculations. Tests
     * the example result given in class.
     */
    @Test
    public void testGetEventStandsFrequencyTable() {
        System.out.println("getEventStandsFrequencyTable");
        double[] areas = {37, 103, 59, 60, 103, 32, 44, 117, 34, 102, 40, 17, 67, 27, 52, 43, 125, 108, 15, 13};
        Object[] line0 = {"[13.0 ; 35.4[", 6, 30.0};
        Object[] line1 = {"[35.4 ; 57.8[", 5, 25.0};
        Object[] line2 = {"[57.8 ; 80.2[", 3, 15.0};
        Object[] line3 = {"[80.2 ; 102.6[", 1, 5.0};
        Object[] line4 = {"[102.6 ; 125.0]", 5, 25.0};
        Object[][] expResult = {line0, line1, line2, line3, line4};
        Object[][] result = Calculations.getEventStandsFrequencyTable(areas);
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of getMeanRate method, of class Calculations.
     */
    @Test
    public void testGetMeanRate() {
        System.out.println("getMeanRate");
        double[] areas = {37, 103, 59, 60, 103, 32, 44, 117, 34, 102, 40, 17, 67, 27, 52, 43, 125, 108, 15, 13};
        double expResult = 59.9;
        double result = Calculations.getMeanRate(areas);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getStandardDeviation method, of class Calculations.
     */
    @Test
    public void testGetStandardDeviation() {
        System.out.println("getStandardDeviation");
        double[] areas = {37, 103, 59, 60, 103, 32, 44, 117, 34, 102, 40, 17, 67, 27, 52, 43, 125, 108, 15, 13};
        double expResult = 35.68;
        double result = Calculations.getStandardDeviation(areas);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getVariance method, of class Calculations.
     */
    @Test
    public void testGetVariance() {
        System.out.println("getVariance");
        double[] areas = {37, 103, 59, 60, 103, 32, 44, 117, 34, 102, 40, 17, 67, 27, 52, 43, 125, 108, 15, 13};
        double meanRate = 59.9;
        double expResult = 1272.99;
        double result = Calculations.getVariance(areas, meanRate);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getEventKeywordsFrequencyTable method, of class Calculations.
     */
    @Test
    public void testGetEventKeywordsFrequencyTable() {
        System.out.println("getEventKeywordsFrequencyTable");
        String[] keywords = {"Memory", "memory", "memory", "Memory", "Delta", "Isep", "Memory", "Delta", "isep", "Memory", "Delta", "isep"};
        List<String> allKeywords = new ArrayList<>();
        for (int i = 0; i < keywords.length; i++) {
            allKeywords.add(keywords[i]);
        }
        Object[] line0 = {"Memory", 50.0};
        Object[] line1 = {"Delta", 25.0};
        Object[] line2 = {"Isep", 25.0};
        Object[][] expResult = {line0, line1, line2};
        Object[][] result = Calculations.getEventKeywordsFrequencyTable(allKeywords);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFrequency method, of class Calculations.
     */
    @Test
    public void testGetFrequency() {
        System.out.println("getFrequency");
        String element = "Memory";
        String[] keywords = {"Memory", "memory", "memory", "Memory", "Delta", "Isep", "Memory", "Delta", "isep", "Memory", "Delta", "isep"};
        List<String> allElements = new ArrayList<>();
        for (int i = 0; i < keywords.length; i++) {
            allElements.add(keywords[i]);
        }
        double expResult = 50.0;
        double result = Calculations.getFrequency(element, allElements);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFaeMeanRate method, of class Calculations.
     */
    @Test
    public void testGetFaeMeanRate() {
        System.out.println("getFaeMeanRate");
        Review review1 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        review1.setEventAdequacy(5);
        review1.setFaeTopicKnowledge(5);
        review1.setInviteAdequacy(5);
        review1.setRecommendation(5);
        Review review2 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        review2.setEventAdequacy(0);
        review2.setFaeTopicKnowledge(0);
        review2.setInviteAdequacy(0);
        review2.setRecommendation(0);
        List<Review> faeReviews = new ArrayList<>();
        faeReviews.add(review1);
        faeReviews.add(review2);
        double expResult = 2.5;
        double result = Calculations.getFaeMeanRate(faeReviews);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFaeMeanStandardDeviationRate method, of class Calculations.
     */
    @Test
    public void testGetFaeMeanStandardDeviationRate() {
        System.out.println("getFaeMeanStandardDeviationRate");
        double globalSubmissionMeanRate = 3.0;
        Review review1 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        review1.setEventAdequacy(5);
        review1.setFaeTopicKnowledge(5);
        review1.setInviteAdequacy(5);
        review1.setRecommendation(5);
        Review review2 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        review2.setEventAdequacy(0);
        review2.setFaeTopicKnowledge(0);
        review2.setInviteAdequacy(0);
        review2.setRecommendation(0);
        List<Review> faeReviews = new ArrayList<>();
        faeReviews.add(review1);
        faeReviews.add(review2);
        double expResult = 2.5;
        double result = Calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, faeReviews);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getFaeMeanStandardDeviation method, of class Calculations.
     */
    @Test
    public void testGetFaeMeanStandardDeviation() {
        System.out.println("getFaeMeanStandardDeviation");
        double globalSubmissionMeanRate = 3.0;
        Review review1 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        review1.setEventAdequacy(5);
        review1.setFaeTopicKnowledge(5);
        review1.setInviteAdequacy(5);
        review1.setRecommendation(5);
        Review review2 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        review2.setEventAdequacy(0);
        review2.setFaeTopicKnowledge(0);
        review2.setInviteAdequacy(0);
        review2.setRecommendation(0);
        List<Review> faeReviews = new ArrayList<>();
        faeReviews.add(review1);
        faeReviews.add(review2);
        double expResult = 0.5;
        double result = Calculations.getFaeMeanStandardDeviation(globalSubmissionMeanRate, faeReviews);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

}
