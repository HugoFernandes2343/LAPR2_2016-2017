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
public class EventDefinedFAEState implements EventState {

    @XmlTransient
    protected Event e;

    public EventDefinedFAEState(Event e) {
        this.e = e;
    }

    public EventDefinedFAEState() {
        //Avoiding xml conflicts
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
    public boolean validate() {
        return !e.getFAEList().getList().isEmpty();
    }

    @Override
    public boolean setEventApplicationsOpenState() {
        if (validate()) {
            e.setState(new EventApplicationsOpenState(e));
            return true;
        } else {
            return false;
        }
    }
}
