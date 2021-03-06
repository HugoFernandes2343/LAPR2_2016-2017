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
public class EventApplicationEvaluatedState implements EventState {

    @XmlTransient
    private Event e;

    public EventApplicationEvaluatedState(Event e) {
        this.e = e;
    }

    public EventApplicationEvaluatedState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean validate() {
        return !e.getApplicationList().getDecidedApplications().isEmpty();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setEventEndedState() {
        return false;
    }
}
