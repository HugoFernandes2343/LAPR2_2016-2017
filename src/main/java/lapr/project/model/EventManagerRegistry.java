/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
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
    private List<EventManager> eventManagerList;

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
    public List<EventManager> getEventManagersList() {
        return eventManagerList;
    }
    
    public List<User> getEventManagersList_byUserRef(){
        List<User> eventManagerUserRef = new ArrayList<>();
        for(EventManager ev : eventManagerList){
            eventManagerUserRef.add(ev.getUser());
        }
        return eventManagerUserRef;
    }

    
    public void addEventManager(EventManager ev){
        eventManagerList.add(ev);
    }
}
