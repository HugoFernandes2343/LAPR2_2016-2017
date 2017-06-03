/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author PC
 */
public class FairCenter {

    /**
     *
     */
    private EventRegistry eventRegistry;

    /**
     *
     */
    private UserRegistry userRegistry;

    /**
     *
     */
    public FairCenter() {//Add Params , nome?, local?
        userRegistry = new UserRegistry();
        eventRegistry = new EventRegistry();
    }
    
        /**
     *
     * @return
     */
    public UserRegistry getUserRegistry() {
        return userRegistry;
    }

    /**
     *
     * @return
     */
    public EventRegistry getEventRegistry() {
        return eventRegistry;
    }

}
