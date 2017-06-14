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
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventManager {

    @XmlElement(name="user")
    private User u;

    public EventManager(User u) {
        this.u = u;
    }

    public EventManager() {
        //to avoid xml conflicts
    }

    public User getUser() {
        return this.u;
    }
    
}
