/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author LAPR2-G054
 */
public class UC11Controller {

    private final FairCenter fc;
    private Event event;
    private final User user;
    private Application application;

    /**
     *
     * constructor of UC11 controllers
     *
     * @param fc faircenter in which the controller operates
     * @param u user that is using the UC11 feature
     */
    public UC11Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.user = u;
    }

    /**
     * returns the events that have applications from the user
     *
     * @return list of events with applications from the user
     */
    public List<Event> getEventsWithApplicationsFromUser() {
        List<Event> events = fc.getEventRegistry().getEventsWithApplicationFromUser(user);
        return events;
    }

    /**
     * returns the titles of the events given
     *
     * @param eventsList events from which to get titles
     * @return array of titles from the events
     */
    public String[] getTitlesOfEvents(List<Event> eventsList) {
        return fc.getEventRegistry().getEventTitles(eventsList);
    }

    /**
     * set method of the atribute event
     *
     * @param event to set as event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * creates an object Application
     *
     * @param tradeName trade name to give to the application
     * @param description description to give to the application
     * @param address address to give to the application
     * @param phone phone number to give to the application
     * @param boothArea intended booth area to give to the application
     * @param productsToBeDisplayed products to be displayed to give to the
     * application
     * @param numberOfInvitations number of invitatios to give to the
     * application
     * @param keywords keywords to give to the application
     */
    public void createApplication(String tradeName, String description, String address, long phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        this.application = new Application(tradeName, description, address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);

    }

    /**
     * returns the Applications information
     *
     * @return string containing the information
     */
    public String getApplicationInfo() {
        return application.toString();
    }

    /**
     * registers the changes to the Application
     */
    public void registerApplicationChanges() {
        event.saveApplicationChanges(application);
    }

}
