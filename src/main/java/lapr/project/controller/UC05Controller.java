/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.*;

/**
 *
 * @author LAPR2-G054
 */
public class UC05Controller {

    private final FairCenter fc;
    private Event event;
    private ApplicationList applicationList;
    private Application application = new Application();
    private User user;

    /**
     * constructor of UC05
     *
     * @param fc fair center in which the controller operates
     * @param u user that is using the UC22 features
     */
    public UC05Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.user = u;
    }

    /**
     * returns a list of the events that are ready to be submitted
     *
     * @return list of events
     */
    public List<Event> getEventsReadyForSubmit() {
        return fc.getEventRegistry().getEventsReadyForSubmit();
    }

    /**
     * Returns a array of titles from the events given
     *
     * @param events list of events from which to get the titles
     * @return array of titles
     */
    public String[] getTitlesForSubmit(List<Event> events) {
        return fc.getEventRegistry().getEventTitles(events);
    }

    /**
     * sets the Event object from the controller
     *
     * @param event to set as this event
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
        this.applicationList = event.getApplicationList();
        this.application = new Application(tradeName, description, address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);

    }

    /**
     * saves the application as part of the event
     *
     */
    public void registerApplication() {
        application.setRepresentative(new Representative(user));
        applicationList.registerApplication(application);
    }

    /**
     * returns a stingr with the infomation from the toString of the applicaiton
     *
     * @return string with the information
     */
    public String getApplicationInfo() {
        return application.toString();
    }

}

