/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.EventState;

/**
 *
 * @author PC
 */
public class EventCreatedState implements EventState {

    public Event e;

    public EventCreatedState(Event e) {
        this.e = e;
    }

    @Override
    public boolean setEventCreatedState() {
        return false;
    }

    @Override
    public boolean setEventDefinedFAEState() {
        if(validate()){
            e.setState(new EventDefinedFAEState(e));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean validate() {
        //Specific
        return false;
    }

}
