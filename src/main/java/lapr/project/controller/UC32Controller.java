/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private User u;
    public Event selectedEvent;

    public UC32Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.u = u;
    }

//    public void importCongressData() throws FileNotFoundException {
//        Congress c;
//        XMLImporter importData = new XMLImporter();
//        c = importData.importEventData();
//        fc.updatedDataFromXML(c);
//    }
//
//    public void importExhibitionData() throws FileNotFoundException {
//        Exhibition ex;
//        XMLImporter importData = new XMLImporter();
//        ex = importData.importEventData();
//        fc.updatedDataFromXML(ex);
//    }
    
    public void importEventData() throws FileNotFoundException {
        Event ev;
        XMLImporter importData = new XMLImporter();
        ev = importData.importEventData();
        selectedEvent.recieveXMLData(ev);//Requer Revisao
        fc.updatedDataFromXML(selectedEvent,ev);//Implementar
    }

    public ArrayList<Event> getAllEvents() {
        return fc.getEventRegistry().getAllEvents();
    }
    
}
