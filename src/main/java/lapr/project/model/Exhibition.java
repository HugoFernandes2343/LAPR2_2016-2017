/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

/**
 *
 * @author Hugo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Exhibition extends Event {
    
    @XmlElement
    private String eventType;
    
    private static final String EVENT_TYPE = "Exhibition";

    public Exhibition(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {
        super(title, description, place, startDate, endDate, applicationBegin, applicationEnd);
        this.eventType = EVENT_TYPE;
    }

    public Exhibition(){
        //to avoid xml conflicts
    }
    
    @Override
    public String toString() {
        return String.format("Event type: %s%n", eventType) + super.toString();
    }

    @Override
    public Node exportContentToXMLNode() throws ParserConfigurationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Event importContentFromXMLNode(Node node) throws ParserConfigurationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
