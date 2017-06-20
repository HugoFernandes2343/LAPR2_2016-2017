/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileNotFoundException;
import java.util.List;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.utils.XMLImporter;

/**
 *
 * @author PC
 */
public class UC32Controller {

    private FairCenter fc;
    protected Event selectedEvent;

    public UC32Controller(FairCenter fc, User u) {
        this.fc = fc;
    }

    public void importEventData() throws FileNotFoundException {
        Event ev;
        XMLImporter importData = new XMLImporter();
        ev = importData.importEventData();
        selectedEvent.recieveXMLData(ev);//Requer Revisao
        fc.updatedDataFromXML(selectedEvent,ev);//Implementar
    }

    public List<Event> getAllEvents() {
        return fc.getEventRegistry().getAllEvents();
    }
    
    public void setSelectedEvent(Event e){
        this.selectedEvent=e;
    }
    
    public Event getSelectedEvent(){
        return this.selectedEvent;
    }
}
