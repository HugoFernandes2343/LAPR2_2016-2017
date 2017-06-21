/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hugo
 */
@XmlRootElement(name = "review")
@XmlAccessorType(XmlAccessType.FIELD)
public class Review {

    @XmlElement(name = "text")
    private String justification;

    @XmlElement
    private Integer faeTopicKnowledge;

    @XmlElement
    private Integer eventAdequacy;

    @XmlElement
    private Integer inviteAdequacy;

    @XmlElement
    private Integer recommendation;

    @XmlElement
    private Assignment assignment;

    private Review() {
        //Avoid xml conflicts
    }

    public Review(FAE FAE) {
        this.assignment = new Assignment(FAE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }

        Review review = (Review) o;

        if (!justification.equals(review.justification)) {
            return false;
        }
        if (!faeTopicKnowledge.equals(review.faeTopicKnowledge)) {
            return false;
        }
        if (!eventAdequacy.equals(review.eventAdequacy)) {
            return false;
        }
        if (!inviteAdequacy.equals(review.inviteAdequacy)) {
            return false;
        }
        if (!recommendation.equals(review.recommendation)) {
            return false;
        }
        return assignment.equals(review.assignment);

    }

    @Override
    public int hashCode() {
        int result = justification.hashCode();
        result = 31 * result + faeTopicKnowledge.hashCode();
        result = 31 * result + eventAdequacy.hashCode();
        result = 31 * result + inviteAdequacy.hashCode();
        result = 31 * result + recommendation.hashCode();
        result = 31 * result + assignment.hashCode();
        return result;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    /**
     * @param justification the text to set
     */
    public void setJustification(String justification) {
        this.justification = justification;
    }

    /**
     * @param faeTopicKnowledge the faeTopicKnowledge to set
     */
    public void setFaeTopicKnowledge(Integer faeTopicKnowledge) {
        this.faeTopicKnowledge = faeTopicKnowledge;
    }

    /**
     * @param eventAdequacy the eventAdequacy to set
     */
    public void setEventAdequacy(Integer eventAdequacy) {
        this.eventAdequacy = eventAdequacy;
    }

    /**
     * @param inviteAdequacy the inviteAdequacy to set
     */
    public void setInviteAdequacy(Integer inviteAdequacy) {
        this.inviteAdequacy = inviteAdequacy;
    }

    /**
     * @return the recommendation
     */
    public Integer getRecomendationValue() {
        return recommendation;
    }

    /**
     * @param recommendation the recommendation to set
     */
    public void setRecommendation(Integer recommendation) {
        this.recommendation = recommendation;
    }

    public boolean hasFaeEvaluated() {
        return getFaeTopicKnowledgeValue() != null;
    }

    /**
     * @return the faeTopicKnowledge
     */
    public Integer getFaeTopicKnowledgeValue() {
        return faeTopicKnowledge;
    }

    /**
     * @return the eventAdequacy
     */
    public Integer getEventAdequacyValue() {
        return eventAdequacy;
    }

    /**
     * @return the inviteAdequacy
     */
    public Integer getInviteAdequacyValue() {
        return inviteAdequacy;
    }
}
