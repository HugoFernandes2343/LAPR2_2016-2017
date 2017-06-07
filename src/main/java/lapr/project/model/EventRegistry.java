/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class EventRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Congress> congressList;
    private ArrayList<Exhibition> exhibitionList;

    public EventRegistry() {
        this.congressList = new ArrayList<>();
        this.exhibitionList = new ArrayList<>();
    }

    public ArrayList<Event> getEventsByOrganizer() {
        ArrayList<Event> allEvents = new ArrayList<>();
        allEvents.addAll(congressList);
        allEvents.addAll(exhibitionList);
        for (Event e : allEvents) {
            if (e.validateEventStateFAEDefined()) {
                //A implementar
            }

        }
        return null;
    }

    public Event createEvent(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd, String eventType) {
        Event event = null;
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
}
