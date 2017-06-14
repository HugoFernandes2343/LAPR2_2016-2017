/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventManagerRegistry {
    
    
    /**
     *
     */
    @XmlElementWrapper
    @XmlElement(name="eventManager")
    private ArrayList<EventManager> eventManagerList;

    /**
     *
     */
    public EventManagerRegistry() {
        eventManagerList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<EventManager> getEventManagersList() {
        return eventManagerList;
    }
    
    public ArrayList<User> getEventManagersList_byUserRef(){
        ArrayList<User> eventManagerUserRef = new ArrayList<>();
        for(EventManager ev : eventManagerList){
            eventManagerUserRef.add(ev.getUser());
        }
        return eventManagerUserRef;
    }

    
    public void addEventManager(EventManager ev){
        eventManagerList.add(ev);
    }
}
