/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.*;

/**
 *
 * @author hugod
 */
@XmlRootElement(name="representative")
@XmlAccessorType(XmlAccessType.FIELD)
public class Representative {
    
    @XmlElement(name="user")
    private User u;
    
    public Representative(User u){
        this.u=u;
    }
    
    public Representative(){
        //Avoid xml conflicts
    }
    
    public User getUser(){
        return this.u;
    }
    
}
