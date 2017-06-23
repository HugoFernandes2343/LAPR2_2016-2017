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
public class ReviewTest {

    private static final Review review = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
    private static final Review review2 = new Review(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));

    public ReviewTest() {
    }

    /**
     * Test of hasFaeEvaluated method, of class Review.
     */
    @Test
    public void testHasFaeEvaluatedFalse() {
        System.out.println("hasFaeEvaluated");
        boolean expResult = false;
        boolean result = review2.hasFaeEvaluated();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Review.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object o = review;
        boolean expResult = true;
        boolean result = review.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Review.
     */
    @Test
    public void testEqualsFalseNotTheSameInstance() {
        System.out.println("equals");
        Object o = 10.0;
        boolean expResult = false;
        boolean result = review.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssignment method, of class Review.
     */
    @Test
    public void testGetAssignment() {
        System.out.println("getAssignment");
        Assignment expResult = new Assignment(new FAE(new User("testUserName", "test@Email", "testPassword", "testName", "portuguese", "GMT+1", "keyword")));
        Assignment result = review.getAssignment();
        assertEquals(expResult, result);
    }

    /**
     * Test of setJustification method, of class Review.
     */
    @Test
    public void testSetJustification() {
        System.out.println("setJustification");
        String justification = "Aproved";
        review.setJustification(justification);
    }

    /**
     * Test of setFaeTopicKnowledge method, of class Review.
     */
    @Test
    public void testSetFaeTopicKnowledge() {
        System.out.println("setFaeTopicKnowledge");
        Integer faeTopicKnowledge = 4;
        review.setFaeTopicKnowledge(faeTopicKnowledge);
    }

    /**
     * Test of setEventAdequacy method, of class Review.
     */
    @Test
    public void testSetEventAdequacy() {
        System.out.println("setEventAdequacy");
        Integer eventAdequacy = 3;
        review.setEventAdequacy(eventAdequacy);
    }

    /**
     * Test of setInviteAdequacy method, of class Review.
     */
    @Test
    public void testSetInviteAdequacy() {
        System.out.println("setInviteAdequacy");
        Integer inviteAdequacy = 2;
        review.setInviteAdequacy(inviteAdequacy);
    }

    /**
     * Test of setRecommendation method, of class Review.
     */
    @Test
    public void testSetRecommendation() {
        System.out.println("setRecommendation");
        Integer recommendation = 4;
        review.setRecommendation(recommendation);
    }

    /**
     * Test of getRecomendationValue method, of class Review.
     */
    @Test
    public void testGetRecomendationValue() {
        System.out.println("getRecomendationValue");
        Integer expResult = 4;
        Integer result = review.getRecomendationValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFaeTopicKnowledgeValue method, of class Review.
     */
    @Test
    public void testGetFaeTopicKnowledgeValue() {
        System.out.println("getFaeTopicKnowledgeValue");
        Integer expResult = null;
        Integer result = review.getFaeTopicKnowledgeValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasFaeEvaluated method, of class Review.
     */
    @Test
    public void testHasFaeEvaluatedTrue() {
        System.out.println("hasFaeEvaluated");
        review.setFaeTopicKnowledge(2);
        boolean expResult = true;
        boolean result = review.hasFaeEvaluated();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventAdequacyValue method, of class Review.
     */
    @Test
    public void testGetEventAdequacyValue() {
        System.out.println("getEventAdequacyValue");
        Integer expResult = null;
        Integer result = review.getEventAdequacyValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInviteAdequacyValue method, of class Review.
     */
    @Test
    public void testGetInviteAdequacyValue() {
        System.out.println("getInviteAdequacyValue");
        Integer expResult = 2;
        Integer result = review.getInviteAdequacyValue();
        assertEquals(expResult, result);
    }

}
