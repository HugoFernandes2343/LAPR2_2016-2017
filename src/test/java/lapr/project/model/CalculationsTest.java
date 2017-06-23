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
public class CalculationsTest {

    private final Calculations calculations;

    public CalculationsTest() {
        calculations = new Calculations();

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
        Object[][] result = calculations.getEventStandsFrequencyTable(areas);
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
        double result = calculations.getMeanRate(areas);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStandardDeviation method, of class Calculations.
     */
    @Test
    public void testGetStandardDeviation() {
        System.out.println("getStandardDeviation");
        double[] areas = {37, 103, 59, 60, 103, 32, 44, 117, 34, 102, 40, 17, 67, 27, 52, 43, 125, 108, 15, 13};
        double expResult = 35.68;
        double result = calculations.getStandardDeviation(areas);
        assertEquals(expResult, result, 0.0);
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
        double result = calculations.getVariance(areas, meanRate);
        assertEquals(expResult, result, 0.0);
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
        Object[][] result = calculations.getEventKeywordsFrequencyTable(allKeywords);
        assertArrayEquals(expResult, result);
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
        double result = calculations.getFrequency(element, allElements);
        assertEquals(expResult, result, 0.0);
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
        double result = calculations.getFaeMeanRate(faeReviews);
        assertEquals(expResult, result, 0.0);
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
        double result = calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, faeReviews);
        assertEquals(expResult, result, 0.0);
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
        double result = calculations.getFaeMeanStandardDeviation(globalSubmissionMeanRate, faeReviews);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCriticalValueUnilateralTest method, of class Calculations.
     */
    @Test
    public void testGetCriticalValueUnilateralTest() {
        System.out.println("getCriticalValueUnilateralTest");
        int significance = 5;
        Calculations instance = new Calculations();
        double expResult = 1.65;
        double result = instance.getCriticalValueUnilateralTest(significance);
        assertEquals(expResult, result, 0.01);

    }

    /**
     * Test of getTestStatistic method, of class Calculations.
     */
    @Test
    public void testGetTestStatistic() {
        System.out.println("getTestStatistic");
        int sample = 30;
        int nAccepted = 12;
        Calculations instance = new Calculations();
        Object expResult = -1.095;
        Object result = instance.getTestStatistic(sample, nAccepted);
        assertEquals((double) expResult, (double) result, 0.001);
    }

    /**
     * Test of getDiferenceTestStatistic method, of class Calculations.
     */
    @Test
    public void testGetDiferenceTestStatistic() {
        System.out.println("getDiferenceTestStatistic");
        int sample1 = 30;
        int nAccepted1 = 12;
        int sample2 = 30;
        int nAccepted2 = 20;
        Calculations instance = new Calculations();
        Object expResult = -2.148;
        Object result = instance.getDiferenceTestStatistic(sample1, nAccepted1, sample2, nAccepted2);
        assertEquals((double) expResult, (double) result, 0.001);
    }

    /**
     * Test of getCriticalValueBylateralTest method, of class Calculations.
     */
    @Test
    public void testGetCriticalValueBylateralTest() {
        System.out.println("getCriticalValueBylateralTest");
        int significance = 5;
        Calculations instance = new Calculations();
        double expResult = 1.96;
        double result = instance.getCriticalValueBylateralTest(significance);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getTestStatisticFae method, of class Calculations.
     */
    @Test
    public void testGetTestStatisticFae() {
        System.out.println("getTestStatisticFae");
        double faeMeanStandardDeviationRate = 0.610;
        double faeMeanStandardDeviation = 0.529;
        int sample = 30;
        Calculations instance = new Calculations();
        Object expResult = -4.045;
        Object result = instance.getTestStatisticFae(faeMeanStandardDeviationRate, faeMeanStandardDeviation, sample);
        assertEquals((double) expResult, (double) result, 0.01);

    }

    /**
     * Test of getDiferenceTestStatisticFae method, of class Calculations.
     */
    @Test
    public void testGetDiferenceTestStatisticFae() {
        System.out.println("getDiferenceTestStatisticFae");
        int sample1 = 30;
        double faeMeanDeviationRate1 = 0.610;
        double standardDeviation1 = Math.sqrt(0.279);
        int sample2 = 30;
        double faeMeanDeviationRate2 = 0.666;
        double standardDeviation2 = Math.sqrt(0.285);
        Calculations instance = new Calculations();
        Object expResult = -0.411;
        Object result = instance.getDiferenceTestStatisticFae(sample1, faeMeanDeviationRate1, standardDeviation1, sample2, faeMeanDeviationRate2, standardDeviation2);
        assertEquals((double) expResult, (double) result, 0.01);
    }

}
