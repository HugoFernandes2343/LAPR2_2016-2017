/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.*;

public class UC05Controller {

   
    private final FairCenter fc;
    private Event event;
    private ApplicationList applicationList;
    private Application application = new Application();
    private User user;

    public UC05Controller(FairCenter fc,User u) {
        this.fc = fc;
        this.user=u;
    }

    /**
     *
     * @return
     */
    public List<Event> getEventsReadyForSubmit() {
        return fc.getEventRegistry().getEventsReadyForSubmit();
    }

    /**
     *
     * @param events
     * @return
     */
    public String[] getTitlesForSubmit(List<Event> events) {
        return fc.getEventRegistry().getTitlesForSubmit(events);
    }

    /**
     *
     * @param event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * creates an empty object Application
     *
     * @param tradeName
     * @param description
     * @param address
     * @param phone
     * @param boothArea
     * @param productsToBeDisplayed
     * @param numberOfInvitations
     * @param keywords
     */
     public void createApplication(String tradeName,String description ,String address, long phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        this.applicationList = event.getApplicationList();
        this.application = new Application(tradeName, description ,address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);
        
    }

    /**
     * saves the application as part of the event
     * @return 
     */
    public boolean registerApplication() {
        application.setRepresentative(new Representative(user));
        return fc.registerApplication(event, application);
    }

    public String getApplicationInfo() {
        return application.toString();
    }

}
