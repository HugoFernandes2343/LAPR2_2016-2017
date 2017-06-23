/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;

/**
 *
 * @author PC
 */
public class UC02Controller {

    private User user;
    private FairCenter fc;
    protected  Event selectedEvent;

    public UC02Controller(FairCenter fc, User u) {
        this.user = u;
        this.fc = fc;
    }

    public List<Event> getEventsByOrganizer() {
        List<Event> listEventsNotFAEDefined;
        List<Event> eventListByOrganizer = new ArrayList<>();
        EventRegistry er = fc.getEventRegistry();
        listEventsNotFAEDefined = er.getEventsNotFAEDefined();
        for (Event e : listEventsNotFAEDefined) {
            if (e.getOrganizersListUserRef().contains(user)) {
                eventListByOrganizer.add(e);
            }
        }
        return eventListByOrganizer;
    }

    public List<User> getUsersList() {
        UserRegistry ur = fc.getConfirmedUsers();
        return ur.getUsersList();
    }

    public void defineFAE(User u) {
        selectedEvent.createFAE(u);
    }

    public void addFAE(boolean conf) {
        if (conf) {
            selectedEvent.getFAEList().addFAEs();
        } else {
            selectedEvent.discardFAEs();
        }
    }

    public void registerFAEs() {
        selectedEvent.registerFAEs();
        selectedEvent.state.setEventDefinedFAEState();
    }
    
    public void setSelectedEvent(Event e){
        this.selectedEvent=e;
    }
    
    public Event getSelectedEvent(){
        return this.selectedEvent;
    }
    
}
