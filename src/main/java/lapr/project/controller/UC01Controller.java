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
 * @author LAPR2-G054
 */
public class UC01Controller {

    private Event event;
    private FairCenter fc;
    private List<Organizer> organizerList;

    /**
     * constructor of UC05
     *
     * @param fc fair center in which the controller operates
     */
    public UC01Controller(FairCenter fc) {
        organizerList = new ArrayList<>();
        this.fc = fc;
    }

    /**
     * creates an Event
     * @param title title for the Event
     * @param description description for the Event
     * @param place place for the Event
     * @param startDate start date for the Event
     * @param endDate end date for the Event
     * @param applicationBegin application submission start date for the event
     * @param applicationEnd application submission end date for the event
     * @param eventType event type for the event
     */
    public void createEvent(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd, String eventType) {
        EventRegistry eventList = fc.getEventRegistry();
        event = eventList.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
    }

    /**
     * returns the confirmed user list
     * @return list of users
     */
    public List<User> getUsers() {
        UserRegistry userList = fc.getConfirmedUsers();
        return userList.getUsersList();
    }

    /**
     * creates an organizer
     * @param user user that is usign the app
     */
    public void newOrganizer(User user) {
        organizerList.add(new Organizer(user));
        event.addOrganizer(user);
    }

    /**
     * returns the event information
     * @return string of the information
     */
    public String getEventInfo() {
        return event.toString();
    }

    /**
     * return number of organizers
     * @return int that is the number of organizers
     */
    public int getNumberOfOrganizers() {
        return organizerList.size();
    }

    /**
     * registers the event in the registry
     * @return
     */
    public boolean registerEvent() {
        return fc.registerEvent(event);
    }

    /**
     * checks if there is not already an organizer with this user
     * @param user user that is logged in 
     * @return boolean that confirms that the organizer is valid(true) or not(false)
     */
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

