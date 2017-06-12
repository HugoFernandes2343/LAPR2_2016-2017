/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Organizer {

    @XmlElement
    private User user;

    public Organizer(User user) {
        this.user = user;
    }

    public Organizer() {
        //Avoiding xml conflicts
    }
    
    public String toString() {
        return user.toString();
    }

    public User getUser() {
        return this.user;
    }

}
