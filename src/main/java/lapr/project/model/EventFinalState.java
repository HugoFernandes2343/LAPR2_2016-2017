/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Date;
import javax.xml.bind.annotation.*;
import lapr.project.utils.EventState;

/**
 *
 * @author PC
 */
@XmlRootElement
public class EventFinalState implements EventState {

    @XmlTransient
    private Event e;

    public EventFinalState(Event e) {
        this.e = e;
    }

    public EventFinalState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean validate() {
        Date now = new Date();
        return now.after(e.getApplicationEndDate());
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
        if (validate()) {
            e.setState(new EventEndedState(e));
            return true;
        } else {
            return false;
        }
    }

}
