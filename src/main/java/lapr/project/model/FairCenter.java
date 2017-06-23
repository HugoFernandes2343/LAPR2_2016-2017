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
    public FairCenter() {
        confirmedUsers = new UserRegistry();
        eventRegistry = new EventRegistry();
        unconfirmedUsers = new UserRegistry();
        eventManagers = new EventManagerRegistry();
        createDefaultUser();
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
        this.confirmedUsers.getUsersList().addAll(ev.getFAEListUserRef());
    }

    


    private void createDefaultUser() {
        String defaultValue ="default";
        User eventManagerDefault = new User(defaultValue,defaultValue,defaultValue,defaultValue,"","GMT+00","delta");
        this.registerUser(eventManagerDefault);
        this.getEventManagerRegistry().addEventManager(new EventManager(eventManagerDefault));
        
        String guestInfo = "guest";
        User guestUser = new User(guestInfo,guestInfo,guestInfo,guestInfo,"","GMT+00",guestInfo);
        this.registerUser(guestUser);
    }
}
