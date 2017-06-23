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
 * @author PC
 */
@XmlRootElement
public class EventEndedState implements EventState {

    @XmlElement
    private Event e;

    public EventEndedState(Event e) {
        this.e = e;
    }
    
    public EventEndedState(){
        //Avoid xml conflicts
    }

    @Override
    public boolean validate() {
        return e != null;
    }

    @Override
    public boolean setEventCreatedState() {
        return false;
    }

    @Override
    public boolean setEventDefinedFAEState() {
        return false;
    }

    @Override
    public boolean setEventApplicationsOpenState() {
        return false;
    }

    @Override
    public boolean setEventApplicationsEvaluatedState() {
        return false;
    }

    @Override
    public boolean setEventFinalState() {
        return false;
    }

    @Override
    public boolean setEventEndedState() {
        return false;
    }

}
