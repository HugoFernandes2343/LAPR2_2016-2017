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

    public UC05Controller(FairCenter fc) {
        this.fc = fc;
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
     * @param address
     * @param phone
     * @param boothArea
     * @param productsToBeDisplayed
     * @param numberOfInvitations
     * @param keywords
     */
    public void createApplication(String tradeName, String address, long phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        this.applicationList = event.getApplicationList();
        this.application = new Application(tradeName, address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);
        applicationList.addApplication(application);
    }

    /**
     * saves the application as part of the event
     * @return 
     */
    public boolean registerApplication() {
        return fc.registerApplication(event, application);
    }

    /**
     * show's all events ready to get applications
     * @return 
     */
    public List<Event> showReadyEventList() {
        // TODO - implement UC05Contoller.showReadyEventList
        throw new UnsupportedOperationException();
    }

    public String getApplicationInfo() {
        return application.toString();
    }

    public String getEventByTitle(String eventTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
