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
import lapr.project.model.User;

/**
 *
 * @author LAPR2-G054
 */
public class UC40Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private final User user;
    protected Event selectedEvent;

    /**
     * constructor of UC40 controllers
     *
     * @param fc faircenter in which the controller operates
     * @param user user that is using the UC40 feature
     */
    public UC40Controller(FairCenter fc, User user) {
        this.fc = fc;
        this.user = user;
        calculations = new Calculations();

    }

    /**
     * get all organizer events
     *
     * @return list of event
     */
    public List<Event> getEventsByOrganizer() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventListByOrganizer = er.getEventsByOrganizer(user);
        return eventListByOrganizer;
    }

    /**
     * returns the event keyword frequency talbe
     *
     * @return matrix that is the table of frequency
     */
    public Object[][] getEventKeywordFrequencyTable() {
        ApplicationList applicationList = selectedEvent.getApplicationList();
        List<Application> applications = applicationList.getApplications();
        List<String> allKeywords = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            List<String> keywords = applications.get(i).getKeywordList();
            for (int j = 0; j < keywords.size(); j++) {
                allKeywords.add(keywords.get(j));
            }
        }
        return calculations.getEventKeywordsFrequencyTable(allKeywords);
    }

    /**
     * set of the selectedEvent attribute
     *
     * @param e event to set as selectedEvent
     */
    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    /**
     * get of the selectedEvent
     *
     * @return selectedEvent
     */
    public Event getSelectedEvent() {
        return this.selectedEvent;
    }
}
