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
 * @author Hugo
 */
public class UC41Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private final User user;
    protected Event selectedEvent;
    private double[] eventStandsAreas;

    public UC41Controller(FairCenter fc, User user) {
        this.fc = fc;
        this.user = user;
        calculations = new Calculations();

    }

    public List<Event> getEventsByOrganizer() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventListByOrganizer = er.getEventsByOrganizer(user);
        return eventListByOrganizer;
    }

    public Object[][] getEventStandsFrequencyTable() {
        List<Stand> selectedEventStands = selectedEvent.getStands();
        eventStandsAreas = new double[selectedEventStands.size()];
        for (int i = 0; i < selectedEventStands.size(); i++) {
            eventStandsAreas[i] = selectedEventStands.get(i).getArea();
        }
        return calculations.getEventStandsFrequencyTable(eventStandsAreas);
    }

    public double getStandsMeanRate() {
        return calculations.getMeanRate(eventStandsAreas);
    }

    public double getStandsStandardDeviationRate() {
        return calculations.getStandardDeviation(eventStandsAreas);
    }

    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    public Event getSelectedEvent() {
        return this.selectedEvent;
    }
}
