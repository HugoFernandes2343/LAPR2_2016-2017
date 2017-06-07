/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hugo
 */
public class Exhibition extends Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private String eventType;
    private static final String EVENT_TYPE = "Exhibition";

    public Exhibition(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {
        super(title, description, place, startDate, endDate, applicationBegin, applicationEnd);
        this.eventType = EVENT_TYPE;
    }
    
    
public String toString(){
return String.format("Event type: %s%n", eventType)+super.toString();
}
        
}
