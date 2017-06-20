/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.List;
import lapr.project.model.*;

/**
 *
 * @author PC
 */
public class ClearanceCheck {

    private User u;
    private FairCenter fc;

    public ClearanceCheck(FairCenter fc, User u) {
        this.u = u;
        this.fc = fc;
    }

    public boolean isFAE() {
        List<Event> eventList = fc.getEventRegistry().getAllEvents();
        for (Event event : eventList) {
        List<User> FAEList_UserRef = event.getFAEList_UserRef();    
            for (User user : FAEList_UserRef) {
                if (u.equals(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOrg() {
        List<Event> eventList = fc.getEventRegistry().getAllEvents();
        for (Event event : eventList) {
        List<User> OrgList_UserRef = event.getOrganizersList_UserRef();    
            for (User user : OrgList_UserRef) {
                if (u.equals(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEventManager() {
        List<User> EventManager_UserRef = fc.getEventManagerRegistry().getEventManagersList_byUserRef();
        for (User user : EventManager_UserRef) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isRepresentative(){
        return true;//Add rest
    }
}
