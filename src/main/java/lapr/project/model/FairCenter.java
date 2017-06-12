/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FairCenter {

    /**
     *
     */
    @XmlElement
    private EventRegistry eventRegistry;

    /**
     *
     */
    @XmlElement
    private UserRegistry confirmedUsers;

    /**
     *
     */
    @XmlElement
    private UserRegistry unconfirmedUsers;

    /**
     *
     */
    public FairCenter() {//Add Params , nome?, local?
        confirmedUsers = new UserRegistry();
        eventRegistry = new EventRegistry();
        unconfirmedUsers = new UserRegistry();
    }

    /**
     *
     * @return
     */
    public UserRegistry getConfirmedUsers() {
        return confirmedUsers;
    }

    /**
     *
     * @return
     */
    public EventRegistry getEventRegistry() {
        return eventRegistry;
    }

    public boolean registerEvent(Event event) {
        return eventRegistry.registerEvent(event);
    }

}
