/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;
import lapr.project.model.Organizer;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;

/**
 *
 * @author Hugo
 */
public class UC01Controller {

    private Event event;
    private FairCenter fc;
    private List<Organizer> organizerList;

    public UC01Controller(FairCenter fc) {
        organizerList = new ArrayList<>();
        this.fc = fc;
    }

    public void createEvent(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd, String eventType) {
        EventRegistry eventList = fc.getEventRegistry();
        event = eventList.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
    }

    public List<User> getUsers() {
        UserRegistry userList = fc.getConfirmedUsers();
        return userList.getUsersList();
    }

    public void newOrganizer(User user) {
        organizerList.add(new Organizer(user));
        event.addOrganizer(user);
    }

    public String getEventInfo() {
        return event.toString();
    }

    public int getNumberOfOrganizers() {
        return organizerList.size();
    }

    public boolean registerEvent() {
        return fc.registerEvent(event);
    }

    public boolean validateOrganizer(User user) {
        Organizer organizer = new Organizer(user);
        for (int i = 0; i < organizerList.size(); i++) {
            if (organizerList.get(i).equals(organizer)) {
                return false;
            }

        }
        return true;
    }

}
