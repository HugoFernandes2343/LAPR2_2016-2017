/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Hugo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Place {
    
    @XmlElement
    private String localName;

    public Place(String localName) {
        this.localName = localName;
    }
    
    public Place(){
        
    }
    
    public String toString(){
    
    return String.format("%s",localName);
    }
}
