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
 * @author hugod
 */
public class UC20Controller {

    private final User user;
    private final FairCenter fc;
    private Event event;
    private Stand stand;

    /**
     *
     * @param fc
     * @param u
     */
    public UC20Controller(FairCenter fc, User u) {
        this.user = u;
        this.fc = fc;
    }

    /**
     *
     * @param u
     * @return
     */
    public List<Event> getOrganizerEvents(User u) {
        List<Event> organizerEvents = fc.getEventRegistry().getEventsByOrganizer(u);
        return organizerEvents;
    }

    /**
     *
     * @param event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     *
     * @param area
     * @param descricao
     * @return
     */
    public String createStand(double area, String descricao) {
        this.stand = new Stand(area, descricao);
        return stand.toString();
    }

    /**
     *
     * @return
     */
    public boolean registerStand() {
        if (event.addStand(stand)) {
            return true;
        } else {
            return false;
        }
    }
}
