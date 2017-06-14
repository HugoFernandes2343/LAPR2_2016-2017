/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
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
    public  Event selectedEvent;

    public UC02Controller(FairCenter fc, User u) {
        this.user = u;
        this.fc = fc;
    }

    public ArrayList<Event> getEventsByOrganizer() {
        ArrayList<Event> listEventsNotFAEDefined;
        ArrayList<Event> eventListByOrganizer = new ArrayList<>();
        EventRegistry er = fc.getEventRegistry();
        listEventsNotFAEDefined = er.getEventsNotFAEDefined();
        for (Event e : listEventsNotFAEDefined) {
            if (e.getOrganizersList_UserRef().contains(user)) {
                eventListByOrganizer.add(e);
            }
        }
        return eventListByOrganizer;
    }

    public ArrayList<User> getUsersList() {
        UserRegistry ur = fc.getConfirmedUsers();
        ArrayList<User> userList = ur.getUsersList();
        return userList;
    }

    public void defineFAE(User u) {
        selectedEvent.createFAE(u);
        selectedEvent.getFAEList().validateFAE();
    }

    public void addFAE(boolean conf) {
        if (conf == true) {
            selectedEvent.getFAEList().addFAEs();
        } else {
            selectedEvent.getFAEList().discardFAE();
        }
    }

    public void registerFAEs() {
        selectedEvent.registerFAEs();
    }
    
}
