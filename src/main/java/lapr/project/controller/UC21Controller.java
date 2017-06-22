/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationList;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.Stand;
import lapr.project.model.User;

/**
 *
 * @author hugod
 */
public class UC21Controller {

    private final User user;
    private final FairCenter fc;
    private Event event;
    private Application application;
    private Stand stand;

    /**
     *
     * @param fc
     * @param u
     */
    public UC21Controller(FairCenter fc, User u) {
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

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Application> getApplicationsEvaluatedState() {
        ApplicationList applications = event.getApplicationList();
        return applications.getApplicationsEvaluatedState();
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Stand> getEventAvailableStands() {
        return event.getAvailableStands();
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public String showStandAssingmentCreated() {
        return "The application from " + application.getTradeName() + " will be assigned the stand " + stand.getDescription() + " with an area of " + Double.toString(stand.getArea());
    }
    public boolean registerStandAssignment(){
       application.setStand(stand); 
       return application.getState().setApplicationGivenStandState();
    }
}
