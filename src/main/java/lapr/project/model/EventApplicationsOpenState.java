/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import javax.xml.bind.annotation.*;
import lapr.project.utils.EventState;


/**
 *
 * @author hugod
 */
@XmlRootElement
public class EventApplicationsOpenState implements EventState{
     @XmlTransient
    public Event e;
    
    public EventApplicationsOpenState(Event e){
        this.e=e;
    }
    
    public EventApplicationsOpenState(){
        //Avoiding xml conflicts
    }
    
    @Override
    public boolean setEventDefinedFAEState() {
        return false;
    }

  /*  public boolean setEventApplicationsEvaluatingState() {
         if (validate()) {
            e.setState(new EventApplicationsEvaluatingState(e));
            return true;
        } else {
            return false;
        }
    }*/

    @Override
    public boolean validate() {
        //Specific
        return false;
    }

    @Override
    public boolean setEventCreatedState() {
        return false;
    }

    @Override
    public boolean setEventApplicationsOpenState() {
        return false;
    }
}
