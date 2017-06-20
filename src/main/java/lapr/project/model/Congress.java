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
 * @author PC
 */
@XmlRootElement
@XmlSeeAlso(Event.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Congress extends Event {
    
    @XmlElement
    private String eventType;
    
    private static final String EVENT_TYPE = "Congress";

    public Congress(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {
        super(title, description, place, startDate, endDate, applicationBegin, applicationEnd);
        this.eventType = EVENT_TYPE;
    }
    
    public Congress(){
        //to avoid xml conflicts
    }

    @Override
    public String toString() {
        return String.format("Event type:  %s%n", eventType) + super.toString();
    }

}
