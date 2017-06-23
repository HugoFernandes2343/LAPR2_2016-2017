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
 * @author LAPR2-G054
 */
public class UC22Controller {

    private final FairCenter fc;
    private final User user;
    private Event event;

    /**
     * constructor of UC22 controllers
     * @param fc faircenter in which the controller operates
     * @param u user that is using the UC22 feature
     */
    public UC22Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.user = u;
    }

    /**
     * returns all events currently registered
     * @return List of all events
     */
    public List<Event> getAllEvents() {
        return fc.getEventRegistry().getAllEvents();
    }
    
    /**
     * returns the titles of the events in the list given
     * @param eventsList list of events
     * @return array that contains the titles of the events
     */
    public String[] getEventTitles(List<Event> eventsList) {
        return fc.getEventRegistry().getEventTitles(eventsList);
    }
    
    /**
     * sets the Event object in the controller
     * @param event to set as event
     */
    public void setEvent(Event event) {
        this.event=event;
    }

    /**
     * returns an array of array that contains in one collumm the trade name of an application and in another the address
     * @return object with the information on the trade name and address of each application
     */
    public Object[][] getApplicationInfoTable() {
        ApplicationList applicationList = event.getApplicationList();
        List<Application> applications = applicationList.getApplications();
        Object[][] infoTable = new Object[applications.size()][2];
        for(int i = 0; i<applications.size();i++){
            infoTable[i][0]=applications.get(i).getTradeName();
            infoTable[i][1]=applications.get(i).getAddress();
        }
        return infoTable;
    }

    

}
