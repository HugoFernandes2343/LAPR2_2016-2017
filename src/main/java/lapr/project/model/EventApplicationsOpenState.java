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
public class EventApplicationsOpenState implements EventState {

    @XmlTransient
    protected Event e;

    public EventApplicationsOpenState(Event e) {
        this.e = e;
    }

    public EventApplicationsOpenState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean setEventDefinedFAEState() {
        return false;
    }

    @Override
    public boolean validate() {
        Date now = new Date();
        return now.after(e.getApplicationBeginDate());
    }

    @Override
    public boolean setEventCreatedState() {
        return false;
    }

    @Override
    public boolean setEventApplicationsOpenState() {
        return false;
    }

    @Override
    public boolean setEventApplicationsEvaluatedState() {
        if (validate()) {
            e.setState(new EventApplicationEvaluatedState(e));
            return true;
        } else {
            return false;
        }
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
