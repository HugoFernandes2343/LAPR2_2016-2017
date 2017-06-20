/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


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
    @XmlElement
    private EventManagerRegistry eventManagers;

    /**
     *
     */
    public FairCenter() {//Add Params , nome?, local?
        confirmedUsers = new UserRegistry();
        eventRegistry = new EventRegistry();
        unconfirmedUsers = new UserRegistry();
        eventManagers = new EventManagerRegistry();
    }

    /**
     *
     * @param newFC
     */
    public FairCenter(FairCenter newFC) {
        this.confirmedUsers = newFC.confirmedUsers;
        this.eventManagers = newFC.eventManagers;
        this.eventRegistry = newFC.eventRegistry;
        this.unconfirmedUsers = newFC.unconfirmedUsers;
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
        if (eventRegistry.validateEvent(event)) {
            return eventRegistry.registerEvent(event);
        }
        return false;
    }

    /**
     *
     * @return
     */
    public EventManagerRegistry getEventManagerRegistry() {
        return eventManagers;
    }

    public boolean registerUser(User user) {
        return confirmedUsers.registerUser(user);
    }

//    public void exportData() {
//        //Todo
//    }
    public void encryptUsers() {
        this.confirmedUsers.encryptAll();
    }

    public void decryptUsers() {
        this.confirmedUsers.decryptAll();
    }

    public void updatedDataFromXML(Event selectedEvent, Event ev) {
        this.confirmedUsers.getUsersList().addAll(ev.getFAEList_UserRef());
    }

    public boolean registerApplication(Event event, Application application) {
        return eventRegistry.registerApplication(event, application);
    }

    public boolean registerApplicationChanges(Event event, Application application) {
        return eventRegistry.registerApplicationChanges(event, application);
    }
}
