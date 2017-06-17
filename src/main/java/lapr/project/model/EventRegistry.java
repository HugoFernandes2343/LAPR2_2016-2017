/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventRegistry {

    @XmlElement
    private ArrayList<Congress> congressList;
    @XmlElement
    private ArrayList<Exhibition> exhibitionList;

    public EventRegistry() {
        this.congressList = new ArrayList<>();
        this.exhibitionList = new ArrayList<>();
    }

    public ArrayList<Event> getEventsByOrganizer(User u) {
        ArrayList<Event> allEvents = getAllEvents();
        ArrayList<Event> validEvents = new ArrayList<>();
        for (Event e : allEvents) {
            if (e.getOrganizersList_UserRef().contains(u)) {
                validEvents.add(e);
            }
        }
        return validEvents;
    }

    public ArrayList<Event> getEventsNotFAEDefined(){
        ArrayList<Event> allEvents = getAllEvents();
        ArrayList<Event> validEvents = new ArrayList<>();
        for (Event e : allEvents) {
            if (e.validateEventStateCreated() == true) {
                validEvents.add(e);
            }
        }
        return validEvents;
    }
    
    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> allEvents = new ArrayList<>();
        allEvents.addAll(congressList);
        allEvents.addAll(exhibitionList);
        return allEvents;
    }

    public Event createEvent(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd, String eventType) {
        Event event;
        if (eventType.equals("Exhibition")) {
            event = new Exhibition(title, description, place, startDate, endDate, applicationBegin, applicationEnd);
        } else {
            event = new Congress(title, description, place, startDate, endDate, applicationBegin, applicationEnd);
        }
        return event;
    }

    public boolean registerEvent(Event event) {
        if (event instanceof Congress) {
            congressList.add((Congress) event);
        } else {
            exhibitionList.add((Exhibition) event);
        }
        return true;
    }
    
    public boolean validateEvent(Event event) {
        for (int i = 0; i < exhibitionList.size(); i++) {
            if (exhibitionList.get(i).equals(event)) {
                return false;
            }
        }
        for (int j = 0; j < congressList.size(); j++) {
            if (congressList.get(j).equals(event)) {
                return false;
            }

        }
        return true;
    }
    public ArrayList<Event> getEventsReadyForSubmit() {
        ArrayList<Event> eventsReadyForSubmit = new ArrayList<>();
        for (int i = 0; i < congressList.size(); i++) {
            if (congressList.get(i).validateEventStateApplicationsOpen()) {
                eventsReadyForSubmit.add(congressList.get(i));
            }
        }
        for (int i = 0; i < exhibitionList.size(); i++) {
            if (exhibitionList.get(i).validateEventStateApplicationsOpen()) {
                eventsReadyForSubmit.add(exhibitionList.get(i));
            }
        }
        return eventsReadyForSubmit;
    }

    public String[] getTitlesForSubmit(ArrayList<Event> eventsReadyForSubmit) {
        String[] titles = new String[eventsReadyForSubmit.size() + 1];
        titles[0] = "";
        for (int i = 0; i < eventsReadyForSubmit.size(); i++) {
            titles[i + 1] = eventsReadyForSubmit.get(i).getTitle();
        }
        return titles;
    }

    public boolean registerApplication(Event event, Application application) {
        return event.registerApplication(application);
    }
}
