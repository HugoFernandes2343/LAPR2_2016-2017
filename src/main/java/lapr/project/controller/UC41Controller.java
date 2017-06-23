/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Calculations;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;
import lapr.project.model.Stand;
import lapr.project.model.User;

/**
 *
 * @author LAPR2-G054
 */
public class UC41Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private final User user;
    protected Event selectedEvent;
    private double[] eventStandsAreas;

    /**
     * constructor of UC41 controllers
     *
     * @param fc faircenter in which the controller operates
     * @param user user that is using the UC41 feature
     */
    public UC41Controller(FairCenter fc, User user) {
        this.fc = fc;
        this.user = user;
        calculations = new Calculations();

    }

    /**
     * gets all the events of the organizer
     *
     * @return list of events
     */
    public List<Event> getEventsByOrganizer() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventListByOrganizer = er.getEventsByOrganizer(user);
        return eventListByOrganizer;
    }

    /**
     * returns event stands frequency table
     *
     * @return matrix that represnts the frequency table
     */
    public Object[][] getEventStandsFrequencyTable() {
        List<Stand> selectedEventStands = selectedEvent.getStands();
        eventStandsAreas = new double[selectedEventStands.size()];
        for (int i = 0; i < selectedEventStands.size(); i++) {
            eventStandsAreas[i] = selectedEventStands.get(i).getArea();
        }
        return calculations.getEventStandsFrequencyTable(eventStandsAreas);
    }

    /**
     * returns stands mean rate
     *
     * @return mean rate (double)
     */
    public double getStandsMeanRate() {
        return calculations.getMeanRate(eventStandsAreas);
    }

    /**
     * returns standsStandardDeviationRate
     *
     * @return Standard Deviation Rate (double)
     */
    public double getStandsStandardDeviationRate() {
        return calculations.getStandardDeviation(eventStandsAreas);
    }

    /**
     * set method of selectedEvent attribute
     *
     * @param e event to set as selectedEvent
     */
    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    /**
     * get method of the selectedEvent attribute
     *
     * @return selectedEvent
     */
    public Event getSelectedEvent() {
        return this.selectedEvent;
    }
}
