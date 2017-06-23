/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.Stand;
import lapr.project.model.User;

/**
 *
 * @author LAPR2-G054
 */
public class UC20Controller {

    private final User user;
    private final FairCenter fc;
    private Event event;
    private Stand stand;

    /**
     * constructor of UC20 controllers
     *
     * @param fc faircenter in which the controller operates
     * @param u user that is using the UC20 feature
     */
    public UC20Controller(FairCenter fc, User u) {
        this.user = u;
        this.fc = fc;
    }

    /**
     * returns events in which the user is organizer
     *
     * @return list of events
     */
    public List<Event> getOrganizerEvents() {
        List<Event> organizerEvents = fc.getEventRegistry().getEventsByOrganizer(user);
        return organizerEvents;
    }

    /**
     * set method of the event attribute
     *
     * @param event to be set as event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * creates a stand
     *
     * @param area area of the new stand
     * @param description description of the new stand
     * @return string with the information from the new stand
     */
    public String createStand(double area, String description) {
        this.stand = new Stand(area, description);
        return stand.toString();
    }

    /**
     * registers stand
     *
     * @return boolean if the operation is successful false if it is not
     */
    public boolean registerStand() {
        if (event.addStand(stand)) {
            return true;
        } else {
            return false;
        }
    }
}
