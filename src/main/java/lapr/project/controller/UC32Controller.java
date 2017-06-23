/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.JFrame;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.utils.XMLImporter;

/**
 *
 * @author LAPR2-G054
 */
public class UC32Controller {

    private final FairCenter fc;
    protected Event selectedEvent;

    /**
     * constructor of UC32 Controller
     *
     * @param fc faircenter in which the controller operates
     */
    public UC32Controller(FairCenter fc) {
        this.fc = fc;
    }

    /**
     * Creates the object XMLImporter
     *
     * @param window login window
     * @param menuWindow main menu window
     * @throws FileNotFoundException
     */
    public void importEventData(JFrame window, JFrame menuWindow) throws FileNotFoundException {
        Event ev;
        XMLImporter importData = new XMLImporter();
        ev = importData.importEventData(window, menuWindow);

        selectedEvent.recieveXMLData(ev);
        fc.updatedDataFromXML(selectedEvent, ev);
    }

    /**
     * returns all events
     *
     * @return list of events
     */
    public List<Event> getAllEvents() {
        return fc.getEventRegistry().getAllEvents();
    }

    /**
     * set of the attribute selectedEvent
     *
     * @param e event to be set as selectedEvent
     */
    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    /**
     * get of the attribute selectedEvent
     *
     * @return selectedEvent
     */
    public Event getSelectedEvent() {
        return this.selectedEvent;
    }
}