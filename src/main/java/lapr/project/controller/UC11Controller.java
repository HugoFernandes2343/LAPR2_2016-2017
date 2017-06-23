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
import lapr.project.model.User;

/**
 *
 * @author hugod
 */
public class UC11Controller {

    private FairCenter fc;
    private Event event;
    private User user;
    private ApplicationList applicationList;
    private Application application;

    public UC11Controller(FairCenter fc,User u) {
        this.fc = fc;
        this.user=u;
    }

    public boolean checkForRepresentativeApplications() {
        return fc.getEventRegistry().checkForRepresentativeApplications(user);
    }

    public List<Event> getEventsWithApplicationsFromUser() {
        List<Event> events = fc.getEventRegistry().getEventsWithApplicationFromUser(user);
        return events;
    }
    
    public String[] getTitlesOfEvents(List<Event> eventsList) {
        return fc.getEventRegistry().getEventTitles(eventsList);
    }

    public void setEvent(Event event) {
        this.event = event;
        //this.application = event.getApplicationOfRepresentative(user);

    }

    public void createApplication(String tradeName, String description, String address, long phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        this.applicationList = event.getApplicationList();
        this.application = new Application(tradeName, description, address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);

    }

    public String getApplicationInfo() {
        return application.toString();
    }

    public void registerApplicationChanges() {
        event.saveApplicationChanges(application);
    }
 
}
