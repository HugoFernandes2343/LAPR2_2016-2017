/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Date;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizerList;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;

/**
 *
 * @author Hugo
 */
public class UC01Controller {

    private Event event;
    private FairCenter fc;
    private OrganizerList organizerList;

    public UC01Controller(FairCenter fc, User user) {
        this.fc = fc;
    }

    public void createEvent(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd, String eventType) {
        EventRegistry eventList = fc.getEventRegistry();
        event = eventList.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
    }

    public ArrayList<User> getUsers() {
        UserRegistry userList = fc.getConfirmedUsers();
        ArrayList<User> users = userList.getUsersList();
        return users;
    }

    public void newOrganizer(User user) {
        organizerList.addOrganizer(user);
        event.addOrganizer(user);
    }

    public String getEventInfo() {
        return event.toString();
    }

    public String getOrganizerInfo() {
        String organizersInfo = "";
        ArrayList<Organizer> organizers = organizerList.getList();
        for (int i = 0; i < organizers.size(); i++) {
            organizersInfo = organizersInfo + organizers.get(i).toString();
        }
        return organizersInfo;
    }

    public boolean registerEvent() {
        return fc.registerEvent(event);
    }

}
