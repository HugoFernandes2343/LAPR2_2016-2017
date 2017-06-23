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
 * @author LAPR2-G054
 */
public class UC02Controller {

    private User user;
    private FairCenter fc;
    protected Event selectedEvent;

    /**
     *
     * constructor of UC02
     *
     * @param fc fair center in which the controller operates
     * @param u user that is using the UC02 features
     */
    public UC02Controller(FairCenter fc, User u) {
        this.user = u;
        this.fc = fc;
    }

    /**
     * returns the events in which the user that is using the funcionallity
     *
     * @return list of events
     */
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

    /**
     * returns all confirmed users
     *
     * @return list of users
     */
    public List<User> getUsersList() {
        UserRegistry ur = fc.getConfirmedUsers();
        return ur.getUsersList();
    }

    /**
     * creates an object FAE
     *
     * @param u user that will become an FAE
     */
    public void defineFAE(User u) {
        selectedEvent.createFAE(u);
    }

    /**
     * Either saves or discards the created FAEs
     *
     * @param conf confirmation of saving or not the created FAEs
     */
    public void addFAE(boolean conf) {
        if (conf) {
            selectedEvent.getFAEList().addFAEs();
        } else {
            selectedEvent.discardFAEs();
        }
    }

    /**
     * registers FAEs in the event
     */
    public void registerFAEs() {
        selectedEvent.registerFAEs();
        selectedEvent.state.setEventDefinedFAEState();
    }

    /**
     * set method of the object selectedEvent
     *
     * @param e event to set as selectedEvent
     */
    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    /**
     * get method of the object selectedEvent
     *
     * @return selectedEvent
     */
    public Event getSelectedEvent() {
        return this.selectedEvent;
    }

}
