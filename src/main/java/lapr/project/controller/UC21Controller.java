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
 * @author LAPR2-G054
 */
public class UC21Controller {

    private final User user;
    private final FairCenter fc;
    private Event event;
    private Application application;
    private Stand stand;

    /**
     * constructor of UC21 controllers
     *
     * @param fc faircenter in which the controller operates
     * @param u user that is using the UC21 feature
     */
    public UC21Controller(FairCenter fc, User u) {
        this.user = u;
        this.fc = fc;
    }

    /**
     * get the events in which the user that is using the funcionallity is
     * organizer
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
     * returns a list of applications that are in the evaluated state
     *
     * @return list of applications
     */
    public List<Application> getApplicationsEvaluatedState() {
        ApplicationList applications = event.getApplicationList();
        return applications.getApplicationsEvaluatedState();
    }

    /**
     * set method of the attribute application
     *
     * @param application to set as application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * returns the available stands of the event
     *
     * @return list of stands
     */
    public List<Stand> getEventAvailableStands() {
        return event.getAvailableStands();
    }

    /**
     * set method of the attribute stand
     *
     * @param stand to set as stand
     */
    public void setStand(Stand stand) {
        this.stand = stand;
    }

    /**
     * return information of assignment
     *
     * @return string with the assignment information
     */
    public String showStandAssingmentCreated() {
        return "The application from " + application.getTradeName() + " will be assigned the stand " + stand.getDescription() + " with an area of " + Double.toString(stand.getArea());
    }

    /**
     * register stand assingment
     *
     * @return true if it is succeffully registered
     */
    public boolean registerStandAssignment() {
        application.setStand(stand);
        return application.getState().setApplicationGivenStandState();
    }
}
