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
 * @author Hugo
 */
public class UC42Controller {

    private final FairCenter fc;
    protected Event selectedEvent;

    public UC42Controller(FairCenter fc) {
        this.fc = fc;
    }

    public List<Event> getEvents() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getAllEvents();
        return eventList;
    }

    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    public Event getSelectedEvent() {
        return this.selectedEvent;
    }

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
        return Calculations.getFrequency("accepted", acceptanceList);
    }
}
