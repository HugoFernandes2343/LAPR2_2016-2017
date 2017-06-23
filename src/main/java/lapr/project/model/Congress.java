/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import javax.xml.bind.annotation.*;

/**
 *
 * @author LAPR2-G54
 */
@XmlRootElement
@XmlSeeAlso(Event.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Congress extends Event {

    @XmlElement
    private String eventType;

    private static final String EVENT_TYPE = "Congress";
/**
 * creates a congress
 * @param title  to assing to created congress
 * @param description to assing to created congress
 * @param place to assing to created congress
 * @param startDate to assing to created congress
 * @param endDate to assing to created congress
 * @param applicationBegin to assing to created congress 
 * @param applicationEnd to assing to created congress
 */
    public Congress(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {
        super(title, description, place, startDate, endDate, applicationBegin, applicationEnd);
        this.eventType = EVENT_TYPE;
    }
/**
 * constructor to avoid xml conflicts
 */
    public Congress() {
        //to avoid xml conflicts
    }
/**
 * override of toString method
 * @return information of congress
 */
    @Override
    public String toString() {
        return String.format("Event type:  %s%n", eventType) + super.toString();
    }

}
