/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationList;
import lapr.project.model.Calculations;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;

/**
 *
 * @author LAPR2-G054
 */
public class UC42Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    protected Event selectedEvent;

    /**
     * constructor of UC42 controllers
     *
     * @param fc faircenter in which the controller operates
     */
    public UC42Controller(FairCenter fc) {
        this.fc = fc;
        calculations = new Calculations();
    }

    /**
     * returns all events in the registry
     *
     * @return list of events
     */
    public List<Event> getEvents() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getAllEvents();
        return eventList;
    }

    /**
     * set method of the selectedEvent attribute
     *
     * @param e event to be set as selectedEvent
     */
    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    /**
     * get method of the selectedEvent attribute
     *
     * @return selectedEvent
     */
    public Event getSelectedEvent() {
        return this.selectedEvent;
    }

    /**
     * returns event acceptance rate
     *
     * @return acceptance rate (double)
     */
    public double getEventAcceptanceRate() {
        ApplicationList applicationList = selectedEvent.getApplicationList();
        List<Application> applications = applicationList.getApplications();
        List<String> acceptanceList = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getAcceptance()) {
                acceptanceList.add("accepted");
            } else {
                acceptanceList.add("not accepted");
            }
        }
        return calculations.getFrequency("accepted", acceptanceList);
    }
}
