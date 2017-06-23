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
 * @author Hugo
 */
public class UC40Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private final User user;
    protected Event selectedEvent;

    public UC40Controller(FairCenter fc, User user) {
        this.fc = fc;
        this.user = user;
        calculations = new Calculations();

    }

    public List<Event> getEventsByOrganizer() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventListByOrganizer = er.getEventsByOrganizer(user);
        return eventListByOrganizer;
    }

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

    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    public Event getSelectedEvent() {
        return this.selectedEvent;
    }
}
