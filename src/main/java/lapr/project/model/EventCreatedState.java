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
public class EventCreatedState implements EventState {

    @XmlTransient
    protected Event e;

    public EventCreatedState(Event e) {
        this.e = e;
    }

    public EventCreatedState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean setEventCreatedState() {
        return false;
    }

    @Override
    public boolean setEventDefinedFAEState() {
        if (validate() == true) {
            e.setState(new EventDefinedFAEState(e));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validate() {
        return e.getOrganizerList().getList().isEmpty() == false;
    }

    @Override
    public boolean setEventApplicationsOpenState() {
        return false;
    }

    /*@Override
    public boolean setEventApplicationsEvaluatingState() {
        return false;
    }*/
}
